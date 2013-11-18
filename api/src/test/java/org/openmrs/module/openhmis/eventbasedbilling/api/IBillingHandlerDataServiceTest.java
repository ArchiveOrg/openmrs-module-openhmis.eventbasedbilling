package org.openmrs.module.openhmis.eventbasedbilling.api;

import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataServiceTest;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.EncounterHandler;

public class IBillingHandlerDataServiceTest extends IMetadataDataServiceTest<IBillingHandlerDataService, IBillingHandler> {
	public static final String HANDLERS_DATASET = TestConstants.BASE_DATASET_DIR + "HandlersTest.xml";
	public static final String INVENTORY_DATASET = TestConstants.BASE_DATASET_DIR + "InvTest.xml";

	@Override
	public void before() throws Exception{
		super.before();
		executeDataSet(TestConstants.CORE_DATASET);
		executeDataSet(HANDLERS_DATASET);
		executeDataSet(INVENTORY_DATASET);
	}

	@Override
	protected IBillingHandler createEntity(boolean valid) {
		EncounterHandler handler = new EncounterHandler();
		if (valid) handler.setName("Test Encounter Handler");
		handler.setBillAssociator(Context.getService(IBillAssociatorDataService.class).getById(0));
		handler.setEncounterType(Context.getEncounterService().getEncounterType(1));
		return handler;
	}

	@Override
	protected int getTestEntityCount() {
		return 1;
	}

	@Override
	protected void updateEntityFields(IBillingHandler entity) {
		entity.setName("Another Handler");
	}
}
