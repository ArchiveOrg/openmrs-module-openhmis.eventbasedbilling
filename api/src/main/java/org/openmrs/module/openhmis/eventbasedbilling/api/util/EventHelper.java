package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.openmrs.api.context.Context;
import org.openmrs.event.Event;
import org.openmrs.module.openhmis.billableobjects.api.IBillingHandler;
import org.openmrs.module.openhmis.billableobjects.api.IBillingHandlerDataService;
import org.openmrs.module.openhmis.billableobjects.api.util.BillsFor;

public class EventHelper {
	private static final Logger logger = Logger.getLogger(EventHelper.class);
	
	/**
	 * @should bind all existing handlers
	 */
	public static void bindAllHandlers() {
		List<IBillingHandler> handlers = Context.getService(IBillingHandlerDataService.class).getAll();
		for (IBillingHandler handler : handlers) {
			BillsFor annotation = handler.getClass().getAnnotation(BillsFor.class);
			if (annotation == null) {
				logger.warn(String.format("%s class %s for handler \"%s\" does not specify handled classes using %s annotation: skipping event binding.",
						IBillingHandler.class.getSimpleName(),
						handler.getClass().getSimpleName(),
						handler.getName(),
						BillsFor.class.getSimpleName()));
				continue;
			}
			for (Class<?> handledClass : annotation.value()) {
				Event.subscribe(handledClass, Event.Action.CREATED.toString(), handler);
			}
		}
	}
	
	public static void unbindAllHandlers() {
		
	}
	
}
