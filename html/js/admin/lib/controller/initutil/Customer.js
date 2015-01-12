Ext.define('HdcaLib.controller.initutil.Customer', {
	control : function(controller) {
		
		controller.control({
			'mainmenu > button#bt_customermgmt' : {
				click : function() {
					// alert('客户管理');

					var vp = Ext.ComponentQuery.query('hdcaviewport')[0];
					var p = Ext.ComponentQuery.query('mainpanel')[0];

					p.removeAll();
					p.add({
						xtype : 'customergrid'
					});
					p.doLayout();

					var g = Ext.ComponentQuery.query('customergrid')[0];
					g.getStore().load();
				}
			}
		});// control end
		
		controller
		.control({
			'customergrid > toolbar > button#customergrid_tbbt_add' : {
				click : function() {
					var g = Ext.ComponentQuery
							.query('customergrid')[0];
					var win = Ext.widget(
							'customer.edit.window',
							{
								title : '添加客户',
								customeraction : 'create'
//								warehousecode : g.warehousecode
							});
					// show window
					win.show();
				}
			}
		});// control end
	}
});