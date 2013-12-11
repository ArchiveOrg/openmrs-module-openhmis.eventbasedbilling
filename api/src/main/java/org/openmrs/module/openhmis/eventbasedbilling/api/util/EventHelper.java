package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openmrs.OpenmrsObject;
import org.openmrs.event.Event;
import org.openmrs.event.Event.Action;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillingHandler;
import org.openmrs.module.openhmis.billableobjects.api.util.BillableObjectsHelper;
import org.openmrs.module.openhmis.billableobjects.api.util.BillingHandlerHelper;
import org.openmrs.module.openhmis.eventbasedbilling.api.BillingHandlerEventListenerFactory;

public class EventHelper {
	private static final Logger logger = Logger.getLogger(EventHelper.class);
	private static DaemonToken daemonToken;
	private static Set<Class<? extends IBillableObject>> handledBillabledObjectClasses =
			new HashSet<Class<? extends IBillableObject>>();
	
	/**
	 * @should bind all existing handlers
	 */
	public static void bindListenerForAllHandlers() {
		unbindListenerForAllHandlers();
		for (Class<?> handledClass : BillingHandlerHelper.getActivelyHandledClasses()) {
			Set<IBillingHandler<?>> handlers = BillingHandlerHelper.getHandlersForClassName(handledClass.getName());
			for (IBillingHandler handler : handlers) {
				Class<? extends IBillableObject> billableObjectType = BillableObjectsHelper.getBillableObjectTypeForClassName(handledClass.getName());
				BillingHandlerEventListener<OpenmrsObject> listener = BillingHandlerEventListenerFactory.getInstance();
				listener.registerHandler(billableObjectType.getName(), handler);
				handledBillabledObjectClasses.add(billableObjectType);
				Event.subscribe(
						billableObjectType,
						Action.CREATED.toString(),
						listener
				);
			}
		}
	}
	
	public static void unbindListenerForAllHandlers() {
		BillingHandlerEventListener listener = BillingHandlerEventListenerFactory.getInstance();
		Class<?>[] registeredClasses = new Class<?>[handledBillabledObjectClasses.size()];
		handledBillabledObjectClasses.toArray(registeredClasses);
		for (Class<?> cls : registeredClasses) {
			listener.unregisterHandler(cls.getName());
			Event.unsubscribe(
					cls,
					Action.CREATED,
					listener
			);
			handledBillabledObjectClasses.remove(cls);
		}
	}
}
