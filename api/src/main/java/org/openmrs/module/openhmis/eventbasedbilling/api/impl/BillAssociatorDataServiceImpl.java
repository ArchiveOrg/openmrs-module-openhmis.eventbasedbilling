package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.impl.BaseMetadataDataServiceImpl;
import org.openmrs.module.openhmis.commons.api.entity.security.IMetadataAuthorizationPrivileges;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.BaseBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;
import org.openmrs.module.openhmis.inventory.api.security.BasicMetadataAuthorizationPrivileges;
import org.reflections.Reflections;

public class BillAssociatorDataServiceImpl extends BaseMetadataDataServiceImpl<BaseBillAssociator>
	implements IBillAssociatorDataService {

	private static List<String> associatorTypeNames;

	@Override
	protected IMetadataAuthorizationPrivileges getPrivileges() {
		return new BasicMetadataAuthorizationPrivileges();
	}

	@Override
	protected void validate(BaseBillAssociator object) throws APIException {

	}
	
	@Override
	public <T extends IBillAssociator> List<T> getAll(PagingInfo pagingInfo, Class<T> clazz) {
		IMetadataAuthorizationPrivileges privileges = getPrivileges();
		if (privileges != null && !StringUtils.isEmpty(privileges.getGetPrivilege())) {
			Context.requirePrivilege(privileges.getGetPrivilege());
		}

		loadPagingTotal(pagingInfo, repository.createCriteria(clazz));

		return executeCriteria(clazz, pagingInfo, null, getDefaultSort());
	}
	
	@Override
	public List<Class<? extends IBillAssociator>> locateAssociators() {
		// Search for any modules that define classes which implement the IBillAssociator interface
		Reflections reflections = new Reflections("org.openmrs.module");
		List<Class<? extends IBillAssociator>> classes = new ArrayList<Class<? extends IBillAssociator>>();
		for (Class<? extends IBillAssociator> cls : reflections.getSubTypesOf(IBillAssociator.class)) {
			// We only care about public instantiable classes so ignore others
			if (!cls.isInterface() &&
					!Modifier.isAbstract(cls.getModifiers()) &&
					Modifier.isPublic(cls.getModifiers())) {
				classes.add(cls);
			}
		}		
		return classes;
	}

	@Override
	public List<String> getAssociatorClassNames() {
		if (associatorTypeNames == null) {
			List<Class<? extends IBillAssociator>> classes = locateAssociators();
			associatorTypeNames = new ArrayList<String>(classes.size());
			for (Class<? extends IBillAssociator> cls : classes)
				associatorTypeNames.add(cls.getSimpleName());
			Collections.sort(associatorTypeNames);
		}
		return associatorTypeNames;
	}
}