package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.cashier.api.IBillService;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.cashier.api.model.BillStatus;
import org.openmrs.module.openhmis.cashier.api.search.BillSearch;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociationContext;

public class LatestPendingBillAssociator extends BaseBillAssociator {
	Integer id;

	@Override
	public Bill associateItemsToBill(List<BillLineItem> lineItems, IBillAssociationContext context) {
		BillSearch search = new BillSearch();
		search.getTemplate().setStatus(BillStatus.PENDING);
		List<Bill> pendingBills = Context.getService(IBillService.class).findBills(search);
		
		if (pendingBills.size() > 0) {
			Bill bill = null;
			if (pendingBills.size() > 1) {
				Collections.sort(pendingBills, new Comparator<Bill>() {
					@Override
					public int compare(Bill bill1, Bill bill2) {
						return bill1.getDateCreated().compareTo(bill2.getDateCreated()); 
					}
				});
				Collections.reverse(pendingBills);
			}
			bill = pendingBills.get(0);
			addLineItemsToBill(lineItems, bill);
			Context.getService(IBillService.class).save(bill);
			return bill;
		}		
		else {
			SimpleNewBillAssociator newBillAssociator = new SimpleNewBillAssociator();
			return newBillAssociator.associateItemsToBill(lineItems, context);
		}
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
