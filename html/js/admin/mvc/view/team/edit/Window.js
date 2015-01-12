Ext
		.define(
				'Hdca.view.team.edit.Window',
				{
					extend : 'Ext.window.Window',
					alias : 'widget.team.edit.window',
					requires : [ 'Hdca.view.team.edit.Form' ],
					// iconCls: 'icon_user',
					width : 600,
					modal : true,
					resizable : true,
					draggable : true,
					constrainHeader : true,
					layout : 'fit',
					onChange : function(btn) {
						// var form=Ext.getCmp("mainPanel");
						var form = btn.up('window').down('form'); // this is a
																	// better
																	// approach
						
						//input check
						var cbfDistrictareas = form
								.query('fieldcontainer[id="teamformcbf_districtareas"]')[0];
						if(cbfDistrictareas.items.length==0){
							Ext.MessageBox.alert('提示','请选择省市!');
							return;
						}
						
						var iDistrictareasChecked = 0;
						for(var i=0;i<cbfDistrictareas.items.length;i++){
							if(cbfDistrictareas.items.get(i).checked==true){
								iDistrictareasChecked++;
							}
						}
						if(cbfDistrictareas.items.length==0){
							Ext.MessageBox.alert('提示','请选择服务区域!');
							return;
						}
//						if(iDistrictareasChecked){
							
//						}
//						alert('cbf=' + cbf);
//						alert('cbf.items.len=' + cbf.items.length);
						

						form
								.submit({
									waitMsg : 'Saving..',
									headers : {
										'Content-Type' : 'application/json'
									},
									clientValidation : true,
									// standardSubmit: true,
									success : function(form, action) {
										// alert('success');
										Ext.MessageBox
												.alert(
														'提示',
														me.teamaction == 'update' ? '修改成功'
																: '创建成功');
										// refresh grid
										var g = Ext.ComponentQuery
												.query('teamgrid')[0];
										g.getStore().reload();
										// g.getView().refresh();
										// close window
										btn.up('window').close();
									},
									failure : function(form, action) {
										Ext.MessageBox.alert('提示',
												me.customeraction == 'update' ? '修改失败' : '创建失败');
										btn.up('window').close();
									}
								});
					},
					initComponent : function() {
						var me = this;
						Ext.applyIf(me, {
							items : [ {
								xtype : 'team.edit.form',
								teamaction : me.teamaction,
								teamvalues : me.teamvalues
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