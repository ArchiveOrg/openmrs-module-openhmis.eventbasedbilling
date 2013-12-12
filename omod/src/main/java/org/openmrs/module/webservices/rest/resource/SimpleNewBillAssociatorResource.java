package org.openmrs.module.webservices.rest.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.BaseBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.SimpleNewBillAssociator;
import org.openmrs.module.webservices.rest.web.EventBasedBillingRestConstants;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.AlreadyPaged;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingSubclassHandler;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;

@Resource(name = EventBasedBillingRestConstants.SIMPLE_NEW_ASSOCIATOR_RESOURCE, supportedClass = SimpleNewBillAssociator.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
public class SimpleNewBillAssociatorResource extends BillAssociatorResource<BaseBillAssociator> implements
		DelegatingSubclassHandler<BaseBillAssociator, BaseBillAssociator> {

	@Override
	public Class<? extends IMetadataDataService<BaseBillAssociator>> getServiceClass() {
		return IBillAssociatorDataService.class;
	}
	
	@Override
	public PageableResult getAllByType(RequestContext context) throws ResourceDoesNotSupportOperationException {
		PagingInfo info = PagingUtil.getPagingInfoFromContext(context);
        return new AlreadyPaged<SimpleNewBillAssociator>(
                context,
                Context.getService(IBillAssociatorDataService.class).getAll(info, SimpleNewBillAssociator.class),
                info.hasMoreResults());
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = super.getRepresentationDescription(rep);
		return description;
	}

	@Override
	public String getTypeName() {
		return SimpleNewBillAssociator.class.getSimpleName();
	}

	@Override
	public Class<BaseBillAssociator> getSuperclass() {
		return BaseBillAssociator.class;
	}

	@Override
	public Class<BaseBillAssociator> getSubclassHandled() {
		return BaseBillAssociator.class;
	}

	@Override
	public BaseBillAssociator newDelegate() {
		return new SimpleNewBillAssociator();
	}

}
