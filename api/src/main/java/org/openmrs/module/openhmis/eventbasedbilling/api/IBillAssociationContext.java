package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.module.openhmis.billableobjects.api.type.IBillableObject;
import org.openmrs.module.openhmis.cashier.api.model.CashPoint;

public interface IBillAssociationContext {
	public Patient getPatient();
	public Provider getProvider();
	public CashPoint getCashPoint();
	public IBillableObject<?> getBillableObject();
}
