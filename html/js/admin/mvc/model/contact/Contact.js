Ext.define('Hdca.model.contact.Contact', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'type',
		type : 'int'
	}, {
		name : 'customername',
		type : 'string'
	}, {
		name : 'teamname',
		type : 'string'
	}, {
		name : 'request',
		type : 'string'
	} ],
	proxy : {
		type : 'ajax',
		batchActions : true,
		extraParams : {
			putawayid : null
		},
		reader : {
			root : 'data',
			totalProperty : 'totalCount',
			type : 'json'
		},
		api : {
			read : '/hdca/admin/servicerequest/read',
			create : '/hjmall/management/contact/create',
			update : '/hjmall/management/contact/update',
			destroy : '/hjmall/management/contact/destroy'

		},
		actionMethods : {
			destroy : 'POST',
			read : 'GET',
			create : 'POST',
			update : 'POST'
		}

	}
});