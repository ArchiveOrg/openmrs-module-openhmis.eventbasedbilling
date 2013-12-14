package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.billableobjects.api.IBillableObjectDataService;
import org.openmrs.module.openhmis.billableobjects.api.model.IBillableObject;
import org.openmrs.module.openhmis.billableobjects.api.type.BaseBillableObject;
import org.openmrs.module.openhmis.cashier.api.IBillService;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.cashier.api.model.BillStatus;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociationContext;

public class SimpleNewBillAssociator extends BaseBillAssociator {
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
	public Bill associateItemsToBill(List<BillLineItem> lineItems, IBillAssociationContext context) {
		Bill bill = new Bill();
		bill.setStatus(BillStatus.PENDING);

		addLineItemsToBill(lineItems, bill);
		
		bill.setPatient(context.getPatient());
		bill.setCashier(context.getProvider());
		bill.setCashPoint(context.getCashPoint());
		bill.setCashier(context.getProvider());

		Context.getService(IBillService.class).save(bill);
		
		IBillableObject<?> billableObject = context.getBillableObject();
		billableObject.setBill(bill);
		Context.getService(IBillableObjectDataService.class).save((BaseBillableObject<?>) billableObject);

		return bill;
	}
}
