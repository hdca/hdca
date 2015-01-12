Ext.define('Hdca.model.team.Team', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'teamid',
		type : 'string'
	}, {
		name : 'email',
		type : 'string'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'mobile',
		type : 'string'
	}, {
		name : 'cityname',
		type : 'string'
	}, {
		name : 'offertype',
		type : 'int'
	}, {
		name : 'contactname',
		type : 'string'
	}, 
	{
		name : 'districtnames',
		type : 'string'
	}, 
	{
		name : 'types',
		type : 'string'
	},
	{
		name : 'priceranges',
		type : 'string'
	},
	{
		name : 'basepackages',
		type : 'string'
	},
	{
		name : 'districtareas',
		type : 'string'
	},
	{
		name : 'contractdesc',
		type : 'string'
	}, 
	{
		name : 'address',
		type : 'string'
	}, 
	{
		name : 'extrapackage',
		type : 'string'
	}, 
	{
		name : 'adid',
		type : 'int'
	},
	{
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
			read : '/hdca/admin/team/read',
			create : '/hjmall/management/team/create',
			update : '/hjmall/management/team/update',
			destroy : '/hjmall/management/team/destroy'

		},
		actionMethods : {
			destroy : 'POST',
			read : 'GET',
			create : 'POST',
			update : 'POST'
		}

	}
});