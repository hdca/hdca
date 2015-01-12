Ext.define('Hdca.view.team.ad.Window', {
	extend : 'Ext.window.Window',
	alias : 'widget.team.ad.window',
	requires : [ 'Hdca.view.team.ad.Form' ],
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
		form.submit({
			waitMsg : 'Saving..',
			headers : {
				'Content-Type' : 'application/json'
			},
			clientValidation : true,
			success : function(form, action) {
				Ext.MessageBox.alert('提示', 'ad成功!');
				// alert('success');
				// refresh grid
				var g = Ext.ComponentQuery
						.query('teamgrid')[0];
				g.getStore().reload();
				// g.getView().refresh();
				// close window
				btn.up('window').close();
			},
			failure : function(form, action) {
				Ext.MessageBox.alert('提示', 'ad失败!(错误1)');
				this.close();
			}
		});
	},
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [ {
				xtype : 'team.ad.form'
			} ],
			buttons : [ {
				text : '设定',
				scope : this,
				handler : this.onChange
			}, ]
		});
		me.callParent(arguments);
	}
});