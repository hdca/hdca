//var selModel = Ext.create('Ext.selection.CheckboxModel');

Ext
		.define(
				'Hdca.view.team.TeamGrid',
				{
					title : '施工队',
					region : 'center',
					extend : 'Ext.grid.Panel',
					alias : 'widget.teamgrid',
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
						store : 'team.Teams', // same store GridPanel is using
						dock : 'bottom',
						displayInfo : true
					// 分页
					} ],
					tbar : [ {
						id : 'teamgrid_tbbt_add',
						text : '新增施工队'
					} ],
					initComponent : function() {
						var me = this;
						this.store = 'team.Teams';

						this.columns = [
								{
									text : "id",
									width : 110,
									dataIndex : 'id'
								},
								{
									text : '名称',
									width : 110,
									dataIndex : 'name'
								},
								{
									text : 'email',
									width : 170,
									dataIndex : 'email'
								},
								{
									text : '联系人',
									width : 80,
									dataIndex : 'contactname'
								},
								{
									text : '联系手机',
									width : 100,
									dataIndex : 'mobile'
								},
								{
									text : '城市',
									width : 100,
									dataIndex : 'cityname'
								},
								{
									text : '服务区域',
									width : 200,
									dataIndex : 'districtnames'
								},
								{
									text : '擅长方向',
									width : 170,
									// dataIndex : 'typenames',
									renderer : function(value, metaData,
											record, row, col, store, gridView) {
										// record.get('types');
										var sTypes = record.get('types');
										if (sTypes != null
												&& sTypes.length != 0) {
											var tkTypes = sTypes.split(',');
											var sTypeNames = '';
											for (var i = 0; i < tkTypes.length; i++) {
												sTypeNames += teamattrmaps.typemap[tkTypes[i]
														+ '']
														+ ',';
											}
											return sTypeNames;
										}
										return '';
									}
								},
								{
									text : '服务支持',
									width : 100,
									renderer : function(value, metaData,
											record, row, col, store, gridView) {
										var sBasepackages = record
												.get('basepackages');
										if (sBasepackages != null
												&& sBasepackages.length != 0) {
											var tkPriceranges = sBasepackages
													.split(',');
											var sPricerangeNames = '';
											for (var i = 0; i < tkPriceranges.length; i++) {
												sPricerangeNames += teamattrmaps.basepackagemap[tkPriceranges[i]
														+ '']
														+ ',';
											}
											return sPricerangeNames;
										}
										return '';
									}
								// dataIndex : 'support'
								},
								{
									text : '深化预算',
									width : 100,
									// dataIndex : 'offertype'
									renderer : function(value, metaData,
											record, row, col, store, gridView) {
										// record.get('types');
										var sOffertype = record
												.get('offertype');
										// alert('offertype='+sOffertype);
										if (sOffertype != null
												&& sOffertype.length != 0
												&& sOffertype != 0) {
											return teamattrmaps.offertypemap[sOffertype];

										}
										return '';
									}
								},
								{
									text : '承接价位',
									width : 170,
									// dataIndex : 'pricerangenames',
									renderer : function(value, metaData,
											record, row, col, store, gridView) {
										// record.get('types');
										var sPriceranges = record
												.get('priceranges');
										if (sPriceranges != null
												&& sPriceranges.length != 0) {
											var tkPriceranges = sPriceranges
													.split(',');
											var sPricerangeNames = '';
											for (var i = 0; i < tkPriceranges.length; i++) {
												sPricerangeNames += teamattrmaps.pricerangemap[tkPriceranges[i]
														+ '']
														+ ',';
											}
											return sPricerangeNames;
										}
										return '';
									}
								},
								{
									text : '合同规范',
									width : 100,
									dataIndex : 'contractdesc'
								},
								{
									text : '地址',
									width : 100,
									dataIndex : 'address'
								},
								{
									text : '特色服务',
									width : 100,
									dataIndex : 'extrapackage'
								},
								{
									text : '展示编号',
									width : 100,
									dataIndex : 'adid'
								},
								{
									text : '简介',
									width : 100,
									dataIndex : 'comment'
								},
								{
									text : "LOGO上传",
									xtype : 'actioncolumn',
									width : 50,
									items : [
											{
												icon : '/hdca/img/admin/uploadimage.png',
												tooltip : '上传LOGO',
												handler : function(grid,
														rowIndex, colIndex) {

													var rec = grid.getStore()
															.getAt(rowIndex);
													var teamid = rec.get('id');
													// var name =
													// rec.get('name');

													var imgForm = Ext
															.create(
																	'Ext.form.Panel',
																	{
																		defaults : {
																			anchor : '100%',
																			allowBlank : false,
																			msgTarget : 'side',
																			labelWidth : 100
																		},

																		items : [
																				{
																					xtype : 'hidden',
																					name : 'id',
																					value : teamid
																				},
																				{
																					xtype : 'filefield',
																					id : 'form-file',
																					emptyText : 'Select an image',
																					fieldLabel : '选择文件',
																					name : 'logo',
																					buttonText : '...'
																				} ],

																		buttons : [ {
																			text : '上传',
																			handler : function() {
																				var form = this
																						.up(
																								'form')
																						.getForm();
																				if (form
																						.isValid()) {
																					form
																							.submit({
																								url : '/hdca/admin/team/uploadLogo',
																								waitMsg : 'Uploading your logo...',
																								success : function(
																										fp,
																										o) {
																									Ext.MessageBox
																											.alert(
																													'提示',
																													'文件"'
																															+ o.result.file
																															+ '"上传成功');
																								}
																							});
																				}
																			}
																		} ]
																	});
													var rec = grid.getStore()
															.getAt(rowIndex);
													// imgForm.show();
													var win = Ext
															.widget(
																	'window',
																	{
																		title : '上传LOGO: '
																				+ name
																				+ '('
																				+ teamid
																				+ ')',
																		width : 600,
																		modal : true,
																		resizable : true,
																		draggable : true,
																		constrainHeader : true,
																		layout : 'fit',
																		items : [ imgForm ]
																	});
													// show window
													win.show();

												}
											},
											{
												icon : '/hdca/img/admin/viewimage.png',
												tooltip : '查看LOGO',
												handler : function(grid,
														rowIndex, colIndex) {
													var rec = grid.getStore()
															.getAt(rowIndex);
													var teamid = rec.get('id');

													var image = new Ext.Component(
															{
																autoEl : {
																	tag : 'img',
																	src : '/hdca/teamlogo/'
																			+ teamid
																			+ '.jpg'
																}
															});

													var imgForm = Ext
															.create(
																	'Ext.form.Panel',
																	{
																		items : [ image ]
																	});

													// imgForm.show();
													var win = Ext
															.widget(
																	'window',
																	{
																		title : '查看概览图: '
																				+ name
																				+ '('
																				+ teamid
																				+ ')',
																		width : 600,
																		height : 500,
																		modal : true,
																		resizable : true,
																		draggable : true,
																		constrainHeader : true,
																		layout : 'fit',
																		items : [ imgForm ]
																	});
													// show window
													win.show();
												}

											} ]
								},
								{
									text : '展示栏',
									xtype : 'actioncolumn',
									width : 110,
									items : [ {
										icon : '/hdca/img/admin/settings.png',
										tooltip : 'ad',
										handler : function(grid, rowIndex,
												colIndex) {
											var rec = me.getStore().getAt(
													rowIndex);
											// var code = rec.get('code');

											var win = Ext.widget(
													'team.ad.window',
													{
														title : 'ad'
													});

											var f = win.down('form');
											f
													.getForm()
													.setValues(
															{
																id : rec
																		.get('id')
															})

											// show window
											win.show();
										}
									} ]
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
														url : '/hdca/admin/team/delete',
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
																		.query('teamgrid')[0];
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
								// alert('working, rec putawayid=' +
								// record.get('putawayid')
								// + ', commodityname=' +
								// record.get('commodityname'));

								var g = Ext.ComponentQuery.query('teamgrid')[0];
								// alert('extra='
								// +
								// g.getStore().getProxy().extraParams['putawayid']);

								var win = Ext.widget('team.edit.window',
										{
											title : '修改施工队信息',
											// putawayid : g.getStore()
											// .getProxy().extraParams['putawayid'],
											teamaction : 'update',
											teamvalues : {
												id : record.get('id'),
												contactname : record
														.get('contactname'),
												email : record.get('email'),
												name : record.get('name'),
												cityname : record
														.get('cityname'),
												cityareaid : record
														.get('cityareaid'),
												types : record.get('types'),
												basepackages : record
														.get('basepackages'),
												priceranges : record
														.get('priceranges'),
												offertype : record
														.get('offertype'),
												mobile : record.get('mobile')
											}
										});

								var f = win.down('form');
								// f.getForm().setValues({
								// contactname : record.get('contactname'),
								// email : record.get('email'),
								// name : record.get('name'),
								// cityname : record.get('cityname'),
								// cityareaid:record.get('cityareaid'),
								// mobile : record.get('mobile')
								// })
								// alert('f=' + f+', value
								// cic='+f.getForm().findField("commodityincatalog").getValue());

								// show window
								win.show();

							}
						};

						this.callParent(arguments);
					}
				});