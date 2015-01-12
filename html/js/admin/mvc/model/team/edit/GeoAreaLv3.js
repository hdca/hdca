Ext.define('Hdca.model.team.edit.GeoAreaLv3', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ {
		name : 'areaid',
		type : 'int'
	}, {
		name : 'parentid',
		type : 'int'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'type',
		type : 'int'
	}],
	proxy : {
		type : 'ajax',
		batchActions : true,
		extraParams : {
			parentid : null
		},
		reader : {
			root : 'data',
			totalProperty : 'totalCount',
			type : 'json'
		},
		api : {
			read : '/hdca/admin/team/attribute/geoAreaLv3Read',
			create : '/hjmall/management/geoarea/create',
			update : '/hjmall/management/geoarea/update',
			destroy : '/hjmall/management/geoarea/destroy'

		},
		actionMethods : {
			destroy : 'POST',
			read : 'GET',
			create : 'POST',
			update : 'POST'
		}

	}
});