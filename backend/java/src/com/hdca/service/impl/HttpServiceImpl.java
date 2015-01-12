package com.hdca.service.impl;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.hdca.service.IHttpService;

public class HttpServiceImpl implements IHttpService {
	public String getRequestBody(HttpServletRequest req) {

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = buffer.toString();

		return data;
	}
}
