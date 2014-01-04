package org.openmrs.module.openhmis.eventbasedbilling.api.security;

import org.openmrs.module.openhmis.commons.api.entity.security.IMetadataAuthorizationPrivileges;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.PrivilegeConstants;

public class BasicMetadataAuthorizationPrivileges implements
		IMetadataAuthorizationPrivileges {

	@Override
	public String getSavePrivilege() {
		return PrivilegeConstants.MANAGE;
	}

	@Override
	public String getPurgePrivilege() {
		return PrivilegeConstants.PURGE;
	}

	@Override
	public String getGetPrivilege() {
		return PrivilegeConstants.VIEW;
	}

	@Override
	public String getRetirePrivilege() {
		return PrivilegeConstants.RETIRE;
	}

}
