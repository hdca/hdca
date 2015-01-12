package com.hdca;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class TestServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		System.out.println("=====================> doPost: req=" + data);

		Enumeration names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Enumeration values = req.getHeaders(name); // support multiple
														// values
			if (values != null) {
				while (values.hasMoreElements()) {
					String value = (String) values.nextElement();
					System.out.println(name + ": " + value);
				}
			}
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("=====================> doGet: req=");

		// logger.debug("[TeamLogoAction][execute] start");

		// HttpServletRequest req = ServletActionContext.getRequest();
		// HttpServletResponse resp = ServletActionContext.getResponse();

		System.out.println("[TeamLogoAction][execute] start, withext="
				+ req.getRequestURI());

		// String folder = "/root/hjmalldata/commimages";

		// String strActionName = ActionContext.getContext().getName();
		// String[] tkActionName = strActionName.split("/");
		// String strTeamId = tkActionName[tkActionName.length - 1];
		// long teamId = Long.parseLong(strTeamId);

		String strUri = req.getRequestURI();
		String[] tkUri = strUri.split("/");
		String fnImage = tkUri[tkUri.length - 1];
		// long teamId = Long.parseLong(strTeamId);

		// logger.debug("[TeamLogoAction][execute] teamid=" + strTeamId);

		System.out.println("[TeamLogoAction][execute] start, fnImage="
				+ fnImage);

		resp.setContentType("image/jpeg");
		// resp.setContentLength(imageBytes.length);

		try { //
				// InputStream is = new FileInputStream(folder + "/" + serial +
				// ".jpg");
			InputStream is = new FileInputStream("E:/temp/hdcatemp/ifnotdebug/"
					+ fnImage);
			OutputStream os = resp.getOutputStream();
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0)
				os.write(buffer, 0, count);
			os.close();
			is.close();

		} catch (Exception e) {
		}

	}

}
