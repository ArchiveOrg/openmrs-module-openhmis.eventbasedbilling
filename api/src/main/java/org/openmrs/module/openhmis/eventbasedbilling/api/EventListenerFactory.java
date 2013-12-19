package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.OpenmrsObject;
import org.openmrs.api.APIException;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.BillableObjectEventListener;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.HandlerChangeListener;

public class EventListenerFactory {
	private static volatile BillableObjectEventListener<OpenmrsObject> billableObjectEventListener;
	private static volatile HandlerChangeListener handlerChangeListener;
	// set a default token so that tests don't fail
	private static DaemonToken daemonToken = new DaemonToken("default");

	private EventListenerFactory() { /* private constructor */ }
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> instanceType) {
		if (instanceType == null)
			throw new APIException("Instance type cannot be null.");

		if (instanceType == BillableObjectEventListener.class) {
			if (billableObjectEventListener == null)
				billableObjectEventListener = new BillableObjectEventListener<OpenmrsObject>(daemonToken);
			return (T) billableObjectEventListener;
		}
		if (instanceType == HandlerChangeListener.class) {
			if (handlerChangeListener == null)
				handlerChangeListener = new HandlerChangeListener(daemonToken);
			return (T) handlerChangeListener;
		}
		throw new APIException(EventListenerFactory.class.getSimpleName() + " does not handle " + instanceType.getSimpleName());
	}
	
	public static void setDaemonToken(DaemonToken token) {
		daemonToken = token;
	}
}
