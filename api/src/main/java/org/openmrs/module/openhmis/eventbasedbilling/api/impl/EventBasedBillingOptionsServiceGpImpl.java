package org.openmrs.module.openhmis.eventbasedbilling.api.impl;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openmrs.GlobalProperty;
import org.openmrs.api.APIException;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillAssociatorDataService;
import org.openmrs.module.openhmis.eventbasedbilling.api.IEventBasedBillingOptionsService;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.EventBasedBillingOptions;
import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.EventHelper;
import org.openmrs.module.openhmis.eventbasedbilling.web.EventBasedBillingWebConstants;
import org.springframework.beans.factory.annotation.Autowired;

public class EventBasedBillingOptionsServiceGpImpl extends BaseOpenmrsService
		implements IEventBasedBillingOptionsService {
	private static final Logger logger = Logger.getLogger(EventBasedBillingOptionsServiceGpImpl.class);
	private AdministrationService adminService;
	private IBillAssociatorDataService associatorService;

	@Autowired
	public EventBasedBillingOptionsServiceGpImpl(AdministrationService adminService, IBillAssociatorDataService associatorService) {
		this.adminService = adminService;
		this.associatorService = associatorService;
	}

	@Override
	public EventBasedBillingOptions getOptions() {
		EventBasedBillingOptions options = new EventBasedBillingOptions();
		options.setBillAssociator(loadAssociator());
		options.setIsEnabled(loadIsEnabled());
		return options;
	}

	@Override
	public void saveOptions(EventBasedBillingOptions options) {
		GlobalProperty isEnabledProp = new GlobalProperty(
				EventBasedBillingWebConstants.ENABLED_PROPERTY,
				Boolean.toString(options.isEnabled())
		);
		
		// Bind or unbind listeners according to new enabled setting
		boolean isCurrentlyEnabled = loadIsEnabled();
		if (options.isEnabled() && !isCurrentlyEnabled)
			EventHelper.bindListenerForAllHandlers();
		else if (!options.isEnabled() && isCurrentlyEnabled)
			EventHelper.unbindListenerForAllHandlers();
		
		GlobalProperty associatorIdProp = new GlobalProperty(EventBasedBillingWebConstants.ASSOCIATOR_ID_PROPERTY);
		if (options.getBillAssociator() == null || options.getBillAssociator().getId() == null)
			associatorIdProp.setPropertyValue("");
		else
			associatorIdProp.setPropertyValue(options.getBillAssociator().getId().toString());
		
		adminService.saveGlobalProperties(Arrays.asList(isEnabledProp, associatorIdProp));
	}
	
	private IBillAssociator loadAssociator() {
		Integer associatorId;
		try {
			associatorId = Integer.parseInt(adminService.getGlobalProperty(EventBasedBillingWebConstants.ASSOCIATOR_ID_PROPERTY));
		}
		catch (Exception e) {
			return null;
		}
		
		IBillAssociator associator = null;
		try {
			 associator = associatorService.getById(associatorId);
		}
		catch (APIException e) {
			logger.error("Failed to load configured bill associator with ID " + associatorId + ".");
		}
		return associator;
	}
	
	private boolean loadIsEnabled() {
		boolean isEnabled = false;
		try {
			isEnabled = Boolean.parseBoolean(adminService.getGlobalProperty(EventBasedBillingWebConstants.ENABLED_PROPERTY));
		}
		catch (Exception e) {
			logger.warn("Failed to load/parse enabled property--maybe it isn't set.");
		}

		return isEnabled;
	}
}
