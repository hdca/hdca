package com.hdca.logic.admin.action.team;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdca.domain.GeoArea;
import com.hdca.logic.BaseAction;
import com.hdca.service.gson.processor.GeoAreaProcessor;

public class TeamAttributeAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamAttributeAction.class.getName());

	/*---------------------
	 * for combobox
	 *--------------------*/
	public String geoAreaLv1Read() {
		logger.debug("[GeoAreaServiceImpl][geoAreaLv1Read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		// get total count and items
		Integer count = null;
		List<GeoArea> geoAreaList = null;

		GeoArea gaQuery = new GeoArea();
		gaQuery.setType(1);
		gaQuery.setParentid(1);
		geoAreaList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getGeoAreaByTypeAndParentid", gaQuery);

		//
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(GeoArea.class, new GeoAreaProcessor());
		Gson gson = gb.create();

		// JsonArray jsaCommoditiesResult = new JsonArray();
		String jsonGeoAreas = gson.toJson(geoAreaList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonGeoAreas).append("}");

		response.setContentType("application/json");
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String geoAreaLv2Read() {
		logger.debug("[GeoAreaAction][lv2Read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String strParentid = req.getParameter("parentid");

		logger.debug("[GeoAreaAction][lv2Read] param: parentid=" + strParentid);

		int parentid = Integer.parseInt(strParentid);

		// get total count and items
		Integer count = null;
		List<GeoArea> geoAreaList = null;

		GeoArea gaQuery = new GeoArea();
		gaQuery.setType(2);
		gaQuery.setParentid(parentid);
		// count = (Integer) getSqlSession().selectOne(
		// "hjmall.GeoAreaMapper.getGeoAreaCountByTypeAndParentid");
		geoAreaList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getGeoAreaByTypeAndParentid", gaQuery);

		//
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(GeoArea.class, new GeoAreaProcessor());
		Gson gson = gb.create();

		// JsonArray jsaCommoditiesResult = new JsonArray();
		String jsonGeoAreas = gson.toJson(geoAreaList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonGeoAreas).append("}");

		response.setContentType("application/json");
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String geoAreaLv3Read() {
		logger.debug("[GeoAreaAction][lv3Read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String strParentid = req.getParameter("parentid");

		logger.debug("[GeoAreaAction][lv3Read] param: parentid=" + strParentid);

		int parentid = Integer.parseInt(strParentid);

		// get total count and items
		Integer count = null;
		List<GeoArea> geoAreaList = null;

		GeoArea gaQuery = new GeoArea();
		gaQuery.setType(3);
		gaQuery.setParentid(parentid);
		// count = (Integer) getSqlSession().selectOne(
		// "hjmall.GeoAreaMapper.getGeoAreaCountByTypeAndParentid");
		geoAreaList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getGeoAreaByTypeAndParentid", gaQuery);

		//
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(GeoArea.class, new GeoAreaProcessor());
		Gson gson = gb.create();

		// JsonArray jsaCommoditiesResult = new JsonArray();
		String jsonGeoAreas = gson.toJson(geoAreaList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonGeoAreas).append("}");

		response.setContentType("application/json");
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
