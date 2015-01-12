Ext.define('Hdca.model.customer.Customer', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'nickname',
		type : 'string'
	}, {
		name : 'password',
		type : 'string'
	}, {
		name : 'email',
		type : 'string'
	}, {
		name : 'comment',
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
			read : '/hdca/admin/customer/read',
			create : '/hjmall/management/customer/create',
			update : '/hjmall/management/customer/update',
			destroy : '/hjmall/management/customer/destroy'

		},
		actionMethods : {
			destroy : 'POST',
			read : 'GET',
			create : 'POST',
			update : 'POST'
		}

	}
});