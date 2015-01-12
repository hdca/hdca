Ext.Loader.setConfig({
	enabled : true,
	// disableCaching : true,
	paths : {
		'HdcaLib': '/hdca/js/admin/lib'
	}
});

// Ext.Msg.alert('Status title 111', 'I am an Ext.Msg.alert()');

Ext.application({
	name : 'Hdca',
	appFolder : '/hdca/js/admin/mvc',

	controllers : [ 'Main' ],

	autoCreateViewport : true
});