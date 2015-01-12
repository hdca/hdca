package com.hdca.service.gson.processor;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hdca.domain.Team;

public class TeamProcessor implements JsonDeserializer<Team>,
		JsonSerializer<Team> {
	public JsonElement serialize(Team s, Type arg1, JsonSerializationContext ctx) {
		JsonObject jso = new JsonObject();
		jso.addProperty("id", s.getId());
		jso.addProperty("name", s.getName());
		jso.addProperty("mobile", s.getMobile());
		jso.addProperty("contactname", s.getContactname());
		jso.addProperty("email", s.getEmail());
		jso.addProperty("cityareaid", s.getCityareaid());

		// property of array type
//		jso.addProperty(
//				"styles",
//				s.getStyles() == null ? null : Joiner.on(
//						IntArrayTypeHandler.SEPARATOR).join(s.getStyles()));
		return jso;
	}

	public Team deserialize(JsonElement jse, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jso = jse.getAsJsonObject();
		Team s = new Team();
		s.setId(jso.get("id") == null ? 0 : jso.get("id").getAsLong());
		s.setName(jso.get("name") == null ? null : jso.get("name")
				.getAsString());
		s.setMobile(jso.get("mobile") == null ? null : jso.get("mobile")
				.getAsString());
		s.setContactname(jso.get("contactname") == null ? null : jso.get(
				"contactname").getAsString());
		s.setEmail(jso.get("email") == null ? null : jso.get("email")
				.getAsString());
		s.setCityareaid(jso.get("cityareaid") == null ? 0 : jso.get(
				"cityareaid").getAsInt());
		s.setAdid(jso.get("adid") == null ? 0 : jso.get("adid").getAsInt());
		return s;
	}
}
