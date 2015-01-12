package com.hdca.logic.action.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.Team;
import com.hdca.domain.page.TeamSearchPage;
import com.hdca.domain.ui.NavModule;
import com.hdca.logic.BaseAction;
import com.hdca.service.IGeoAreaService;
import com.opensymphony.xwork2.Action;

public class TeamListAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamListAction.class.getName());

	// ---------------------------frontend accessor-------------
	private IGeoAreaService gaservice;

	private TeamSearchPage page;

	// ============================= data ============
	private List<Team> teamList;
	private int total;

	/**
	 * HTML response
	 * 
	 * @return
	 */
	public String execute() {
		logger.debug("[TeamListAction] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		String strStart = req.getParameter("start");
		String strLimit = req.getParameter("limit");
		String strPage = req.getParameter("page");
		Integer nStart = Integer.parseInt(strStart);
		Integer nLimit = Integer.parseInt(strLimit);
		Integer nPage = Integer.parseInt(strPage);

		// param parsing
		TeamSearchPage tsPage = new TeamSearchPage();

		tsPage.setStart(nStart);
		tsPage.setLimit(nLimit);
		tsPage.setPage(nPage);

		// output to page
		page = tsPage;

		// query building
		String sCityareaid = req.getParameter("cityareaid");
		if (sCityareaid != null && sCityareaid.length() != 0) {
			tsPage.setCityareaid(Integer.parseInt(sCityareaid));
		}

		String sDistrictAreaids = req.getParameter("districtareaids");
		logger.debug("[TeamListAction] sDistrictAreaids=" + sDistrictAreaids);
		if (sDistrictAreaids != null && sDistrictAreaids.length() != 0) {
			String[] tkDistrictAreaid = sDistrictAreaids.split(",");
			Integer[] districtAreaids = new Integer[tkDistrictAreaid.length];
			for (int i = 0; i < tkDistrictAreaid.length; i++) {
				districtAreaids[i] = Integer.parseInt(tkDistrictAreaid[i]);
			}
			tsPage.setDistrictareaids(districtAreaids);
		}

		String sTypeIds = req.getParameter("typeids");
		if (sTypeIds != null && sTypeIds.length() != 0) {
			String[] tkTypeId = sTypeIds.split(",");
			Integer[] typeIds = new Integer[tkTypeId.length];
			for (int i = 0; i < tkTypeId.length; i++) {
				typeIds[i] = Integer.parseInt(tkTypeId[i]);
			}
			tsPage.setTypeids(typeIds);
		}

		String sNamelike = req.getParameter("namelike");
		tsPage.setNamelike(sNamelike);
		String sCommentlike = req.getParameter("commentlike");
		tsPage.setCommentlike(sCommentlike);

		// add shop record
		// teamList = getSqlSession().selectList(
		// "hdca.TeamMapper.getTeamByPage", page);

		logger.debug("[TeamListAction] sql=" + tsPage.getDatasql());

		teamList = getSqlSession().selectList("hdca.TeamMapper.searchTeam",
				tsPage);

		total = getSqlSession().selectOne("hdca.TeamMapper.getTeamSearchCount",
				tsPage);

//		logger.debug("[TeamListAction] teamList="+teamList);
		
		if (teamList == null || teamList.size() == 0) {
			logger.debug("[TeamListAction] hdca.TeamMapper.getTeamByPage failure: variable teamList is null or empty!");

			teamList = new ArrayList<Team>();

			// resp.setContentType("application/json");
			// try {
			// resp.setCharacterEncoding("UTF-8");
			// resp.getWriter().write("[]");
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

			// return null;

		}

		// java data to json
		// GsonBuilder gb = new GsonBuilder();
		// gb.registerTypeAdapter(Team.class, new TeamProcessor());
		// Gson gson = gb.create();

		// String jsonTeams = gson.toJson(teamList);

		navmodule = NavModule.NAME_SEARCH;

		return Action.SUCCESS;

	}

	public TeamSearchPage getPage() {
		return page;
	}

	public void setPage(TeamSearchPage page) {
		this.page = page;
	}

	public static void main(String[] args) {
		String s = "0,719";
		String[] tks = s.split(",");
		System.out.println("tks length=" + tks.length);

		Integer[] typeIds = new Integer[tks.length];
		for (int i = 0; i < tks.length; i++) {
			typeIds[i] = Integer.parseInt(tks[i]);
			System.out.println("tk=" + typeIds[i]);
		}

	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IGeoAreaService getGaservice() {
		return gaservice;
	}

	public void setGaservice(IGeoAreaService gaservice) {
		this.gaservice = gaservice;
	}
}
