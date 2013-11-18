package org.openmrs.module.openhmis.eventbasedbilling.api;

import java.util.List;

import javax.jms.Message;

import org.openmrs.OpenmrsMetadata;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;

public interface IBillingHandler extends OpenmrsMetadata {
	public IBillAssociator getBillAssociator();
	public void setBillAssociator(IBillAssociator associator);
	public List<BillLineItem> handleEvent(Message message);
}
