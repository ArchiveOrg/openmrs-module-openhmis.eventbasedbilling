package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.List;

import org.openmrs.OpenmrsMetadata;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociationContext;

public interface IBillAssociator extends OpenmrsMetadata {
	public Bill associateItemsToBill(List<BillLineItem> lineItems, IBillAssociationContext context);
}
