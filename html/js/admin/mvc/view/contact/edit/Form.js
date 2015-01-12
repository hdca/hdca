Ext.define('Hdca.view.contact.edit.Form', {
	extend : 'Ext.form.Panel',
	alias : 'widget.contact.edit.form',
	requires : [ 'Ext.tab.Panel', 'Ext.form.FieldContainer',
			'Ext.form.FieldSet' ],
	initComponent : function() {
		var me = this;

		// var date = new Date();
		// var strDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
		// + date.getDate() + " " + date.getHours() + ":"
		// + date.getMinutes() + ":" + date.getSeconds();
		// var strExtDate = Ext.util.Format.date(date,'m/d/Y H:i:s');

		Ext.applyIf(me, {
			url : '/hdca/management/contact/create',
			jsonSubmit : true,
			items : [ {
				xtype : 'textfield',
				name : 'id',
				fieldLabel : '编号',
				allowBlank : true
			// requires a non-empty value
			}, {
				xtype : 'textfield',
				name : 'name',
				fieldLabel : '用户名',
				allowBlank : false
			// requires a non-empty value
			}, {
				xtype : 'textfield',
				name : 'contain',
				fieldLabel : '内容',
				allowBlank : false
			} , {
				xtype : 'textfield',
				name : 'team',
				fieldLabel : '施工队',
				allowBlank : false
			}  ]
		});
		me.callParent(arguments);
	}
});