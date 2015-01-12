package com.hdca.service.gson.processor;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hdca.domain.GeoArea;

public class GeoAreaProcessor implements JsonDeserializer<GeoArea>,
		JsonSerializer<GeoArea> {

	public GeoArea deserialize(JsonElement jse, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		// TODO use final keyword?
		JsonObject jso = jse.getAsJsonObject();
		GeoArea s = new GeoArea();

		s.setAreaid(jso.get("areaid") == null ? 0 : jso.get("areaid")
				.getAsInt());
		s.setParentid(jso.get("parentid") == null ? 0 : jso.get("parentid")
				.getAsInt());
		s.setName(jso.get("name") == null ? null : jso.get("name")
				.getAsString());
		s.setType(jso.get("type") == null ? 0 : jso.get("type").getAsInt());

		return s;
	}

	public JsonElement serialize(GeoArea s, Type arg1,
			JsonSerializationContext ctx) {
		// TODO use final keyword?
		JsonObject jso = new JsonObject();

		jso.addProperty("areaid", s.getAreaid());
		jso.addProperty("parentid", s.getParentid());
		jso.addProperty("name", s.getName());
		jso.addProperty("type", s.getType());
		return jso;
	}
}
