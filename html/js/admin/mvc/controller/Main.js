Ext.define('Hdca.controller.Main',
		{
			extend : 'Ext.app.Controller',
			requires : [ 'HdcaLib.controller.initutil.Team',
					'HdcaLib.controller.initutil.Customer',
					'HdcaLib.controller.initutil.Contact' ],
			/*
			 */
			stores : [ 'customer.Customers', 'team.Teams',
					'team.edit.GeoAreasLv1', 'team.edit.GeoAreasLv2',
					'team.edit.GeoAreasLv3', 'contact.Contacts' ],

			models : [ 'customer.Customer', 'team.Team',
					'team.edit.GeoAreaLv1', 'team.edit.GeoAreaLv2',
					'team.edit.GeoAreaLv3', 'contact.Contact' ],

			views : [ 'Viewport', 'MainPanel', 'MainMenu',
					'customer.CustomerGrid', 'customer.edit.Form',
					'customer.edit.Window', 'team.TeamGrid', 'team.edit.Form',
					'team.edit.Window', 'team.ad.Form', 'team.ad.Window',
					'contact.ContactGrid', 'contact.edit.Form',
					'contact.edit.Window' ],

			init : function() {
				/***************************************************************
				 * team
				 **************************************************************/
				var iuCustomer = new HdcaLib.controller.initutil.Customer();
				iuCustomer.control(this);

				/***************************************************************
				 * team
				 **************************************************************/
				var iuTeam = new HdcaLib.controller.initutil.Team();
				iuTeam.control(this);
				/***************************************************************
				 * contact
				 **************************************************************/
				var iuContact = new HdcaLib.controller.initutil.Contact();
				iuContact.control(this);

			},// init end

		});