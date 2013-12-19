package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.OpenmrsObject;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openhmis.billableobjects.api.IBillableObjectsService;
import org.openmrs.module.openhmis.billableobjects.api.IBillingHandlerService;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.BillableObjectEventListener;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.HandlerChangeListener;

public interface IBillingEventService extends OpenmrsService {
	
	/**
	 * Bind BillableObjectEventListener for all handled billable object events
	 */
	void rebindListenerForAllHandlers();
	
	/**
	 * Unbind BillableObjectEventListener from all billable object events
	 */
	void unbindListenerForAllHandlers();
	
	/**
	 * Bind HandlerChangeListener to billing handler change events
	 */
	void bindNewBillingHandlerListener();
	
	/**
	 * Unbind HandlerChangeListener from billing handler change events
	 */
	void unbindNewBillingHandlerListener();
	
	IBillingHandlerService getBillingHandlerService();
	void setBillingHandlerService(IBillingHandlerService billableObjectsService);
	IBillableObjectsService getBillableObjectsService();
	void setBillableObjectsService(IBillableObjectsService billableObjectsService);
	BillableObjectEventListener<OpenmrsObject> getEventListener();
	void setEventListener(BillableObjectEventListener<OpenmrsObject> eventListener);
	HandlerChangeListener getHandlerChangeListener();
	void setHandlerChangeListener(HandlerChangeListener handlerChangeListener);
}
