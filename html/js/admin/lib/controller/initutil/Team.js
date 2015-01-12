Ext.define('HdcaLib.controller.initutil.Team', {
	control : function(controller) {
		controller.control({
			'mainmenu > button#bt_teammgmt' : {
				click : function() {
					// load store
					// var ccstore = Ext.StoreManager
					// .lookup('CommodityCategories');

					// load grid view

					// alert('施工队管理');
					var vp = Ext.ComponentQuery.query('hdcaviewport')[0];
					var p = Ext.ComponentQuery.query('mainpanel')[0];

					p.removeAll();
					p.add({
						xtype : 'teamgrid'
					});
					p.doLayout();

					var g = Ext.ComponentQuery.query('teamgrid')[0];
					g.getStore().load();
				}
			}
		});// control end

		controller.control({
			'teamgrid > toolbar > button#teamgrid_tbbt_add' : {
				click : function() {
					var g = Ext.ComponentQuery.query('teamgrid')[0];
					var win = Ext.widget('team.edit.window', {
						title : '添加施工队',
						teamaction : 'create'
					// warehousecode : g.warehousecode
					});
					// show window
					win.show();
				}
			}
		});// control end
	}
});