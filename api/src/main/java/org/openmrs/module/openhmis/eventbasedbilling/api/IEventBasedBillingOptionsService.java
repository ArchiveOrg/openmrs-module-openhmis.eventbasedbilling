package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.EventBasedBillingOptions;

public interface IEventBasedBillingOptionsService extends OpenmrsService {
	public EventBasedBillingOptions getOptions();
	public void saveOptions(EventBasedBillingOptions options);	
}
