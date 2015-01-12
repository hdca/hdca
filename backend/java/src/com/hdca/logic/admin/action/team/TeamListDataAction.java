package com.hdca.logic.admin.action.team;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdca.domain.Team;
import com.hdca.domain.jqgrid.JqGridPage;
import com.hdca.domain.jqgrid.JqGridResult;
import com.hdca.domain.page.TeamSearchPage;
import com.hdca.logic.BaseAction;
import com.hdca.service.IJqGridPagingService;
import com.hdca.service.gson.processor.admin.TeamProcessor;

public class TeamListDataAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamListDataAction.class.getName());

	private IJqGridPagingService jqGridPagingService;

	String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	Team team;

	public String executeTest() {
		logger.debug("[TeamListDataAction][execute] start=====================");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		String s = "{\"page\":\"1\",\"total\":431,\"records\":2155,\"rows\":[{\"OrderID\":\"1024811\",\"UnitPrice\":\"14.0000\",\"Quantity\":\"12\",\"Discount\":\"0\"},{\"OrderID\":\"1024842\",\"UnitPrice\":\"9.8000\",\"Quantity\":\"10\",\"Discount\":\"0\"},{\"OrderID\":\"1024872\",\"UnitPrice\":\"34.8000\",\"Quantity\":\"5\",\"Discount\":\"0\"},{\"OrderID\":\"1024914\",\"UnitPrice\":\"18.6000\",\"Quantity\":\"9\",\"Discount\":\"0\"},{\"OrderID\":\"1024951\",\"UnitPrice\":\"42.4000\",\"Quantity\":\"40\",\"Discount\":\"0\"}]}";

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --------------

		return null;
	}

	public String execute() {
		logger.debug("[TeamListDataAction][execute] start=====================");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		// req k=type, v=BS21 7RH
		// req k=_search, v=false
		// req k=nd, v=1418181803625
		// req k=rows, v=20
		// req k=page, v=1
		// req k=sidx, v=
		// req k=sord, v=asc

		JqGridPage page = jqGridPagingService.getPageFromHttpRequest(req);

		// Enumeration params = req.getParameterNames();
		// while (params.hasMoreElements()) {
		// String paramName = (String) params.nextElement();
		// logger.debug("[JqDataAction][execute] req k=" + paramName + ", v="
		// + req.getParameter(paramName));
		// }

		// StringBuilder buffer = new StringBuilder();
		// BufferedReader reader = null;
		// try {
		// reader = req.getReader();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// String line;
		// try {
		// while ((line = reader.readLine()) != null) {
		// buffer.append(line);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// String data = buffer.toString();
		// logger.debug("[JqDataAction][execute] post req=" + data);

		// logger.debug("[JqDataAction][execute] page=" + page);

		// get data (list) and format to json
		List<Team> teamList = null;
		TeamSearchPage teamPage = new TeamSearchPage();
		teamPage.setStart((page.getPage() - 1) * page.getRows());
		teamPage.setLimit(page.getRows());

		logger.debug("[JqDataAction][execute] datasql=" + teamPage.getDatasql());

		teamList = getSqlSession().selectList("hdca.TeamMapper.searchTeam",
				teamPage);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class, new TeamProcessor(msgsrc,
				getSqlSession()));
		Gson gson = gb.create();

		String jsonData = gson.toJson(teamList);

		// get count
		int count = getSqlSession().selectOne(
				"hdca.TeamMapper.getTeamSearchCount", teamPage);
		int totalCountOfPage = (int) Math.ceil(count * 1.0 / page.getRows());
		// mmm: wrong

		// build result
		JqGridResult jqGridResult = new JqGridResult();
		jqGridResult.setPage(page.getPage());
		jqGridResult.setTotal(totalCountOfPage);
		jqGridResult.setRecords(count);
		jqGridResult.setJsonData(jsonData);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jqGridResult.toJsonStr());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --------------

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

	public IJqGridPagingService getJqGridPagingService() {
		return jqGridPagingService;
	}

	public void setJqGridPagingService(IJqGridPagingService jqGridPagingService) {
		this.jqGridPagingService = jqGridPagingService;
	}

}
