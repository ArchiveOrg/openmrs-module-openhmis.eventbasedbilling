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
                restUrl: 'v2/eventbasedbilling/associators'
            },

            schema: {
            	type: {
                	type: 'BillAssociatorTypeSelect',
                	options: new openhmis.GenericCollection([], { model: openhmis.BillAssociatorType }),
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
        
        openhmis.SimpleNewBillAssociator = openhmis.BillAssociator.extend({
            meta: {
                name: __("Simple New Bill Associator"),
                openmrsType: 'metadata',
                restUrl: 'v2/eventbasedbilling/simplenewbillassociators'
            },
            
            initialize: function(attrs, options) {
            	this.set("type", "SimpleNewBillAssociator", { silent: true });
            }
        });

        openhmis.LatestPendingBillAssociator = openhmis.BillAssociator.extend({
            meta: {
                name: __("Latest Pending Bill Associator"),
                openmrsType: 'metadata',
                restUrl: 'v2/eventbasedbilling/latestpendingbillassociators'
            },
            
            initialize: function(attrs, options) {
            	this.set("type", "LatestPendingBillAssociator", { silent: true });
            }
        });

        return openhmis;
    }
);