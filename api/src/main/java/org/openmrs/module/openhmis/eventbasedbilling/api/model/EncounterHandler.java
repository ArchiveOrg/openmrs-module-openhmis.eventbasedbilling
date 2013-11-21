package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.log4j.Logger;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.cashier.api.model.Bill;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.BillsFor;
import org.openmrs.module.openhmis.inventory.api.model.Item;

@BillsFor({ Encounter.class })
public class EncounterHandler extends BaseBillingHandler {
	private static final Logger logger = Logger.getLogger(EncounterHandler.class);
	
	private Integer encounterHandlerId;
	private EncounterType encounterType;
	private Set<Item> associatedItems;
	
	@Override
	public Bill handleEvent(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			Encounter encounter = Context.getEncounterService().getEncounterByUuid(mapMessage.getString("uuid"));
			if (encounter.getEncounterType().getUuid().equals(getEncounterType().getUuid())) {
				Bill lineItems = new Bill();
				lineItems.setPatient(encounter.getPatient());
				// TODO: Fix hard-coded provider
				lineItems.setCashier(Context.getProviderService().getProvider(3));
				for (Item item : getAssociatedItems()) {
					BillLineItem lineItem = new BillLineItem();
					lineItem.setItem(item);
					lineItem.setPrice(item.getDefaultPrice().getPrice());
					lineItem.setQuantity(1);
					lineItems.addLineItem(lineItem);
				}
				return lineItems;
			}
		}
		catch (JMSException e) {
			logger.error("Error reading message.");
		}
		return null;
	}
		
	/** Getters & setters **/
	@Override
	public Integer getId() {
		return encounterHandlerId;
	}

	@Override
	public void setId(Integer id) {
		encounterHandlerId = id;
	}

	public EncounterType getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(EncounterType encounterType) {
		this.encounterType = encounterType;
	}

	public Set<Item> getAssociatedItems() {
		return associatedItems;
	}

	public void setAssociatedItems(Set<Item> associatedItems) {
		this.associatedItems = associatedItems;
	}

}