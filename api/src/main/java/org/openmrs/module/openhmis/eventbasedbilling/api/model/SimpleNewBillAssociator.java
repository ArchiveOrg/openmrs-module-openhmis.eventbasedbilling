package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.List;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociator;

public class SimpleNewBillAssociator extends BaseOpenmrsMetadata implements IBillAssociator {
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Bill associateItemsToBill(List<BillLineItem> lineItems) {
		// TODO Auto-generated method stub
		return null;
	}
}
