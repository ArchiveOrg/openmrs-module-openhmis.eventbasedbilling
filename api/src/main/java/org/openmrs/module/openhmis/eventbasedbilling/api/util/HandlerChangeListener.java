package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.Daemon;
import org.openmrs.event.EventListener;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.openhmis.eventbasedbilling.api.IBillingEventService;

public class HandlerChangeListener implements EventListener, Runnable {
	private String lastMessageHandled;
	private DaemonToken daemonToken;
	
	public HandlerChangeListener(DaemonToken token) {
		if (token == null)
			throw new APIException("Daemon token cannot be null.");
		this.daemonToken = token;
	}
	
	@Override
	public void onMessage(Message message) {
		// For some reason the way I've bound the events causes the message to be
		// dispatched to the handler multiple times.  I only want to handle it once.
		String messageId;
		try {
			messageId = ((MapMessage) message).getJMSMessageID();
		} catch (JMSException e) {
			messageId = null;
		}
		if (messageId != null && messageId.equals(lastMessageHandled))
			return;
		lastMessageHandled = messageId;
		
		Daemon.runInDaemonThread(this, this.daemonToken);
	}
	
	@Override
	public void run() {
		Context.getService(IBillingEventService.class).rebindListenerForAllHandlers();
	}
}
