Ext.define('Hdca.view.MainMenu', {
	style : 'background-color:#333333;',
	extend : 'Ext.toolbar.Toolbar',
	alias : 'widget.mainmenu',
	region : 'north',
	initComponent : function() {
		var me = this;
		me.items = [ {
			xtype : "button",
			id : 'bt_teammgmt',
			width : 90,
			text : "施工队管理"
		}, {
			xtype : "button",
			id : 'bt_customermgmt',
			text : "客户管理",
			width : 90
		} , {
			xtype : "button",
			id : 'bt_contactmgmt',
			text : "联系信息管理",
			width : 90
		} ];

		me.callParent();
	}

});