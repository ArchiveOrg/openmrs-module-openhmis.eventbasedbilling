package org.openmrs.module.openhmis.eventbasedbilling.api.util;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.openmrs.module.openhmis.eventbasedbilling.api.model.IBillAssociator;
import org.reflections.Reflections;

public class BillAssociatorHelper {

	private static volatile List<String> associatorTypeNames;
	
	public static List<Class<? extends IBillAssociator>> locateAssociators() {
		// Search for any modules that define classes which implement the IBillAssociator interface
		Reflections reflections = new Reflections("org.openmrs.module");
		List<Class<? extends IBillAssociator>> classes = new ArrayList<Class<? extends IBillAssociator>>();
		for (Class<? extends IBillAssociator> cls : reflections.getSubTypesOf(IBillAssociator.class)) {
			// We only care about public instantiable classes so ignore others
			if (!cls.isInterface() &&
					!Modifier.isAbstract(cls.getModifiers()) &&
					Modifier.isPublic(cls.getModifiers())) {
				classes.add(cls);
			}
		}		
		return classes;
	}

	public static List<String> getAssociatorTypeNames() {
		if (associatorTypeNames == null) {
			List<Class<? extends IBillAssociator>> classes = locateAssociators();
			associatorTypeNames = new ArrayList<String>(classes.size());
			for (Class<? extends IBillAssociator> cls : classes) {
				associatorTypeNames.add(cls.getSimpleName());
			}
		}
		return associatorTypeNames;
	}
}
