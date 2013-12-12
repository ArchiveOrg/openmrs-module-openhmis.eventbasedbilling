package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import org.openmrs.module.openhmis.cashier.api.model.CashPoint;

public class EventBasedBillingOptions {
	private boolean enabled = false;
	private IBillAssociator billAssociator;
	private CashPoint cashPoint;
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setIsEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public IBillAssociator getBillAssociator() {
		return billAssociator;
	}
	public void setBillAssociator(IBillAssociator billAssociator) {
		this.billAssociator = billAssociator;
	}
	public CashPoint getCashPoint() {
		return cashPoint;
	}
	public void setCashPoint(CashPoint cashPoint) {
		this.cashPoint = cashPoint;
	}
	
}
