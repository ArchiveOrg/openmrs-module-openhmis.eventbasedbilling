package org.openmrs.module.openhmis.eventbasedbilling.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.openhmis.eventbasedbilling.api.impl.BillAssociatorServiceImpl;
import org.openmrs.module.openhmis.eventbasedbilling.web.EventBasedBillingWebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = EventBasedBillingWebConstants.ASSOCIATOR_TYPES_LOCATION)
public class BillAssociatorTypesController {
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> typeNames() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("results", BillAssociatorServiceImpl.getAssociatorClassNames());
		return result;
	}
}
