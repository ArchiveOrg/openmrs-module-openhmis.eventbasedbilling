package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.OpenmrsObject;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.BillingHandlerEventListener;

public class BillingHandlerEventListenerFactory {
	private static volatile BillingHandlerEventListener<OpenmrsObject> instance;
	private static DaemonToken daemonToken; 

	public static BillingHandlerEventListener<OpenmrsObject> getInstance() {
		if (instance == null)
			instance = new BillingHandlerEventListener<OpenmrsObject>(daemonToken);
		return instance;
	}
	
	public static void setDaemonToken(DaemonToken token) {
		if (daemonToken == null)
			daemonToken = token;
	}
}
