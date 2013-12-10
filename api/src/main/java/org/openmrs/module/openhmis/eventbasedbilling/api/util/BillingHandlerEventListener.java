package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.log4j.Logger;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.Daemon;
import org.openmrs.event.EventListener;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.billableobjects.api.IBillableObjectDataService;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillingHandler;
import org.openmrs.module.openhmis.billableobjects.api.util.BillableObjectEventListener;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociationContext;
import org.openmrs.module.openhmis.eventbasedbilling.api.impl.BillAssociationContext;

public class BillingHandlerEventListener<T extends OpenmrsObject> implements EventListener, Runnable {
	private static final Logger logger = Logger.getLogger(BillableObjectEventListener.class);
	private DaemonToken daemonToken;
	private IBillingHandler<T> billingHandler;
	private Message message;

	public BillingHandlerEventListener(IBillingHandler<T> handler, DaemonToken token) {
		if (handler == null)
			throw new APIException(IBillingHandler.class.getSimpleName() + " must be supplied.");			
		if (token == null)
			throw new APIException("The DaemonToken cannot be null.");

		billingHandler = handler;
		daemonToken = token;
	}
	
	@Override
	public void onMessage(Message message) {
		this.message = message;
		Daemon.runInDaemonThread(this, daemonToken);
	}
	
	@Override
	public void run() {
		try {
			MapMessage mapMessage = (MapMessage) message;
			String uuid = mapMessage.getString("uuid");
			IBillableObject<T> billableObject = Context.getService(IBillableObjectDataService.class).getByUuid(uuid);
			List<BillLineItem> lineItems = billingHandler.handleObject(billableObject.getObject());
			if (lineItems == null) // not handled
				return;
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
		
	}


}
