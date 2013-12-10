package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openmrs.event.Event;
import org.openmrs.event.Event.Action;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillingHandler;
import org.openmrs.module.openhmis.billableobjects.api.type.BaseBillableObject;
import org.openmrs.module.openhmis.billableobjects.api.util.BillableObjectsHelper;
import org.openmrs.module.openhmis.billableobjects.api.util.BillingHandlerHelper;

public class EventHelper {
	private static final Logger logger = Logger.getLogger(EventHelper.class);
	private static DaemonToken daemonToken;
	private static Set<BillingHandlerEventListener> boundListeners = new HashSet<BillingHandlerEventListener>();
	
	/**
	 * @should bind all existing handlers
	 */
	public static void bindListenerForAllHandlers() {
		unbindListenerForAllHandlers();
		for (Class<?> handledClass : BillingHandlerHelper.getActivelyHandledClasses()) {
			Set<IBillingHandler<?>> handlers = BillingHandlerHelper.getHandlersForClassName(handledClass.getName());
			for (IBillingHandler handler : handlers) {
				BillingHandlerEventListener listener = new BillingHandlerEventListener(handler, daemonToken);
				boundListeners.add(listener);
				Class<? extends IBillableObject> billableObjectType = BillableObjectsHelper.getBillableObjectTypeForClassName(handledClass.getName());
				Event.subscribe(
						billableObjectType,
						Action.CREATED.toString(),
						listener
				);
			}
		}
	}
	
	public static void unbindListenerForAllHandlers() {
		BillingHandlerEventListener[] listeners = new BillingHandlerEventListener[boundListeners.size()];
		boundListeners.toArray(listeners);
		for (BillingHandlerEventListener listener : listeners) {
			Event.unsubscribe(
					BaseBillableObject.class,
					Action.CREATED,
					listener
			);
			boundListeners.remove(listener);
		}
		
	}

	public static void setDaemonToken(DaemonToken daemonToken) {
		EventHelper.daemonToken = daemonToken;
	}
}
