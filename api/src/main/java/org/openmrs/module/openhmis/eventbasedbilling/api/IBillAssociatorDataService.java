package org.openmrs.module.openhmis.eventbasedbilling.api;

import java.util.List;

import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.BaseBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;

public interface IBillAssociatorDataService extends IMetadataDataService<BaseBillAssociator> {
	public <T extends IBillAssociator> List<T> getAll(PagingInfo pagingInfo, Class<T> clazz);
}
