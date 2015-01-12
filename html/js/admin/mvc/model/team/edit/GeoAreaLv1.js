Ext.define('Hdca.model.team.edit.GeoAreaLv1', {
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
			type : null
		},
		reader : {
			root : 'data',
			totalProperty : 'totalCount',
			type : 'json'
		},
		api : {
			read : '/hdca/admin/team/attribute/geoAreaLv1Read',
			create : '/hdca/management/geoarea/create',
			update : '/hdca/management/geoarea/update',
			destroy : '/hdca/management/geoarea/destroy'

		},
		actionMethods : {
			destroy : 'POST',
			read : 'GET',
			create : 'POST',
			update : 'POST'
		}

	}
});