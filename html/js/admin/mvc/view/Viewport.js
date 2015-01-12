/**
 * The main application viewport, which displays the whole application
 * 
 * @extends Ext.Viewport
 */
Ext.define('Hdca.view.Viewport', {
	extend : 'Ext.Viewport',
	alias : 'widget.hdcaviewport',
	layout : 'border',

	requires : [],

	initComponent : function() {
		var me = this;

		Ext.apply(me, {
			items : [ {
				xtype : 'mainmenu'
			}, {
				xtype : 'mainpanel'
			} ]
		});

		me.callParent(arguments);
	}
});
