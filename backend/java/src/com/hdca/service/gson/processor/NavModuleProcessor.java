package com.hdca.service.gson.processor;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hdca.domain.ui.NavModule;

public class NavModuleProcessor implements JsonDeserializer<NavModule>,
		JsonSerializer<NavModule> {
	public JsonElement serialize(NavModule s, Type arg1,
			JsonSerializationContext ctx) {
		JsonObject jso = new JsonObject();
		jso.addProperty("id", s.getId());
		jso.addProperty("name", s.getName());
		jso.addProperty("text", s.getText());
		jso.addProperty("href", s.getHref());
		jso.addProperty("hidewhenlogin", s.isHidewhenlogin());

		return jso;
	}

	public NavModule deserialize(JsonElement jse, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jso = jse.getAsJsonObject();
		NavModule s = new NavModule();
		s.setId(jso.get("id") == null ? 0 : jso.get("id").getAsInt());
		s.setName(jso.get("name") == null ? null : jso.get("name")
				.getAsString());
		s.setText(jso.get("text") == null ? null : jso.get("text")
				.getAsString());
		s.setHref(jso.get("href") == null ? null : jso.get("href")
				.getAsString());
		s.setHidewhenlogin(jso.get("hidewhenlogin") == null ? false : jso.get(
				"hidewhenlogin").getAsBoolean());
		return s;
	}
}
