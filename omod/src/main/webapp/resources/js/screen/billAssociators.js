curl(
    { baseUrl: openhmis.url.resources },
    [
        openhmis.url.backboneBase + 'js/lib/jquery',
        openhmis.url.backboneBase + 'js/openhmis',
        openhmis.url.backboneBase + 'js/view/generic',
        openhmis.url.billableobjBase + 'js/model/generic',
        openhmis.url.billableobjBase + 'js/view/generic',
        openhmis.url.eventbillBase + 'js/model/billAssociator',
        openhmis.url.eventbillBase + 'js/view/editors' // for BillAssociatorTypeSelect
    ],
    function($, openhmis) {
    	var screen = new openhmis.MultiTypeAddEditScreen({
    		modelType: openhmis.BillAssociator,
    		collection: new openhmis.InheritanceCollection(null, { model: openhmis.BillAssociator }),
    		addEditViewType: openhmis.MultiTypeAddEditView,
    		listFields: ["name", "type"]
    	});
    	$("#content").append(screen.render().el);
    }

);