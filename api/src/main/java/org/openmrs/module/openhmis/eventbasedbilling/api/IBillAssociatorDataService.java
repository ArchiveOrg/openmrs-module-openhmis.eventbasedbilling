package org.openmrs.module.openhmis.eventbasedbilling.api;

import java.util.List;

import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.BaseBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;

public interface IBillAssociatorDataService extends IMetadataDataService<BaseBillAssociator> {
	/**
	 * Get all associator records of the specified class
	 * 
	 * @param pagingInfo Paging information object
	 * @param clazz Return objects of this class
	 * @return list of all bill associators
	 */
	public <T extends IBillAssociator> List<T> getAll(PagingInfo pagingInfo, Class<T> clazz);
	
	/**
	 * Search for any class implementing IBillAssociator and return results
	 * in a list.
	 * 
	 * @return list of IBillAssociator classes
	 */
	List<Class<? extends IBillAssociator>> locateAssociators();
	
	/**
	 * Get the list of implemented IBillAssociator classes in alphabetical
	 * order. 
	 * 
	 * @return list of implemented IBillAssociators
	 * @should return the list of class names in alphabetical order
	 */
	List<String> getAssociatorClassNames();
}
