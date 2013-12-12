/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.openhmis.eventbasedbilling;


import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.DaemonTokenAware;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.openhmis.eventbasedbilling.api.BillingHandlerEventListenerFactory;
import org.openmrs.module.openhmis.eventbasedbilling.api.IEventBasedBillingOptionsService;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.EventHelper;

/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class EventBasedBillingActivator implements ModuleActivator, DaemonTokenAware {
	
	protected Log log = LogFactory.getLog(getClass());
		
	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing Event Based Billing Module");
	}
	
	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("Event Based Billing Module refreshed");
	}
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting Event Based Billing Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		log.info("Event Based Billing Module started");
		if (Context.getService(IEventBasedBillingOptionsService.class).getOptions().isEnabled())
			EventHelper.bindListenerForAllHandlers();
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping Event Based Billing Module");
		EventHelper.unbindListenerForAllHandlers();
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		log.info("Event Based Billing Module stopped");
	}

	@Override
	public void setDaemonToken(DaemonToken token) {
		BillingHandlerEventListenerFactory.setDaemonToken(token);
	}
		
}
