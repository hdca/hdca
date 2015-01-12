package com.hdca.logic.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.hdca.logic.BaseAction;

public class AdminScriptAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(AdminScriptAction.class.getName());

	public String execute() {
		logger.debug("[AdminScriptAction][read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		String strScript = generateAdminScript();

		// msgsrc.getMessage("webroot");

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(strScript);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String generateAdminScript() {
		StringBuffer sb = new StringBuffer();

		sb.append("var teamattrs={};").append("\r\n");
		sb.append("var teamattrmaps={};").append("\r\n");

		GsonBuilder gb = new GsonBuilder();
		Gson gson = gb.create();

		// team.type
		String sTypeScript = "";
		String sTypeNames = msgsrc.getMessage("team.type.names");
		String sTypeValues = msgsrc.getMessage("team.type.values");
		String[] aTypeNames = sTypeNames.split(",");
		String[] aTypeValues = sTypeValues.split(",");
		if (aTypeNames.length == aTypeValues.length) {
			JsonArray jsaTypes = new JsonArray();
			for (int i = 0; i < aTypeNames.length; i++) {
				JsonObject jsoType = new JsonObject();
				jsoType.add("name", new JsonPrimitive(aTypeNames[i]));
				jsoType.add("value", new JsonPrimitive(aTypeValues[i]));
				jsaTypes.add(jsoType);
			}

			sb.append("teamattrs.types=").append(jsaTypes).append(";\r\n");

			// sTypeScript = "var types=" + jsaTypes.toString();
		}

		sb.append(sTypeScript);

		// team.basepackage
		String sBasepackageNames = msgsrc.getMessage("team.basepackage.names");
		String sBasepackageValues = msgsrc
				.getMessage("team.basepackage.values");
		String[] aBasepackageNames = sBasepackageNames.split(",");
		String[] aBasepackageValues = sBasepackageValues.split(",");
		if (aBasepackageNames.length == aBasepackageValues.length) {
			JsonArray jsaBasepackages = new JsonArray();
			for (int i = 0; i < aBasepackageNames.length; i++) {
				JsonObject jsoBasepackage = new JsonObject();
				jsoBasepackage.add("name", new JsonPrimitive(
						aBasepackageNames[i]));
				jsoBasepackage.add("value", new JsonPrimitive(
						aBasepackageValues[i]));
				jsaBasepackages.add(jsoBasepackage);
			}

			sb.append("teamattrs.basepackages=").append(jsaBasepackages)
					.append(";\r\n");
		}

		// team.offertype
		String sOffertypeNames = msgsrc.getMessage("team.offertype.names");
		String sOffertypeValues = msgsrc.getMessage("team.offertype.values");
		String[] aOffertypeNames = sOffertypeNames.split(",");
		String[] aOffertypeValues = sOffertypeValues.split(",");
		if (aOffertypeNames.length == aOffertypeValues.length) {
			JsonArray jsaOffertypes = new JsonArray();
			for (int i = 0; i < aOffertypeNames.length; i++) {
				JsonObject jsoOffertype = new JsonObject();
				jsoOffertype.add("name", new JsonPrimitive(aOffertypeNames[i]));
				jsoOffertype.add("value",
						new JsonPrimitive(aOffertypeValues[i]));
				jsaOffertypes.add(jsoOffertype);
			}

			sb.append("teamattrs.offertypes=").append(jsaOffertypes)
					.append(";\r\n");
		}

		// team.pricerange
		String sPricerangeNames = msgsrc.getMessage("team.pricerange.names");
		String sPricerangeValues = msgsrc.getMessage("team.pricerange.values");
		String[] aPricerangeNames = sPricerangeNames.split(",");
		String[] aPricerangeValues = sPricerangeValues.split(",");
		if (aPricerangeNames.length == aPricerangeValues.length) {
			JsonArray jsaPriceranges = new JsonArray();
			for (int i = 0; i < aPricerangeNames.length; i++) {
				JsonObject jsoPricerange = new JsonObject();
				jsoPricerange.add("name",
						new JsonPrimitive(aPricerangeNames[i]));
				jsoPricerange.add("value", new JsonPrimitive(
						aPricerangeValues[i]));
				jsaPriceranges.add(jsoPricerange);
			}

			sb.append("teamattrs.priceranges=").append(jsaPriceranges)
					.append(";\r\n");
		}

		// ------------- map-----------
		sb.append("\r\n").append("//------------- map-----------")
				.append("\r\n").append("\r\n");
		if (aTypeNames.length == aTypeValues.length) {
			JsonObject jsoTypeMap = new JsonObject();
			for (int i = 0; i < aTypeNames.length; i++) {
				jsoTypeMap
						.add(aTypeValues[i], new JsonPrimitive(aTypeNames[i]));
			}

			sb.append("teamattrmaps.typemap=").append(jsoTypeMap).append(";\r\n");
			// sTypeScript = "var types=" + jsaTypes.toString();
		}
		if (aPricerangeNames.length == aPricerangeValues.length) {
			JsonObject jsoPricerangeMap = new JsonObject();
			for (int i = 0; i < aPricerangeNames.length; i++) {
				jsoPricerangeMap
						.add(aPricerangeValues[i], new JsonPrimitive(aPricerangeNames[i]));
			}
			sb.append("teamattrmaps.pricerangemap=").append(jsoPricerangeMap).append(";\r\n");
			// sTypeScript = "var types=" + jsaTypes.toString();
		}

		return sb.toString();
	}
}
