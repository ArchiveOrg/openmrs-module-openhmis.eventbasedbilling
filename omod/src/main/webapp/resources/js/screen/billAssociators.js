curl(
    { baseUrl: openhmis.url.resources },
    [
        openhmis.url.backboneBase + 'js/lib/jquery',
        openhmis.url.backboneBase + 'js/openhmis',
        openhmis.url.backboneBase + 'js/view/generic',
        openhmis.url.billableobjBase + 'js/model/billAssociator',
    ],
    function($, openhmis) {
        $(function() {
            openhmis.startAddEditScreen(openhmis.BillAssociator, {
                listFields: ['name', 'description']
            });
        });
    }
);