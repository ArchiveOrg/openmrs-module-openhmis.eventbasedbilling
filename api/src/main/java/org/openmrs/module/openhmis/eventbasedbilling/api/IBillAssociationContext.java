package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.OpenmrsObject;
import org.openmrs.Patient;
import org.openmrs.Provider;

public interface IBillAssociationContext {
	public Patient getPatient();
	public Provider getProvider();
	public OpenmrsObject getObjectBilledFor();
}
