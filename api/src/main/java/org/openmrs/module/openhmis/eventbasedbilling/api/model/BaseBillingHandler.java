package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import org.openmrs.module.openhmis.commons.api.entity.model.BaseSerializableOpenmrsMetadata;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillingHandler;

public abstract class BaseBillingHandler extends BaseSerializableOpenmrsMetadata implements IBillingHandler {
	
	protected IBillAssociator billAssociator;

	@Override
	public IBillAssociator getBillAssociator() {
		return billAssociator;
	}
	
	@Override
	public void setBillAssociator(IBillAssociator associator) {
		billAssociator = associator;
	}

}
