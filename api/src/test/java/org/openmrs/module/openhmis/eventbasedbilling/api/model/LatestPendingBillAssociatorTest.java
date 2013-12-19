package org.openmrs.module.openhmis.eventbasedbilling.api.model;


import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.billableobjects.api.IBillableObjectDataService;
import org.openmrs.module.openhmis.cashier.api.ICashPointService;
import org.openmrs.module.openhmis.cashier.api.ReceiptNumberGeneratorFactory;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.eventbasedbilling.api.TestConstants;
import org.openmrs.module.openhmis.eventbasedbilling.api.impl.BillAssociationContext;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.DummyReceiptNumberGenerator;
import org.openmrs.module.openhmis.inventory.api.IItemDataService;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;

@PrepareForTest(ReceiptNumberGeneratorFactory.class)
public class LatestPendingBillAssociatorTest extends BaseModuleWebContextSensitiveTest {
	@Rule
	public PowerMockRule rule = new PowerMockRule();
	static {
		PowerMockAgent.initializeIfNeeded();
	}
	
	@Before
	public void before() throws Exception {
		executeDataSet(TestConstants.CORE_DATASET);
	}
	
	/**
	 * @see LatestPendingBillAssociator#associateItemsToBill(List,IBillAssociationContext)
	 * @verifies use the latest pending bill for the specified patient
	 */
	@Test
	public void associateItemsToBill_shouldUseTheLatestPendingBillForTheSpecifiedPatient() throws Exception {
		executeDataSet(TestConstants.BILL_DATASET);
		executeDataSet(TestConstants.CASHPOINT_DATASET);
		executeDataSet(TestConstants.DEPARTMENT_DATASET);
		executeDataSet(TestConstants.ITEM_DATASET);
		executeDataSet(TestConstants.ENCOUNTER_DATASET);
		executeDataSet(TestConstants.HANDLER_DATASET);
		executeDataSet(TestConstants.BILLABLEOBJ_DATASET);
		
		// Mock ReceiptNumberGeneratorFactory so that we can generate a test
		// receipt number
		mockStatic(ReceiptNumberGeneratorFactory.class);
		when(ReceiptNumberGeneratorFactory.getGenerator())
				.thenReturn(new DummyReceiptNumberGenerator());

		LatestPendingBillAssociator associator = new LatestPendingBillAssociator();
		List<BillLineItem> lineItems = new ArrayList<BillLineItem>(1);
		BillLineItem item = new BillLineItem();
		item.setItem(Context.getService(IItemDataService.class).getById(0));
		item.setQuantity(1);
		item.setPrice(item.getItem().getDefaultPrice().getPrice());
		lineItems.add(item);
		
		BillAssociationContext context = new BillAssociationContext(
			Context.getPatientService().getPatient(0),
			Context.getProviderService().getProvider(0),
			Context.getService(ICashPointService.class).getById(0),
			Context.getService(IBillableObjectDataService.class).getById(0)
		);
		
		Bill bill = associator.associateItemsToBill(lineItems, context);
		Assert.assertEquals(new Integer(0), bill.getId());
	}
}