Ext.define('Hdca.view.team.ad.Form', {
	extend : 'Ext.form.Panel',
	alias : 'widget.team.ad.form',
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
			url : '/hdca/admin/team/setad',
			jsonSubmit : true,
			items : [ {
				xtype : 'textfield',
				name : 'adid',
				fieldLabel : 'adid',
				allowBlank : false
			}, {
				xtype : 'hidden',
				name : 'id'
			} ]
		});
		me.callParent(arguments);
	}
});