package com.hdca.logic.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hdca.service.ITeamLogoService;

public class TeamLogoServlet extends HttpServlet {
	ITeamLogoService teamLogoService;
	private WebApplicationContext ctx;

	public TeamLogoServlet() {
		// teamLogoService = new TeamLogoServiceImpl();
		
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletContext = this.getServletContext();
		this.ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		
		teamLogoService = (ITeamLogoService) ctx.getBean("teamLogoService");
	}

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
		System.out.println("=====================> doGet2222: req=");

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
		// System.out.println("fnImage="+fnImage+",fnImage.split(.).len="+fnImage.split(".").length);
		long teamId = Long.parseLong(fnImage.split("\\.")[0]);

		// logger.debug("[TeamLogoAction][execute] teamid=" + strTeamId);

		System.out.println("[TeamLogoAction][execute] start, fnImage="
				+ fnImage);

		resp.setContentType("image/jpeg");
		// resp.setContentLength(imageBytes.length);

		try { //
				// InputStream is = new FileInputStream(folder + "/" + serial +
				// ".jpg");
			System.out
					.println("[TeamLogoAction][execute] start, image real path="
							+ teamLogoService.getTeamLogoPath(teamId));

			InputStream is = new FileInputStream(
					teamLogoService.getTeamLogoPath(teamId));
			OutputStream os = resp.getOutputStream();
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0)
				os.write(buffer, 0, count);
			os.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String s = "10010.jpg";
		String[] tks = s.split("\\.");
		System.out.println(tks.length);
	}

}
