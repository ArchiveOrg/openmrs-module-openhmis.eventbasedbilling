package org.openmrs.module.openhmis.eventbasedbilling.api;

import java.util.List;

import org.openmrs.OpenmrsMetadata;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;

public interface IBillAssociator extends OpenmrsMetadata {
	public Bill associateItemsToBill(List<BillLineItem> lineItems, Patient patient, Provider provider);
}
