Ext
		.define(
				'Hdca.view.team.edit.Form',
				{
					extend : 'Ext.form.Panel',
					alias : 'widget.team.edit.form',
					requires : [ 'Ext.tab.Panel', 'Ext.form.FieldContainer',
							'Ext.form.FieldSet' ],
					initComponent : function() {
						var me = this;

						var items = [];

						// ---------init items

						// 省
						var itemProvince = {
							xtype : 'combobox',
							fieldLabel : '省',
							name : 'provinceareaid',
							// width : 100,
							// name : 'shopcode',
							editable : false,
							// editor : {
							store : 'team.edit.GeoAreasLv1',
							queryMode : 'remote',
							displayField : 'name',
							valueField : 'areaid',
							listeners : {
								select : function(combo, recs, opts) {
									var lv2store = Ext.StoreManager
											.lookup('team.edit.GeoAreasLv2');
									lv2store.proxy.extraParams.parentid = combo
											.getValue();
								}
							}
						};

						// 市
						var itemCity = {
							xtype : 'combobox',
							name : 'cityareaid',
							fieldLabel : '市',
							// width : 120,
							// name : 'shopcode',
							editable : false,
							// editor : {
							store : 'team.edit.GeoAreasLv2',
							queryMode : 'remote',
							displayField : 'name',
							valueField : 'areaid',
							listeners : {
								beforequery : function(queryEvent, eOpts) {
									delete queryEvent.combo.lastQuery;
								},
								select : function(combo, recs, opts) {
									var stGaLv3 = Ext.StoreManager
											.lookup('team.edit.GeoAreasLv3');
									// alert('stGaLv3=' + stGaLv3);
									stGaLv3.load({
										params : {
											parentid : combo.getValue()
										}
									});

								}
							}
						}

						// 服务区域：itemServiceaddress
						// ref: listener of Hdca.store.team.edit.GeoAreasLv3
						var itemDistrictareas = {
							id : 'teamformcbf_districtareas',
							xtype : 'fieldcontainer',
							layout : 'hbox',
							fieldLabel : '服务区域',
							defaultType : 'checkboxfield',
							items : []
						};

						// 擅长方向：itemTypes
						var itemTypes = {
							xtype : 'fieldcontainer',
							layout : 'hbox',
							fieldLabel : '擅长方向',
							defaultType : 'checkboxfield',
							items : []
						};

						for (var i = 0; i < teamattrs.types.length; i++) {
							var checked = false;
							if (me.teamaction == 'update') {
								var tkTypes = me.teamvalues.types.split(',');
								if ($.inArray(teamattrs.types[i]['value'],
										tkTypes) != -1) {
									checked = true;
								}
							}
							itemTypes.items.push({
								boxLabel : teamattrs.types[i]['name'],
								name : 'types',
								checked : checked,
								inputValue : teamattrs.types[i]['value'],
								id : 'teamchb_types_'
										+ teamattrs.types[i]['name']
							});
						}

						// 服务支持: itemBasepackages
						var itemBasepackages = {
							xtype : 'fieldcontainer',
							layout : 'hbox',
							fieldLabel : '服务支持',
							defaultType : 'checkboxfield',
							items : []
						};
						for (var i = 0; i < teamattrs.basepackages.length; i++) {
							var checked = false;
							if (me.teamaction == 'update') {
								var tkBasepackages = me.teamvalues.basepackages
										.split(',');
								if ($.inArray(
										teamattrs.basepackages[i]['value'],
										tkBasepackages) != -1) {
									checked = true;
								}
							}
							itemBasepackages.items
									.push({
										boxLabel : teamattrs.basepackages[i]['name'],
										name : 'basepackages',
										checked : checked,
										inputValue : teamattrs.basepackages[i]['value'],
										id : 'teamchb_basepackages_'
												+ teamattrs.basepackages[i]['name']
									});
						}

						// 深化预算: itemOffertype
						var itemOffertype = {
							xtype : 'fieldcontainer',
							layout : 'hbox',
							fieldLabel : '深化预算',
							defaultType : 'radiofield',
							items : []
						};
						for (var i = 0; i < teamattrs.offertypes.length; i++) {
							var checked = false;
							if (me.teamaction == 'update') {
								var offertype = me.teamvalues.offertype;
								// alert('offertype='+offertype+",
								// tas.otv[i]="+teamattrs.offertypes[i]['value']);
								if (teamattrs.offertypes[i]['value'] == offertype) {
									checked = true;
								}
							}
							itemOffertype.items.push({
								boxLabel : teamattrs.offertypes[i]['name'],
								name : 'offertype',
								checked : checked,
								inputValue : teamattrs.offertypes[i]['value'],
								id : 'teamchb_offertypes_'
										+ teamattrs.offertypes[i]['name']
							});
						}

						// 承接价位:itemPriceranges
						var itemPriceranges = {
							xtype : 'fieldcontainer',
							layout : 'hbox',
							fieldLabel : '承接价位',
							defaultType : 'checkboxfield',
							items : []
						};
						for (var i = 0; i < teamattrs.priceranges.length; i++) {
							var checked = false;
							if (me.teamaction == 'update') {
								var tkPriceranges = me.teamvalues.priceranges
										.split(',');
								if ($.inArray(
										teamattrs.priceranges[i]['value'],
										tkPriceranges) != -1) {
									checked = true;
								}
							}
							itemPriceranges.items.push({
								boxLabel : teamattrs.priceranges[i]['name'],
								name : 'priceranges',
								checked : checked,
								inputValue : teamattrs.priceranges[i]['value'],
								id : 'teamchb_priceranges_'
										+ teamattrs.priceranges[i]['name']
							});
						}

						// ---------push items in the array
						if (me.teamaction == 'update') {
							items.push({
								xtype : 'hidden',
								name : 'id',
								value : me.teamvalues.id
							});
						}
						items
								.push({
									xtype : 'textfield',
									name : 'name',
									fieldLabel : '名称',
									allowBlank : false,
									value : me.teamaction == 'update' ? me.teamvalues.name
											: ''
								});

						items
								.push({
									xtype : 'textfield',
									name : 'email',
									fieldLabel : 'email',
									allowBlank : true,
									value : me.teamaction == 'update' ? me.teamvalues.email
											: ''
								});
						items
								.push({
									xtype : 'textfield',
									name : 'contactname',
									fieldLabel : '联系人',
									allowBlank : true,
									value : me.teamaction == 'update' ? me.teamvalues.contactname
											: ''
								});
						items
								.push({
									xtype : 'textfield',
									name : 'mobile',
									fieldLabel : '联系手机',
									allowBlank : true,
									value : me.teamaction == 'update' ? me.teamvalues.mobile
											: '',
								});
						items.push(itemProvince);
						items.push(itemCity);
						items.push(itemDistrictareas);
						items.push(itemTypes);
						items.push(itemBasepackages);
						items.push(itemOffertype);
						items.push(itemPriceranges);
						// items.push({
						// xtype : 'textfield',
						// name : 'offertype',
						// fieldLabel : '深化预算',
						// allowBlank : true
						// });
						items.push({
							xtype : 'textfield',
							name : 'contractdesc',
							fieldLabel : '合同规范',
							allowBlank : true
						});
						items.push({
							xtype : 'textfield',
							name : 'address',
							fieldLabel : '地址',
							allowBlank : true
						});
						items.push({
							xtype : 'textfield',
							name : 'extrapackage',
							fieldLabel : '特色服务',
							allowBlank : true
						});
						items.push({
							xtype : 'textfield',
							name : 'comment',
							fieldLabel : '简介',
							allowBlank : true
						});

						// see http://182.92.173.63:8080/hdca/public/mainScript

						Ext.applyIf(me, {
							url : '/hdca/admin/team/' + me.teamaction,
							jsonSubmit : true,
							// standardSubmit: true,
							// fileUpload : true,
							items : items
						});
						me.callParent(arguments);
					}
				});