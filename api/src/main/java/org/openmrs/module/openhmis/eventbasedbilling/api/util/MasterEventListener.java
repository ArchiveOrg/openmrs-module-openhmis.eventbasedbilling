package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import javax.jms.Message;

import org.openmrs.event.EventListener;

public class MasterEventListener implements EventListener {

	@Override
	public void onMessage(Message arg0) {
		// Get all handlers... or get all handlers for the type?
		
		// Dispatch message to handler
	}

}
