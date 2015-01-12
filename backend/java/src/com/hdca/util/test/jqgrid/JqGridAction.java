package com.hdca.util.test.jqgrid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.Team;
import com.opensymphony.xwork2.ActionSupport;

public class JqGridAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(JqGridAction.class
			.getName());

	private Team team;

	public String execute() {
		logger.debug("[NotesAction][create] start");

		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		BufferedReader in = null;
		try {
			// resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			// print the file
			in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									ServletActionContext
											.getServletContext()
											.getRealPath(
													"/WEB-INF/classes/com/hdca/util/test/jqgrid/index.html")),
							"UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				resp.getWriter().println(str);
			}

			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();// very important
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
