package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import org.openmrs.module.openhmis.cashier.api.IReceiptNumberGenerator;
import org.openmrs.module.openhmis.cashier.api.model.Bill;

public class DummyReceiptNumberGenerator implements IReceiptNumberGenerator {

	@Override
	public String getName() {
		return "Dummy";
	}

	@Override
	public String getDescription() {
		return "Dummy receipt number generator";
	}

	@Override
	public String getConfigurationPage() {
		return null;
	}

	@Override
	public boolean isLoaded() {
		return false;
	}

	@Override
	public void load() {
	}

	@Override
	public String generateNumber(Bill bill) {
		return "fake";
	}

}
