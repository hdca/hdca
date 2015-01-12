package com.hdca.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.hdca.domain.GeoArea;
import com.hdca.domain.page.GeoAreaPage;
import com.hdca.service.IGeoAreaService;
import com.hdca.service.gson.processor.GeoAreaProcessor;

public class GeoAreaServiceImpl extends SqlSessionDaoSupport implements
		IGeoAreaService {
	public List<GeoArea> getRegisteredCity() {
		List<GeoArea> geoareaList = new ArrayList<GeoArea>();

		GeoArea ga1 = new GeoArea();
		ga1.setName("北京");
		ga1.setAreaid(52);
		ga1.setType(2);
		ga1.setChildren(getGeoAreaByDirectParent(ga1.getAreaid()));

		GeoArea ga2 = new GeoArea();
		ga2.setName("上海");
		ga2.setAreaid(321);
		ga2.setType(2);
		ga2.setChildren(getGeoAreaByDirectParent(ga2.getAreaid()));

		GeoArea ga3 = new GeoArea();
		ga3.setName("杭州");
		ga3.setAreaid(383);
		ga3.setType(2);
		ga3.setChildren(getGeoAreaByDirectParent(ga3.getAreaid()));

		geoareaList.add(ga1);
		geoareaList.add(ga2);
		geoareaList.add(ga3);

		return geoareaList;
	}

	public List<GeoArea> getGeoAreaByDirectParentTest(int parentid) {
		List<GeoArea> geoareaList = new ArrayList<GeoArea>();

		GeoArea ga1 = new GeoArea();
		ga1.setName("海淀");
		ga1.setAreaid(511);

		GeoArea ga2 = new GeoArea();
		ga2.setName("朝阳");
		ga2.setAreaid(512);

		geoareaList.add(ga1);
		geoareaList.add(ga2);

		return geoareaList;
	}

	@Override
	public List<GeoArea> getGeoAreaByDirectParent(int parentid) {
		List<GeoArea> geoareaList = null;

		GeoAreaPage geoAreaPage = new GeoAreaPage();
		geoAreaPage.setParentid(parentid);

		geoareaList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getGeoAreaByDirectParent", geoAreaPage);

		return geoareaList;
	}

	public String jsongetProvinceMap() {

		// JsonArray jsaCommoditiesResult = new JsonArray();

		List<GeoArea> geoareaList = null;

		geoareaList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getAllProvince");

		// java data to json
		// GsonBuilder gb = new GsonBuilder();
		// gb.registerTypeAdapter(GeoArea.class, new GeoAreaProcessor());
		// Gson gson = gb.create();
		// String jsonProvinces = gson.toJson(geoareaList);

		JsonObject jsoPrMap = new JsonObject();
		for (GeoArea ga : geoareaList) {
			jsoPrMap.addProperty(String.valueOf(ga.getAreaid()), ga.getName());
		}
		String jsonProvinces = jsoPrMap.toString();

		return jsonProvinces;
	}

	/**
	 * mmm: there's important refactoring here (about two-dim-map iteration)
	 * 
	 * @return
	 */
	public String jsongetProvinceCityMapFormat1() {

		// JsonArray jsaCommoditiesResult = new JsonArray();

		List<GeoArea> gaPrList = null;
		List<GeoArea> gaCityList = null;

		gaPrList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getAllProvince");
		gaCityList = getSqlSession()
				.selectList("hdca.GeoAreaMapper.getAllCity");

		// gen map
		Map<Integer, GeoArea> prUtilMap = new HashMap<Integer, GeoArea>();
		for (GeoArea gaPr : gaPrList) {
			prUtilMap.put(Integer.valueOf(gaPr.getAreaid()), gaPr);
		}

		for (GeoArea gaCity : gaCityList) {
			GeoArea gaPr = prUtilMap.get(Integer.valueOf(gaCity.getParentid()));
			gaPr.getChildren().add(gaCity);
		}

		// java data to json
		// GsonBuilder gb = new GsonBuilder();
		// gb.registerTypeAdapter(GeoArea.class, new GeoAreaProcessor());
		// Gson gson = gb.create();
		// String jsonProvinces = gson.toJson(geoareaList);

		JsonObject jsoPrMap = new JsonObject();
		JsonObject jsoCityMap = null;
		for (GeoArea ga : gaPrList) {
			jsoCityMap = new JsonObject();
			for (GeoArea gaCity : ga.getChildren()) {
				jsoCityMap.addProperty(String.valueOf(gaCity.getAreaid()),
						gaCity.getName());
			}
			jsoPrMap.add(String.valueOf(ga.getAreaid()), jsoCityMap);
		}
		String jsonProvinces = jsoPrMap.toString();

		return jsonProvinces;

	}

	public String jsongetProvinceCityMap() {

		// JsonArray jsaCommoditiesResult = new JsonArray();

		List<GeoArea> gaPrList = null;
		List<GeoArea> gaCityList = null;

		gaPrList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getAllProvince");
		gaCityList = getSqlSession()
				.selectList("hdca.GeoAreaMapper.getAllCity");

		// gen map
		Map<Integer, GeoArea> prUtilMap = new HashMap<Integer, GeoArea>();
		for (GeoArea gaPr : gaPrList) {
			prUtilMap.put(Integer.valueOf(gaPr.getAreaid()), gaPr);
		}

		for (GeoArea gaCity : gaCityList) {
			GeoArea gaPr = prUtilMap.get(Integer.valueOf(gaCity.getParentid()));
			gaPr.getChildren().add(gaCity);
		}

		// java data to json
		// GsonBuilder gb = new GsonBuilder();
		// gb.registerTypeAdapter(GeoArea.class, new GeoAreaProcessor());
		// Gson gson = gb.create();
		// String jsonProvinces = gson.toJson(geoareaList);

		JsonObject jsoPrMap = new JsonObject();
		JsonArray jsaCityList = null;
		for (GeoArea ga : gaPrList) {
			jsaCityList = new JsonArray();
			for (GeoArea gaCity : ga.getChildren()) {
				JsonArray jsaCity = new JsonArray();
				jsaCity.add(new JsonPrimitive(
						String.valueOf(gaCity.getAreaid())));
				jsaCity.add(new JsonPrimitive(gaCity.getName()));
				jsaCityList.add(jsaCity);

			}
			jsoPrMap.add(String.valueOf(ga.getAreaid()), jsaCityList);
		}

		String jsonProvinces = jsoPrMap.toString();

		return jsonProvinces;

	}

	/**
	 * temp
	 * 
	 * @return
	 */
	public String jsongetCityMap() {

		return null;

	}

}
