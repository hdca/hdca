Ext.define('Hdca.view.contact.edit.Window', {
	extend : 'Ext.window.Window',
	alias : 'widget.contact.edit.window',
	requires : [ 'Hdca.view.contact.edit.Form' ],
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
				// alert('success');
				// refresh grid
				var g = Ext.ComponentQuery
						.query('gridpanel[xtype=contact.contactgrid]')[0];
				g.getStore().reload();
				// g.getView().refresh();
				// close window
				btn.up('window').close();
			},
			failure : function(form, action) {
				alert('failure');
				this.close();
			}
		});
	},
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [ {
				xtype : 'contact.edit.form'
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