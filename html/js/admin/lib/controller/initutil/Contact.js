Ext.define('HdcaLib.controller.initutil.Contact', {
	control : function(controller) {
		controller.control({
			'mainmenu > button#bt_contactmgmt' : {
				click : function() {
					//alert('施工队管理');
					var vp = Ext.ComponentQuery.query('hdcaviewport')[0];
					var p = Ext.ComponentQuery.query('mainpanel')[0];

					p.removeAll();
					p.add({
						xtype : 'contactgrid'
					});
					p.doLayout();
					
					var g = Ext.ComponentQuery.query('contactgrid')[0];
					g.getStore().load();
				}
			}
		});// control end
		
		controller
		.control({
			'contactgrid > toolbar > button#contactgrid_tbbt_add' : {
				click : function() {
					var g = Ext.ComponentQuery
							.query('contactgrid')[0];
					var win = Ext.widget(
							'contact.edit.window',
							{
								title : '用户联系信息管理'
//								warehousecode : g.warehousecode
							});
					// show window
					win.show();
				}
			}
		});// control end
	}
});