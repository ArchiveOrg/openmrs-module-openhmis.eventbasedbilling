define(
    [
        openhmis.url.backboneBase + 'js/openhmis',
        openhmis.url.backboneBase + 'js/lib/i18n',
        openhmis.url.backboneBase + 'js/model/generic'
    ],
    function(openhmis, __) {
    	openhmis.BillAssociatorType = openhmis.GenericModel.extend({
        	urlRoot: openhmis.url.page + openhmis.url.eventbillBase + "associatortypes.json",
        	
        	parse: function(resp) {
        		return { uuid: resp, name: resp };
        	},
        	
        	toString: function() {
        		return this.get("name");
        	}
        	
        });
        
        openhmis.BillAssociator = openhmis.GenericModel.extend({
            meta: {
                name: __("Bill Associator"),
                openmrsType: 'metadata',
                restUrl: 'v2/eventbasedbilling/billassociators'
            },

            schema: {
            	type: {
                	type: 'BillAssociatorTypeSelect',
                	options: new openhmis.GenericCollection([], { model: openhmis.BillingHandlerType }),
                	objRef: true
                },
                name: 'Text',
                description: 'Text'
            },
            
            validate: function(attrs, options) {
    			if (!attrs.name) return { name: __("A name is required") };
                return null;
            },

            toString: function() {
                return this.get('name');
            }
        });

        return openhmis;
    }
);