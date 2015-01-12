//var selModel = Ext.create('Ext.selection.CheckboxModel');

Ext.define('Hdca.view.contact.ContactGrid', {
	title : '联系信息管理',
	region : 'center',
	extend : 'Ext.grid.Panel',
	alias : 'widget.contactgrid',
	width : 550,
	autoHeight : true,
	selType : 'cellmodel',
	// selModel : selModel,
	viewConfig : {
		stripeRows : true
	// 斑马线效果
	},
	plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit : 1
	// 设置单击单元格编辑
	}) ],
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'contact.Contacts', // same store GridPanel is using
		dock : 'bottom',
		displayInfo : true//分页
	} ],
	
	initComponent : function() {
		var me = this;
		this.store = 'contact.Contacts';

		this.columns = [ {
			text : "id",
			width : 110,
			dataIndex : 'id'
		}, {
			text : "类型",
			width : 110,
			dataIndex : 'type'
		}, {
			text : '用户名',
			width : 110,
			dataIndex : 'customername'
		},{
			text : '施工队',
			width : 170,
			dataIndex : 'teamname'
		}  , {
			text : '内容',
			width : 400,
			dataIndex : 'request'
		} ];

		this.callParent(arguments);
	}
});