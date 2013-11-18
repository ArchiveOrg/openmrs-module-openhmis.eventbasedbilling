package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.openhmis.commons.api.entity.impl.BaseMetadataDataServiceImpl;
import org.openmrs.module.openhmis.commons.api.entity.security.IMetadataAuthorizationPrivileges;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;

public class BillAssociatorDataServiceImpl extends BaseMetadataDataServiceImpl<IBillAssociator>
	implements IBillAssociatorDataService {

	@Override
	protected IMetadataAuthorizationPrivileges getPrivileges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void validate(IBillAssociator object) throws APIException {
		// TODO Auto-generated method stub		
	}
}
