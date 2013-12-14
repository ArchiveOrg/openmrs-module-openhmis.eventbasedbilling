package org.openmrs.module.webservices.rest.resource;

import org.apache.commons.lang.NotImplementedException;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.BaseBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;
import org.openmrs.module.webservices.rest.web.EventBasedBillingRestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;

@Resource(name = EventBasedBillingRestConstants.ASSOCIATOR_RESOURCE, supportedClass = IBillAssociator.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
public class BillAssociatorResource<T extends IBillAssociator> extends BaseRestMetadataResource<BaseBillAssociator> {
	private static final String NEED_SUBCLASS_HANDLER = "This operation should be handled by a subclass handler.";

	@Override
	public Class<? extends IMetadataDataService<BaseBillAssociator>> getServiceClass() {
		return IBillAssociatorDataService.class;
	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = super.getRepresentationDescription(rep);
		description.addProperty("type");
		return description;
	}

	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = super.getCreatableProperties();
		description.addProperty("type");
		return description;
	}

	@Override
	public BaseBillAssociator newDelegate() {
		throw new NotImplementedException(NEED_SUBCLASS_HANDLER);
	}
	
	// Default for implementing subclass resources
	public Class<BaseBillAssociator> getSuperclass() {
		return BaseBillAssociator.class;
	}

	// Default for implementing subclass resources
	public Class<BaseBillAssociator> getSubclassHandled() {
		return BaseBillAssociator.class;
	}
	
	@PropertySetter("type")
	public void setHandlerType(T instance, String type) {
		// Allow this property to be silently ignored
	}
	
	@PropertyGetter("type")
	public String getHandlerType(T instance) {
		return instance.getClass().getSimpleName();
	}
}
