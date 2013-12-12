package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.log4j.Logger;
import org.openmrs.Auditable;
import org.openmrs.OpenmrsObject;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.Daemon;
import org.openmrs.event.EventListener;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.billableobjects.api.IBillableObjectDataService;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillingHandler;
import org.openmrs.module.openhmis.billableobjects.api.util.BillableObjectEventListener;
import org.openmrs.module.openhmis.billableobjects.api.util.BillingHandlerRecoverableException;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.cashier.api.model.CashPoint;
import org.openmrs.module.openhmis.eventbasedbilling.api.IEventBasedBillingOptionsService;
import org.openmrs.module.openhmis.eventbasedbilling.api.impl.BillAssociationContext;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.EventBasedBillingOptions;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;
import org.openmrs.notification.Alert;
import org.openmrs.notification.AlertRecipient;
import org.openmrs.notification.MessageException;

public class BillingHandlerEventListener<T extends OpenmrsObject> implements EventListener {
	private static final Logger logger = Logger.getLogger(BillableObjectEventListener.class);
	private DaemonToken daemonToken;
	private Map<String, Set<IBillingHandler<T>>> billableObjectClassNameToHandlerSetMap = 
			new HashMap<String, Set<IBillingHandler<T>>>();

	public BillingHandlerEventListener(DaemonToken token) {
		if (token == null)
			throw new APIException("The DaemonToken cannot be null.");

		daemonToken = token;
	}

	public void registerHandler(String billableObjetClassName, IBillingHandler<T> handler) {
		Set<IBillingHandler<T>> handlerSet = billableObjectClassNameToHandlerSetMap.get(billableObjetClassName);
		if (handlerSet == null) {
			handlerSet = new HashSet<IBillingHandler<T>>();
			billableObjectClassNameToHandlerSetMap.put(billableObjetClassName, handlerSet);
		}
		handlerSet.add(handler);
	}
	
	public void unregisterHandler(String billableObjetClassName, IBillingHandler<T> handler) {
		Set<IBillingHandler<T>> handlerSet = billableObjectClassNameToHandlerSetMap.get(billableObjetClassName);
		if (handlerSet == null)
			return;
		handlerSet.remove(handler);
	}
	
	public void unregisterHandler(String billableObjetClassName) {
		billableObjectClassNameToHandlerSetMap.remove(billableObjetClassName);
	}

	
	@Override
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			String classname = mapMessage.getString("classname");
			Set<IBillingHandler<T>> handlers = billableObjectClassNameToHandlerSetMap.get(classname);
			if (handlers != null) {
				for (IBillingHandler<T> handler : handlers) {
					Daemon.runInDaemonThread(new HandlerRunner(handler, message), daemonToken);
				}
			}
		}
		catch (JMSException e) {
			logger.error("Error reading JMS message!");
		}
	}
		
	class HandlerRunner implements Runnable {
		private IBillingHandler<T> handler;
		private Message message;
		
		public HandlerRunner(IBillingHandler<T> handler, Message message) {
			this.handler = handler;
			this.message = message;
		}

		@Override
		public void run() {
			try {
				MapMessage mapMessage = (MapMessage) message;
				String uuid = mapMessage.getString("uuid");
				IBillableObject<T> billableObject = Context.getService(IBillableObjectDataService.class).getByUuid(uuid);
				List<BillLineItem> lineItems;
				Exception recoverableException = null;
				try {
					lineItems = handler.handleObject(billableObject.getObject());
				} catch (BillingHandlerRecoverableException exception) {
					recoverableException = exception;
					lineItems = exception.getLineItems();
				}
				if (lineItems == null) // not handled
					return;
				EventBasedBillingOptions options = Context.getService(IEventBasedBillingOptionsService.class).getOptions();
				IBillAssociator associator = options.getBillAssociator();
				CashPoint cashPoint = options.getCashPoint();
				if (options.isEnabled()) {
					if (associator == null)
						logger.error("Automatic billing is enabled but no default associator is configured!");
					else if (cashPoint == null)
						logger.error("Automatic billing is enabled but no default cash point is configured!");
					else {
						// Find provider
						User user = Context.getAuthenticatedUser();
						Provider provider = null;
						Exception providerException = null;
						try {
							provider = (Provider) Context.getProviderService().getProvidersByPerson(user.getPerson()).toArray()[0];
						}
						catch (Exception e) { providerException = e; }
						if (provider == null)
							throw new APIException("Failed to determing provider for automatic billing process.", providerException);

						// Check Patient
						Patient patient = billableObject.getAssociatedPatient();
						if (patient == null)
							throw new APIException("Failed to determing patient for billable object.");							
						
						BillAssociationContext context = new BillAssociationContext();
						context.setProvider(provider);
						context.setCashPoint(cashPoint);
						context.setPatient(patient);
						context.setBillableObject(billableObject);
						
						Bill bill = associator.associateItemsToBill(lineItems, context);
						
						if (recoverableException != null) {
							Alert alert = new Alert();
							patient = bill.getPatient();
							alert.setText(String.format("Warning - bill %s, patient %s: %s",
								bill.getReceiptNumber(),
								patient.getGivenName() + " " + patient.getFamilyName(),
								recoverableException.getMessage()
							));
							try {
								Auditable object = (Auditable) billableObject.getObject();
								Set<AlertRecipient> set = new HashSet<AlertRecipient>();
								set.add(new AlertRecipient(object.getCreator(), false));
								alert.setRecipients(set);
								Context.getAlertService().saveAlert(alert);
							}
							catch (ClassCastException e) {
								logger.warn("");
							}
						}
					}
				}
			}
			catch (JMSException e) {
				logger.error("Error reading JMS message!");
			}
			
		}
	}
}
