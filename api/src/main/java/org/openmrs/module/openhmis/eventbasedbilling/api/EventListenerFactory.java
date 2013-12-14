package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.OpenmrsObject;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.BillingHandlerEventListener;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.NewBillingHandlerListener;

public class EventListenerFactory {
	private static volatile BillingHandlerEventListener<OpenmrsObject> billingHandlerEventListener;
	private static volatile NewBillingHandlerListener newBillingHandlerListener;
	private static DaemonToken daemonToken; 

	private EventListenerFactory() { /* no constructor */ }
	
	public static BillingHandlerEventListener<OpenmrsObject> getBillingHandlerEventListenerInstance() {
		if (billingHandlerEventListener == null)
			billingHandlerEventListener = new BillingHandlerEventListener<OpenmrsObject>(daemonToken);
		return billingHandlerEventListener;
	}
	
	public static NewBillingHandlerListener getNewBillingHandlerListenerInstance() {
		if (newBillingHandlerListener == null)
			newBillingHandlerListener = new NewBillingHandlerListener(daemonToken);
		return newBillingHandlerListener;
	}
	
	public static void setDaemonToken(DaemonToken token) {
		if (daemonToken == null)
			daemonToken = token;
	}
}
