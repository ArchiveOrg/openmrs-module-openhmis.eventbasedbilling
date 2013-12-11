package org.openmrs.module.openhmis.eventbasedbilling.api.model;

public class EventBasedBillingOptions {
	private boolean enabled = false;
	private IBillAssociator billAssociator;
	
	
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
	
}
