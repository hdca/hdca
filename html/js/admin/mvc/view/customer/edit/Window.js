Ext.define('Hdca.view.customer.edit.Window', {
	extend : 'Ext.window.Window',
	alias : 'widget.customer.edit.window',
	requires : [ 'Hdca.view.customer.edit.Form' ],
	// iconCls: 'icon_user',
	width : 600,
	modal : true,
	resizable : true,
	draggable : true,
	constrainHeader : true,
	layout : 'fit',
	onChange : function(btn) {
		// var form=Ext.getCmp("mainPanel");
		var form = btn.up('window').down('form'); // this is a better approach
		var me = this;
		form.submit({
			waitMsg : 'Saving..',
			headers : {
				'Content-Type' : 'application/json'
			},
			clientValidation : true,
			success : function(form, action) {
				
				Ext.MessageBox.alert('提示',
						me.customeraction == 'update' ? '修改成功' : '创建成功');
				// alert('success');
				// refresh grid
				var g = Ext.ComponentQuery.query('customergrid')[0];
				g.getStore().reload();
				// g.getView().refresh();
				// close window
				btn.up('window').close();
			},
			failure : function(form, action) {
				Ext.MessageBox.alert('提示',
						me.customeraction == 'update' ? '修改失败' : '创建失败');
				btn.up('window').close();
			}
		});
	},
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [ {
				xtype : 'customer.edit.form',
				customeraction : me.customeraction,
				customervalues : me.customervalues
			} ],
			buttons : [ {
				text : '保存',
				scope : this,
				handler : this.onChange
			}, ]
		});
		me.callParent(arguments);
	}
});