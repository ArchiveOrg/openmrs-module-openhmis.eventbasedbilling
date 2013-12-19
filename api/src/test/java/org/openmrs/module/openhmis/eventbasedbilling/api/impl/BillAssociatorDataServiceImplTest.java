package org.openmrs.module.openhmis.eventbasedbilling.api.impl;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.TestConstants;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.LatestPendingBillAssociator;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

public class BillAssociatorDataServiceImplTest extends BaseModuleWebContextSensitiveTest {
	private IBillAssociatorDataService associatorService;
	
	@Before
	public void before() throws Exception {
		associatorService = Context.getService(IBillAssociatorDataService.class);
		
		executeDataSet(TestConstants.CORE_DATASET);
		executeDataSet(TestConstants.ASSOCIATOR_DATASET);
	}
	/**
	 * @see IBillAssociatorDataService#getAssociatorClassNames()
	 * @verifies return the list of class names in alphabetical order
	 */
	@Test
	public void getAssociatorClassNames_shouldReturnTheListOfClassNamesInAlphabeticalOrder() throws Exception {
		List<String> names = associatorService.getAssociatorClassNames();
		Assert.assertEquals(LatestPendingBillAssociator.class.getSimpleName(), names.get(0));
	}

}