package org.openmrs.module.openhmis.eventbasedbilling.api.model;

import java.util.List;
import java.util.Set;

import javax.jms.Message;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.module.openhmis.cashier.api.model.BillLineItem;
import org.openmrs.module.openhmis.eventbasedbilling.api.util.BillsFor;
import org.openmrs.module.openhmis.inventory.api.model.Item;

@BillsFor({ Encounter.class })
public class EncounterHandler extends BaseBillingHandler {

	private Integer encounterHandlerId;
	private EncounterType encounterType;
	private Set<Item> associatedItems;
	
	@Override
	public List<BillLineItem> handleEvent(Message message) {
		// TODO Auto-generated method stub
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
