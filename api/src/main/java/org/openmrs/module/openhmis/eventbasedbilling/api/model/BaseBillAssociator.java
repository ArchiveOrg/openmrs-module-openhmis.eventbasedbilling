package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.List;

import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.commons.api.entity.model.BaseSerializableOpenmrsMetadata;

public abstract class BaseBillAssociator extends BaseSerializableOpenmrsMetadata implements IBillAssociator {
	protected void addLineItemsToBill(List<BillLineItem> lineItems, Bill bill) {
		List<BillLineItem> existingItems = bill.getLineItems();
		Integer itemOrder;
		if (existingItems == null)
			itemOrder = 0;
		else
			itemOrder = existingItems.size();
			
		for (BillLineItem lineItem : lineItems) {
			lineItem.setLineItemOrder(itemOrder++);
			bill.addLineItem(lineItem);
		}
	}
}
