package com.hdca.logic.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hdca.domain.Team;

public class TeamAction extends SqlSessionDaoSupport {
	private static final Logger logger = LogManager.getLogger(TeamAction.class
			.getName());

	String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	Team team;

	public String execute() {
		logger.debug("[TeamAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		// Enumeration params = req.getParameterNames();
		// while (params.hasMoreElements()) {
		// String paramName = (String) params.nextElement();
		// logger.debug("[TeamAction][create] req k=" + paramName + ", v len="
		// + req.getParameterValues(paramName).length);
		// }

		logger.debug("[TeamAction][create] team=" + team);

		String name = req.getParameter("name");
		String contactname = req.getParameter("contactname");
		String mobile = req.getParameter("mobile");
		String email = req.getParameter("email");

		Team teamNew = new Team();
		teamNew.setName(name);
		teamNew.setContactname(contactname);
		teamNew.setMobile(mobile);
		teamNew.setEmail(email);

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
		// logger.debug("[TeamAction][create] post req=" + data);

		// GsonBuilder gb = new GsonBuilder();
		// gb.registerTypeAdapter(Team.class, new TeamProcessor());
		// Gson gson = gb.create();

		// JsonParser parser = new JsonParser();
		// JsonElement jse = parser.parse(data);
		// single object
		// JsonObject jsoCommodity = jse.getAsJsonObject();
		// Team team = gson.fromJson(jsoCommodity, Team.class);

		int ret;

		// add shop record
		ret = getSqlSession().insert("hjmall.TeamMapper.addTeam", teamNew);
		if (ret != 1) {
			logger.debug("[TeamAction][create] hjmall.TeamMapper.addTeam failure: ret="
					+ ret);
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{ success: false, msg: \"添加失败!\"}");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
