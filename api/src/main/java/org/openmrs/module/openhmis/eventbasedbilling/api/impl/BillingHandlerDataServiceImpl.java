package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.openhmis.commons.api.entity.impl.BaseMetadataDataServiceImpl;
import org.openmrs.module.openhmis.commons.api.entity.security.IMetadataAuthorizationPrivileges;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillingHandler;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillingHandlerDataService;

public class BillingHandlerDataServiceImpl extends BaseMetadataDataServiceImpl<IBillingHandler>
	implements IBillingHandlerDataService {

	@Override
	protected IMetadataAuthorizationPrivileges getPrivileges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void validate(IBillingHandler object) throws APIException {
		// TODO Auto-generated method stub
		
	}

}
