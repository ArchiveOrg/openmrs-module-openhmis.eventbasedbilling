package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.cashier.api.model.CashPoint;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociationContext;

public class BillAssociationContext implements IBillAssociationContext {
	
	private Patient patient;
	private Provider provider;
	private CashPoint cashPoint;
	private IBillableObject<?> billableObject;
	
	public BillAssociationContext() {
	}
	
	public BillAssociationContext(Patient patient, Provider provider, IBillableObject<?> objectBilledFor) {
		this.patient = patient;
		this.provider = provider;
		this.billableObject = objectBilledFor;
	}

	@Override
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public CashPoint getCashPoint() {
		return cashPoint;
	}

	public void setCashPoint(CashPoint cashPoint) {
		this.cashPoint = cashPoint;
	}

	@Override
	public IBillableObject<?> getBillableObject() {
		return billableObject;
	}

	public void setBillableObject(IBillableObject<?> billableObject) {
		this.billableObject = billableObject;
	}
}
