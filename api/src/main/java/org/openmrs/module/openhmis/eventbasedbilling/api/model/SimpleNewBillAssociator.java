package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.cashier.api.IBillService;
import org.openmrs.module.openhmis.cashier.api.ICashPointService;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
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
		bill.setPatient(context.getPatient());
		bill.setLineItems(lineItems);
		bill.setCashier(context.getProvider());
		// TODO: Fix auto cash point lookup
		bill.setCashPoint(Context.getService(ICashPointService.class).getAll().get(0));
		Context.getService(IBillService.class).save(bill);
		return bill;
	}
}
