package com.hdca.service.gson.processor;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hdca.domain.Customer;

public class CustomerProcessor implements JsonDeserializer<Customer>,
		JsonSerializer<Customer> {
	public JsonElement serialize(Customer s, Type arg1,
			JsonSerializationContext ctx) {
		JsonObject jso = new JsonObject();
		jso.addProperty("id", s.getId());
		jso.addProperty("nickname", s.getNickname());
		jso.addProperty("mobile", s.getMobile());
		jso.addProperty("email", s.getEmail());
		jso.addProperty("districtareaid", s.getDistrictareaid());
		// jso.addProperty("pswhash", s.getPswhash());
		// jso.addProperty("password", s.getPassword());

		return jso;
	}

	public Customer deserialize(JsonElement jse, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jso = jse.getAsJsonObject();
		Customer s = new Customer();
		if(jso.get("id") != null)	{
			s.setId(jso.get("id").getAsLong());
		}
		s.setNickname(jso.get("nickname") == null ? null : jso.get("nickname")
				.getAsString());
		s.setMobile(jso.get("mobile") == null ? null : jso.get("mobile")
				.getAsString());
		s.setEmail(jso.get("email") == null ? null : jso.get("email")
				.getAsString());
		s.setDistrictareaid(jso.get("districtareaid") == null ? 0 : jso.get(
				"districtareaid").getAsInt());
		s.setPassword(jso.get("password") == null ? null : jso.get("password")
				.getAsString());
		return s;
	}
}
