Ext.define('Hdca.store.team.edit.GeoAreasLv2', {
	extend : 'Ext.data.Store',
	sortOnLoad : true,
	sorters : {
		property : 'id',
		direction : 'ASC'
	},


	model : 'Hdca.model.team.edit.GeoAreaLv2'
// storeId: 'Commodity',
// autoLoad: false
});