package com.hdca.service.gson.processor.admin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hdca.domain.GeoArea;
import com.hdca.domain.Team;
import com.hdca.service.mybatis.typehandler.IntArrayTypeHandler;

public class TeamProcessor implements JsonDeserializer<Team>,
		JsonSerializer<Team> {
	private static final Logger logger = LogManager
			.getLogger(TeamProcessor.class.getName());

	MessageSourceAccessor msgsrc;
	SqlSession sqlSession;

	public TeamProcessor(MessageSourceAccessor _msgsrc, SqlSession _sqlSession) {
		this.msgsrc = _msgsrc;
		this.sqlSession = _sqlSession;
	}

	public JsonElement serialize(Team s, Type arg1, JsonSerializationContext ctx) {
		JsonObject jso = new JsonObject();
		jso.addProperty("id", s.getId());
		jso.addProperty("adid", s.getAdid());
		jso.addProperty("name", s.getName());
		jso.addProperty("mobile", s.getMobile());
		jso.addProperty("address", s.getAddress());
		jso.addProperty("contactname", s.getContactname());
		jso.addProperty("email", s.getEmail());
		jso.addProperty("cityareaid", s.getCityareaid());
		jso.addProperty("offertype", s.getOffertype());
		jso.addProperty("contractdesc", s.getContractdesc());
		jso.addProperty("address", s.getAddress());
		jso.addProperty("extrapackage", s.getExtrapackage());
		jso.addProperty("adid", s.getAdid());
		jso.addProperty("comment", s.getComment());
		// mmm: to be checked!
		jso.addProperty("types",
				Joiner.on(IntArrayTypeHandler.SEPARATOR).join(s.getTypes()));
		jso.addProperty("priceranges", Joiner.on(IntArrayTypeHandler.SEPARATOR)
				.join(s.getPriceranges()));
		jso.addProperty(
				"districtareas",
				Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
						s.getDistrictareas()));
		jso.addProperty(
				"districtnames",
				Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
						s.getDistrictnames()));
		jso.addProperty("basepackages", Joiner
				.on(IntArrayTypeHandler.SEPARATOR).join(s.getBasepackages()));

		// jso.addProperty("types",
		// Joiner.on(IntArrayTypeHandler.SEPARATOR).join(s.getTypes()));
		// jso.addProperty("priceranges",
		// Joiner.on(IntArrayTypeHandler.SEPARATOR)
		// .join(s.getPriceranges()));

		// for display
		// jso.addProperty(
		// "districtnames",
		// Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
		// s.getDistrictnames()));
		jso.addProperty("cityname", s.getCityname());

		return jso;
	}

	public Team deserialize(JsonElement jse, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jso = jse.getAsJsonObject();
		Team s = new Team();
		s.setId(jso.get("id") == null ? 0 : jso.get("id").getAsLong());
		s.setAdid(jso.get("adid") == null ? 0 : jso.get("adid").getAsInt());
		s.setName(jso.get("name") == null ? null : jso.get("name")
				.getAsString());
		s.setMobile(jso.get("mobile") == null ? null : jso.get("mobile")
				.getAsString());
		s.setAddress(jso.get("address") == null ? null : jso.get("address")
				.getAsString());
		s.setContactname(jso.get("contactname") == null ? null : jso.get(
				"contactname").getAsString());
		s.setContractdesc(jso.get("contractdesc") == null ? null : jso.get(
				"contractdesc").getAsString());
		s.setExtrapackage(jso.get("extrapackage") == null ? null : jso.get(
				"extrapackage").getAsString());
		s.setComment(jso.get("comment") == null ? null : jso.get("comment")
				.getAsString());
		s.setEmail(jso.get("email") == null ? null : jso.get("email")
				.getAsString());
		int cityareaid = 0;
		if(jso.get("cityareaid") != null){
			cityareaid = jso.get("cityareaid").getAsInt();
			s.setCityareaid(cityareaid);
		}
		s.setOffertype(jso.get("offertype") == null ? 0 : jso.get("offertype")
				.getAsInt());

		// types
		List<Integer> types = getIntValuesByCheckboxValues(jso,
				"team.type.values", "types");
		s.setTypes(types);

		// priceranges
		List<Integer> priceranges = getIntValuesByCheckboxValues(jso,
				"team.pricerange.values", "priceranges");
		s.setPriceranges(priceranges);

		// basepackages
		List<Integer> basepackages = getIntValuesByCheckboxValues(jso,
				"team.basepackage.values", "basepackages");
		s.setBasepackages(basepackages);

		// districtareas

		List<GeoArea> gaDistrictList = null;

		GeoArea gaQuery = new GeoArea();
		gaQuery.setType(3);
		
		gaQuery.setParentid(cityareaid);
		gaDistrictList = getSqlSession().selectList(
				"hdca.GeoAreaMapper.getGeoAreaByTypeAndParentid", gaQuery);

		List<Integer> districtreas = new ArrayList<Integer>();
		// String strMsgsrcValues = msgsrc.getMessage(keyMs);
		// String[] tkMsgsrcValues = strMsgsrcValues.split(",");
		JsonArray jsaValues = jso.get("districtareas").getAsJsonArray();
		for (int i = 0; i < jsaValues.size(); i++) {
			Boolean b = jsaValues.get(i).getAsBoolean();
			if (b) {
				districtreas.add(gaDistrictList.get(i).getAreaid());
			}
		}
		s.setDistrictareas(districtreas);

		return s;
	}

	private List<Integer> getIntValuesByCheckboxValues(JsonObject jso,
			String keyMs, String keyJso) {
		List<Integer> values = new ArrayList<Integer>();
		String strMsgsrcValues = msgsrc.getMessage(keyMs);
		String[] tkMsgsrcValues = strMsgsrcValues.split(",");
		JsonArray jsaValues = jso.get(keyJso).getAsJsonArray();
		for (int i = 0; i < jsaValues.size(); i++) {
			Boolean b = jsaValues.get(i).getAsBoolean();
			if (b) {
				values.add(Integer.parseInt(tkMsgsrcValues[i]));
			}
		}
		return values;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
