package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import org.openmrs.OpenmrsObject;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociationContext;

public class BillAssociationContext implements IBillAssociationContext {
	
	private Patient patient;
	private Provider provider;
	private OpenmrsObject objectBilledFor;
	
	public BillAssociationContext() {
	}
	
	public BillAssociationContext(Patient patient, Provider provider, OpenmrsObject objectBilledFor) {
		this.patient = patient;
		this.provider = provider;
		this.objectBilledFor = objectBilledFor;
	}

	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public Provider getProvider() {
		return provider;
	}

	@Override
	public OpenmrsObject getObjectBilledFor() {
		return objectBilledFor;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public void setObjectBilledFor(IBillableObject<?> objectBilledFor) {
		this.objectBilledFor = objectBilledFor;
	}
}
