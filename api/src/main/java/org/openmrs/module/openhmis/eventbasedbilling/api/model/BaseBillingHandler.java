package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import javax.jms.Message;

import org.apache.log4j.Logger;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.commons.api.entity.model.BaseSerializableOpenmrsMetadata;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillingHandler;

public abstract class BaseBillingHandler extends BaseSerializableOpenmrsMetadata implements IBillingHandler {
	private static final Logger logger = Logger.getLogger(BaseBillingHandler.class);

	protected IBillAssociator billAssociator;

	@Override
	public IBillAssociator getBillAssociator() {
		return billAssociator;
	}
	
	@Override
	public void setBillAssociator(IBillAssociator associator) {
		billAssociator = associator;
	}
	
	public void onMessage(Message message) {
		Bill lineItems = this.handleEvent(message);
		if (lineItems != null && lineItems.getLineItems().size() > 0)
			this.billAssociator.associateItemsToBill(lineItems.getLineItems(), lineItems.getPatient(), lineItems.getCashier());					
	}
}