define(
	[
		openhmis.url.backboneBase + 'js/lib/jquery',
		openhmis.url.backboneBase + 'js/lib/backbone',
		openhmis.url.backboneBase + 'js/lib/underscore',
		openhmis.url.backboneBase + 'js/model/generic',
		openhmis.url.backboneBase + 'js/lib/backbone-forms',
		openhmis.url.backboneBase + 'js/view/editors',
		openhmis.url.eventbillBase + 'js/model/billAssociator'
	],
	function($, Backbone, _, openhmis) { 
		var editors = Backbone.Form.editors;
	
		editors.BillAssociatorTypeSelect = editors.GenericModelSelect.extend({
			modelType: openhmis.BillAssociatorType,
			displayAttr: "name",
			allowNull: true
		});
		
		return editors;
	}
);