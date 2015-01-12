Ext.define('Hdca.view.customer.edit.Form', {
	extend : 'Ext.form.Panel',
	alias : 'widget.customer.edit.form',
	requires : [ 'Ext.tab.Panel', 'Ext.form.FieldContainer',
			'Ext.form.FieldSet' ],
	initComponent : function() {
		var me = this;

		// alert('action='+me.customeraction);

		var items = [];
		if (me.customeraction == 'update') {
			items.push({
				xtype : 'hidden',
				name : 'id',
				value : me.customervalues.id
			});
		}
		items.push({
			xtype : 'textfield',
			name : 'nickname',
			value : me.customeraction == 'update' ? me.customervalues.nickname
					: '',
			fieldLabel : '昵称',
			allowBlank : false
		// requires a non-empty
		// value
		});
		items.push({
			xtype : 'textfield',
			name : 'email',
			value : me.customeraction == 'update' ? me.customervalues.email
					: '',
			fieldLabel : '用户名(Email)',
			allowBlank : true
		// requires a non-empty
		// value
		});
		items.push({
			xtype : 'textfield',
			name : 'password',
			fieldLabel : '密码',
			readOnly : me.customeraction == 'update' ? true : false,
			allowBlank : true
		// requires a non-empty
		// value
		});
		items.push({
			xtype : 'textfield',
			name : 'mobile',
			value : me.customeraction == 'update' ? me.customervalues.mobile
					: '',
			fieldLabel : '手机',
			allowBlank : true
		// requires a non-empty
		// value
		});
		items.push({
			xtype : 'textfield',
			name : 'comment',
			value : me.customeraction == 'update' ? me.customervalues.comment
					: '',
			fieldLabel : '备注',
			allowBlank : true
		// requires a non-empty
		// value
		});

		// requires a non-empty value

		Ext.applyIf(me, {
			url : '/hdca/admin/customer/' + me.customeraction,
			jsonSubmit : true,
			items : items
		});
		me.callParent(arguments);
	}
});