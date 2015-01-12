package com.hdca.logic.admin.action.team;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdca.domain.Customer;
import com.hdca.domain.Team;
import com.hdca.domain.TeamBasePackage;
import com.hdca.domain.TeamDistrict;
import com.hdca.domain.TeamPriceRange;
import com.hdca.domain.TeamType;
import com.hdca.domain.page.Page;
import com.hdca.domain.query.TeamQuery;
import com.hdca.logic.BaseAction;
import com.hdca.service.ITeamLogoService;
import com.hdca.service.gson.processor.admin.TeamProcessor;
import com.opensymphony.xwork2.ActionSupport;

public class TeamAction extends BaseAction {
	private static final Logger logger = LogManager.getLogger(TeamAction.class
			.getName());

	int start;
	int limit;
	int page;

	private ITeamLogoService teamLogoService;

	// ===================params==========
	public String read() {
		logger.debug("[TeamAction][read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamAction][read] param: start=" + start + ", limit="
				+ limit + ",page=" + page);

		Page p = new Page();
		p.setStart(start);
		p.setLimit(limit);
		p.setPage(page);

		// get total count
		Integer count = (Integer) getSqlSession().selectOne(
				"hdca.TeamMapper.getTeamCount");
		logger.debug("[TeamAction][read] count=" + count);

		// get items
		List<Team> teamList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamByPage", p);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class, new TeamProcessor(msgsrc,
				getSqlSession()));
		Gson gson = gb.create();

		String jsonCommodities = gson.toJson(teamList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonCommodities).append("}");

		// ---------------

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String read2() {
		logger.debug("[TeamAction][read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamAction][read] param: start=" + start + ", limit="
				+ limit + ",page=" + page);

		Page p = new Page();
		p.setStart(start);
		p.setLimit(limit);
		p.setPage(page);

		// get total count
		Integer count = (Integer) getSqlSession().selectOne(
				"hdca.TeamMapper.getTeamCount");
		logger.debug("[TeamAction][read] count=" + count);

		// get items
		List<Team> teamList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamBasicInfoByPage", p);

		TeamQuery teamQuery = new TeamQuery();
		for (Team t : teamList) {
			teamQuery.setTeamid(t.getId());
			List<Integer> districtareaidList = getSqlSession().selectList(
					"hdca.TeamMapper.getTeamDistrictByTeamId", teamQuery);
			t.setDistrictareas(districtareaidList);

			List<Integer> typeidList = getSqlSession().selectList(
					"hdca.TeamMapper.getTeamTypeByTeamId", teamQuery);
			t.setTypes(typeidList);

			List<Integer> basepackageidList = getSqlSession().selectList(
					"hdca.TeamMapper.getTeamBasePackageByTeamId", teamQuery);
			t.setBasepackages(basepackageidList);

			List<Integer> pricerangeidList = getSqlSession().selectList(
					"hdca.TeamMapper.getTeamPriceRangeByTeamId", teamQuery);
			t.setPriceranges(pricerangeidList);
		}

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class, new TeamProcessor(msgsrc,
				getSqlSession()));
		Gson gson = gb.create();

		String jsonCommodities = gson.toJson(teamList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonCommodities).append("}");

		// ---------------

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String read1() {
		logger.debug("[TeamAction][read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamAction][read] param: start=" + start + ", limit="
				+ limit + ",page=" + page);

		Page p = new Page();
		p.setStart(start);
		p.setLimit(limit);
		p.setPage(page);

		// get total count
		Integer count = (Integer) getSqlSession().selectOne(
				"hdca.TeamMapper.getTeamCount");
		logger.debug("[TeamAction][read] count=" + count);

		// get items
		List<Customer> customerList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamByPage", p);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class, new TeamProcessor(msgsrc,
				getSqlSession()));
		Gson gson = gb.create();

		String jsonCommodities = gson.toJson(customerList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonCommodities).append("}");

		// ---------------

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -------------------------------------

	public String create() {
		logger.debug("[TeamCreateAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = sb.toString();
		logger.debug("[TeamCreateAction][create] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class, new TeamProcessor(getMsgsrc(),
				getSqlSession()));
		Gson gson = gb.create();

		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);
		// single object
		JsonObject jsoTeam = jse.getAsJsonObject();
		Team team = gson.fromJson(jsoTeam, Team.class);

		logger.debug("[TeamCreateAction][create] team=" + team + ", logo="
				+ logo + ", logo.fn=" + logoFileName);

		int ret;

		// add team record
		ret = getSqlSession().insert("hdca.TeamMapper.addTeam", team);
		if (ret != 1) {
			logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeam failure: ret="
					+ ret);
			return ActionSupport.ERROR;
		}
		logger.debug("[TeamCreateAction][create] team added, generated id="
				+ team.getId());

		// add team districtareas
		for (Integer daid : team.getDistrictareas()) {
			TeamDistrict tdTemp = new TeamDistrict();
			tdTemp.setDistrictareaid(daid);
			tdTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamDistrict",
					tdTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamDistrict failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// add team types
		for (Integer tpid : team.getTypes()) {
			TeamType ttTemp = new TeamType();
			ttTemp.setTypeid(tpid);
			ttTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamType", ttTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamType failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// add basepackages
		for (Integer tbpid : team.getBasepackages()) {
			TeamBasePackage tbpTemp = new TeamBasePackage();
			tbpTemp.setBasepackageid(tbpid);
			tbpTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamBasePackage",
					tbpTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamBasePackage failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// add team priceranges
		for (Integer prid : team.getPriceranges()) {
			TeamPriceRange tpTemp = new TeamPriceRange();
			tpTemp.setPricerangeid(prid);
			tpTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamPriceRange",
					tpTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamPriceRange failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// ----------file upload start
		try { //
			InputStream is = new FileInputStream(logo);
			// OutputStream os = new FileOutputStream(folder + "/" + serial
			// + ".jpg");
			OutputStream os = new FileOutputStream(
					teamLogoService.getTeamLogoPath(team.getId()));
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0)
				os.write(buffer, 0, count);
			os.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ----------file upload end

		// info = "注册成功!";

		return null;

	}

	public String update() {
		logger.debug("[TeamCreateAction][update] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = sb.toString();
		logger.debug("[TeamCreateAction][update] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class, new TeamProcessor(getMsgsrc(),
				getSqlSession()));
		Gson gson = gb.create();

		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);
		// single object
		JsonObject jsoTeam = jse.getAsJsonObject();
		Team team = gson.fromJson(jsoTeam, Team.class);

		logger.debug("[TeamCreateAction][update] team=" + team + ", logo="
				+ logo + ", logo.fn=" + logoFileName);

		int ret;

		// add team record
		ret = getSqlSession().update("hdca.TeamMapper.updateTeam", team);
		if (ret != 1) {
			logger.debug("[TeamCreateAction][update] hdca.TeamMapper.update failure: ret="
					+ ret);
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
		logger.debug("[TeamCreateAction][update] team updated, id="
				+ team.getId());

		TeamQuery teamQuery = new TeamQuery();
		teamQuery.setTeamid(team.getId());

		// clear team types
		ret = getSqlSession().delete("hdca.TeamMapper.clearTeamTypes",
				teamQuery);

		// add team types
		for (Integer tpid : team.getTypes()) {
			TeamType ttTemp = new TeamType();
			ttTemp.setTypeid(tpid);
			ttTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamType", ttTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamType failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// clear team BasePackages
		ret = getSqlSession().delete("hdca.TeamMapper.clearTeamBasePackages",
				teamQuery);

		// add team BasePackages
		for (Integer tbpid : team.getBasepackages()) {
			TeamBasePackage tbpTemp = new TeamBasePackage();
			tbpTemp.setBasepackageid(tbpid);
			tbpTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamBasePackage",
					tbpTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamBasePackage failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// clear team types
		ret = getSqlSession().delete("hdca.TeamMapper.clearTeamPriceRanges",
				teamQuery);

		// add team types
		for (Integer prid : team.getPriceranges()) {
			TeamPriceRange tpTemp = new TeamPriceRange();
			tpTemp.setPricerangeid(prid);
			tpTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamPriceRange",
					tpTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamPriceRange failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"ok!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// info = "注册成功!";

		return null;

	}

	public String delete() {
		logger.debug("[TeamAction][delete] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

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
		logger.debug("[TeamAction][delete] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class,
				new com.hdca.service.gson.processor.TeamProcessor());
		Gson gson = gb.create();

		Team teamToDelete = null;
		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);

		// single object
		JsonObject jsoPutawayDetail = jse.getAsJsonObject();
		teamToDelete = gson.fromJson(jsoPutawayDetail, Team.class);

		int ret = getSqlSession().delete("hdca.TeamMapper.deleteTeam",
				teamToDelete);

		if (ret != 1) {
			logger.error("[TeamAction][delete] failure: id="
					+ teamToDelete.getId());
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{ success: false, msg: \"删除失败!\"}");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		logger.debug("[TeamAction][delete] after delete");

		TeamQuery teamQuery = new TeamQuery();
		teamQuery.setTeamid(teamToDelete.getId());

		// clear team types
		ret = getSqlSession().delete("hdca.TeamMapper.clearTeamTypes",
				teamQuery);

		// clear team BasePackages
		ret = getSqlSession().delete("hdca.TeamMapper.clearTeamBasePackages",
				teamQuery);

		// clear team types
		ret = getSqlSession().delete("hdca.TeamMapper.clearTeamPriceRanges",
				teamQuery);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"ok!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String setad() {
		logger.debug("[TeamAction][setad] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

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
		logger.debug("[setad][setad] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Team.class,
				new com.hdca.service.gson.processor.TeamProcessor());
		Gson gson = gb.create();

		Team customer = null;
		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);

		// single object
		JsonObject jsoCustomer = jse.getAsJsonObject();
		customer = gson.fromJson(jsoCustomer, Team.class);

		int ret = getSqlSession().update(
				"hdca.TeamMapper.updateTeamAdid", customer);
		if (ret != 1) {
			logger.debug("[TeamAction][setad] failure: teamid="
					+ customer.getId());
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{ success: false, msg: \"setad失败!\"}");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("[CommodityAction][DMI] update --- ret=" + ret);

		logger.debug("[TeamAction][setad] after update, teamid="+customer.getId());

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"ok!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String uploadLogo() {
		logger.debug("[TeamAction][uploadLogo] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		String sTeamid = req.getParameter("id");
		Long teamid = Long.parseLong(sTeamid);
		// ----------file upload start
		try { //
			InputStream is = new FileInputStream(logo);
			// OutputStream os = new FileOutputStream(folder + "/" + serial
			// + ".jpg");
			OutputStream os = new FileOutputStream(
					teamLogoService.getTeamLogoPath(teamid));
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0)
				os.write(buffer, 0, count);
			os.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ----------file upload end

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"www\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ITeamLogoService getTeamLogoService() {
		return teamLogoService;
	}

	public void setTeamLogoService(ITeamLogoService teamLogoService) {
		this.teamLogoService = teamLogoService;
	}

	// ---------------- file upload stuff -------------
	private File logo;
	private String logoContentType;
	private String logoFileName;

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		System.out.println("in set logo!");
		this.logo = logo;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		System.out.println("in set setLogoContentType!");
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		System.out.println("in set setLogoFileName!");
		this.logoFileName = logoFileName;
	}

}
