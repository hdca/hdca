package com.hdca.util.test.jqgrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hdca.domain.Team;

public class JqDataAction extends SqlSessionDaoSupport {
	private static final Logger logger = LogManager
			.getLogger(JqDataAction.class.getName());

	String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	Team team;

	public String execute() {
		logger.debug("[JqDataAction][execute] start=====================");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		 Enumeration params = req.getParameterNames();
				 while (params.hasMoreElements()) {
				 String paramName = (String) params.nextElement();
				 logger.debug("[JqDataAction][execute] req k=" + paramName + ", v="
				 + req.getParameter(paramName));
				 }
		
//		StringBuilder buffer = new StringBuilder();
//		BufferedReader reader = null;
//		try {
//			reader = req.getReader();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String line;
//		try {
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String data = buffer.toString();
//		logger.debug("[JqDataAction][execute] post req=" + data);

		String postcode = req.getParameter("type");
		if (postcode == null || postcode.trim().length() == 0)
			postcode = "BS21 7RH";
		System.out.println("Type: " + postcode);
		List<Person> personList = new Data().getData(postcode);

		int totalNumberOfPages = 3;
		int currentPageNumber = 1;
		int totalNumberOfRecords = 48; // All in there are 8 records in our dummy
										// data object

		JqGridData<Person> gridData = new JqGridData<Person>(
				totalNumberOfPages, currentPageNumber, totalNumberOfRecords,
				personList);
		System.out.println("Grid Data: " + gridData.getJsonString());
		try {
			resp.getWriter().write(gridData.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String executeOld() {
		logger.debug("[TeamAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamAction][create] team=" + team);

		// String name = req.getParameter("name");

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
		logger.debug("[TeamAction][create] post req=" + data);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"添加成功111!\"}");
		} catch (IOException e) {
			e.printStackTrace();
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
