package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.openhmis.commons.api.entity.impl.BaseMetadataDataServiceImpl;
import org.openmrs.module.openhmis.commons.api.entity.security.IMetadataAuthorizationPrivileges;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillingHandler;

public class BillingHandlerDataServiceImpl extends BaseMetadataDataServiceImpl<IBillingHandler> {

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
