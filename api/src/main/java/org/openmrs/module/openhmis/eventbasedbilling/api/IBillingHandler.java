package org.openmrs.module.openhmis.eventbasedbilling.api;

import javax.jms.Message;

import org.openmrs.OpenmrsMetadata;
import org.openmrs.event.EventListener;
import org.openmrs.module.openhmis.cashier.api.model.Bill;

public interface IBillingHandler extends OpenmrsMetadata, EventListener {
	public IBillAssociator getBillAssociator();
	public void setBillAssociator(IBillAssociator associator);
	public Bill handleEvent(Message message);
}
