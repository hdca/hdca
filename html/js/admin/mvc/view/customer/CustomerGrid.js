Ext
		.define(
				'Hdca.view.customer.CustomerGrid',
				{
					title : '客户',
					region : 'center',
					extend : 'Ext.grid.Panel',
					alias : 'widget.customergrid',
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
						store : 'customer.Customers', // same store GridPanel
														// is using
						dock : 'bottom',
						displayInfo : true
					} ],
					tbar : [ {
						id : 'customergrid_tbbt_add',
						text : '新增客户'
					} ],
					initComponent : function() {
						var me = this;
						this.store = 'customer.Customers';

						this.columns = [
								{
									text : "id",
									width : 110,
									dataIndex : 'id'
								},
								{
									text : '昵称',
									width : 110,
									dataIndex : 'nickname'
								},
								{
									text : '用户名(email)',
									width : 170,
									dataIndex : 'email'
								},
								{
									text : '操作',
									xtype : 'actioncolumn',
									width : 50,
									items : [ {
										icon : '/hdca/img/admin/delete.png',
										tooltip : '刪除',
										handler : function(grid, rowIndex,
												colIndex) {
											var rec = me.getStore().getAt(
													rowIndex);
											var id = rec.get('id');
											Ext.Ajax
													.request({
														url : '/hdca/admin/customer/delete',
														method : 'POST',
														jsonData : {
															'id' : id
														},
														scope : this,
														success : function(
																resp, opts) {
															var json = Ext.JSON
																	.decode(resp.responseText);
															if (json.success == true) {

																Ext.MessageBox
																		.alert(
																				'提示',
																				'删除成功');
																var g = Ext.ComponentQuery
																		.query('customergrid')[0];
																g
																		.getStore()
																		.reload();
															} else {
																Ext.MessageBox
																		.alert(
																				'提示',
																				'删除失败(错误1)');
															}
														},
														failure : function(err) {
															var json = Ext.JSON
																	.decode(resp.responseText);
															Ext.MessageBox
																	.alert(
																			'提示',
																			'删除失败(错误2)');
														}
													});
										}
									} ]
								} ];

						this.listeners = {
							itemdblclick : function(dv, record, item, index, e) {

								var g = Ext.ComponentQuery
										.query('customergrid')[0];

								var win = Ext.widget('customer.edit.window', {
									title : '修改客户信息',
									customeraction : 'update',
									customervalues : {
										id : record.get('id'),
										nickname : record.get('nickname'),
										email : record.get('email'),
										mobile : record.get('mobile')
									}
								});

								var f = win.down('form');

								// show window
								win.show();

							}
						};

						this.callParent(arguments);
					}
				});