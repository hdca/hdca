package com.hdca.util.test.sourceviewer;

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

public class SourceViewerAction extends ActionSupport {
	private static final Logger logger = LogManager
			.getLogger(SourceViewerAction.class.getName());

	private Team team;

	public String execute() {
		logger.debug("[SourceViewerAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		String fileName = null;
		String pathTest = "/test1127/";

		String nameRelative = req.getParameter("filename");

		if (nameRelative.equalsIgnoreCase("fmpropsexample.html")) {
			fileName = pathTest + nameRelative;
		} else if (nameRelative.equalsIgnoreCase("fmteamsexample.html")) {
			fileName = pathTest + nameRelative;
		}

		BufferedReader in = null;
		try {
//			resp.setContentType("application/json");
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");

			// print the file
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					ServletActionContext.getServletContext().getRealPath(
							fileName)), "UTF8"));

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
