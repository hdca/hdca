package com.hdca.util.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hdca.domain.ui.NavModule;
import com.hdca.domain.ui.UIResource;
import com.hdca.service.gson.processor.NavModuleProcessor;

public class ResourceUtil {
	public static UIResource getUiResourceFromFile(File f) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "UTF8"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();

		String line;
		try {

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(sb.toString());

		JsonArray jsaNavModules = jse.getAsJsonObject().get("navmodules")
				.getAsJsonArray();

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(NavModule.class, new NavModuleProcessor());
		Gson gson = gb.create();

		// convert the json string back to object
		NavModule[] navModules = gson
				.fromJson(jsaNavModules, NavModule[].class);

		UIResource uiResource = new UIResource();
		uiResource.setNavModules(Arrays.asList(navModules));

		return uiResource;
	}
}
