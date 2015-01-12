Ext.define('Hdca.store.team.Teams', {
	extend : 'Ext.data.Store',
	sortOnLoad : true,
	sorters : {
		property : 'id',
		direction : 'ASC'
	},
	// data : [ [ '3m Co', 71.72, 0.02, 0.03, '9/1 12:00am' ] ],
	model : 'Hdca.model.team.Team'
});