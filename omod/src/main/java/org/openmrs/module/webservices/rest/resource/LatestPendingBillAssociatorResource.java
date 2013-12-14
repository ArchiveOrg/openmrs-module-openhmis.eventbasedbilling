package org.openmrs.module.webservices.rest.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.BaseBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.LatestPendingBillAssociator;
import org.openmrs.module.webservices.rest.web.EventBasedBillingRestConstants;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.AlreadyPaged;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingSubclassHandler;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;

@Resource(name = EventBasedBillingRestConstants.LATEST_PENDING_ASSOCIATOR_RESOURCE, supportedClass = LatestPendingBillAssociator.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
public class LatestPendingBillAssociatorResource extends BillAssociatorResource<BaseBillAssociator>
		implements DelegatingSubclassHandler<BaseBillAssociator, BaseBillAssociator> {

	@Override
	public PageableResult getAllByType(RequestContext context) throws ResourceDoesNotSupportOperationException {
		PagingInfo info = PagingUtil.getPagingInfoFromContext(context);
        return new AlreadyPaged<LatestPendingBillAssociator>(
                context,
                Context.getService(IBillAssociatorDataService.class).getAll(info, LatestPendingBillAssociator.class),
                info.hasMoreResults());
	}
	
	@Override
	public String getTypeName() {
		return LatestPendingBillAssociator.class.getSimpleName();
	}

	@Override
	public BaseBillAssociator newDelegate() {
		return new LatestPendingBillAssociator();
	}
}
