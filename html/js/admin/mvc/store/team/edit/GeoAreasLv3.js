Ext.define('Hdca.store.team.edit.GeoAreasLv3', {
	extend : 'Ext.data.Store',
	sortOnLoad : true,
	sorters : {
		property : 'id',
		direction : 'ASC'
	},

	listeners : {
		load : function(store, records, options) {
			// alert('loaded, records='+records);

			var ida = Ext.ComponentQuery
					.query("*[id=teamformcbf_districtareas]")[0];
			ida.removeAll(true);
			// alert("ida="+ida);
			// alert("items len="+data.items.length);
			// alert("rec all ="+stGaLv3.data+"st count="+stGaLv3.data.length);

			for (var i = 0; i < records.length; i++) {
				// alert("i="+i);
				ida.add({
					boxLabel : records[i].get('name'),
					name : 'districtareas',
					inputValue : records[i].get('areaid'),
					id : 'teamchb_districtareas_' + records[i].get('areaid')
				});
			}
		}
	},

	model : 'Hdca.model.team.edit.GeoAreaLv3'
// storeId: 'Commodity',
// autoLoad: false
});