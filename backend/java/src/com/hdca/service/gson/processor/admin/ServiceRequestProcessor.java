package com.hdca.service.gson.processor.admin;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hdca.domain.ServiceRequest;

public class ServiceRequestProcessor implements
		JsonDeserializer<ServiceRequest>, JsonSerializer<ServiceRequest> {
	public JsonElement serialize(ServiceRequest s, Type arg1,
			JsonSerializationContext ctx) {
		JsonObject jso = new JsonObject();
		jso.addProperty("id", s.getId());
		jso.addProperty("type", s.getType());
		jso.addProperty("customername", s.getCustomername());
		jso.addProperty("teamname", s.getTeamname());

		// for display
		jso.addProperty("request", s.getRequest().toString());

		// property of array type
		// jso.addProperty(
		// "styles",
		// s.getStyles() == null ? null : Joiner.on(
		// IntArrayTypeHandler.SEPARATOR).join(s.getStyles()));
		return jso;
	}

	public ServiceRequest deserialize(JsonElement jse, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jso = jse.getAsJsonObject();
		ServiceRequest s = new ServiceRequest();
		s.setId(jso.get("id") == null ? 0 : jso.get("id").getAsLong());
		s.setType(jso.get("type") == null ? 0 : jso.get("type").getAsInt());
		s.setCustomername(jso.get("customername") == null ? null : jso.get(
				"customername").getAsString());
		s.setTeamname(jso.get("teamname") == null ? null : jso.get("teamname")
				.getAsString());
		s.setRequest(jso.get("request") == null ? null : jso.get("request")
				.getAsString());
		return s;
	}
}
