Ext.define('Hdca.store.team.edit.GeoAreasLv1', {
	extend : 'Ext.data.Store',
	sortOnLoad : true,
	sorters : {
		property : 'id',
		direction : 'ASC'
	},

	model : 'Hdca.model.team.edit.GeoAreaLv1'
// storeId: 'Commodity',
// autoLoad: false
});