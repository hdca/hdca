package com.hdca.logic.action.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.Team;
import com.hdca.domain.query.TeamQuery;
import com.hdca.logic.BaseAction;
import com.hdca.util.TeamPropertyNameFinder;
import com.opensymphony.xwork2.ActionSupport;

public class TeamDetailAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamDetailAction.class.getName());

	private TeamPropertyNameFinder teamPropertyNameFinder;

	private Team team;
	private TeamQuery teamQuery;

	private List<String> myList;

	private String title;

	/**
	 * HTML response
	 * 
	 * @return
	 */
	public String execute() {
		logger.debug("[TeamDetailAction][execute] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamDetailAction][execute] teamid="
				+ teamQuery.getTeamid());

		// get team
		team = getSqlSession().selectOne(
				"hdca.TeamMapper.getTeamDetailByTeamId", teamQuery);
		if (team == null) {
			logger.debug("[TeamCreateAction][execute] hdca.TeamMapper.getTeamById failure: ret=null");
			return ActionSupport.ERROR;
		}

//		List<Integer> districtareaidList = getSqlSession().selectList(
//				"hdca.TeamMapper.getTeamDistrictByTeamId", teamQuery);
//		team.setDistrictareas(districtareaidList);
//		List<String> districtnameList = getSqlSession().selectList(
//				"hdca.TeamMapper.getTeamDistrictNamesByTeamId", teamQuery);
//		team.setDistrictnames(districtnameList);
//
//		List<Integer> typeidList = getSqlSession().selectList(
//				"hdca.TeamMapper.getTeamTypeByTeamId", teamQuery);
//		team.setTypes(typeidList);
//		List<String> typenameList = new ArrayList<String>();
//		for (Integer id : typeidList) {
//			typenameList.add(teamPropertyNameFinder.getTypeName(id));
//		}
//		team.setTypenames(typenameList);
//
//		List<Integer> basepackageidList = getSqlSession().selectList(
//				"hdca.TeamMapper.getTeamBasePackageByTeamId", teamQuery);
//		team.setBasepackages(basepackageidList);
//		List<String> basepackageList = new ArrayList<String>();
//		for (Integer id : basepackageidList) {
//			basepackageList.add(teamPropertyNameFinder.getBasepackageName(id));
//		}
//		team.setBasepackagenames(typenameList);
//
//		List<Integer> pricerangeidList = getSqlSession().selectList(
//				"hdca.TeamMapper.getTeamPriceRangeByTeamId", teamQuery);
//		team.setPriceranges(pricerangeidList);
//		List<String> pricerangenameList = new ArrayList<String>();
//		for (Integer id : pricerangeidList) {
//			pricerangenameList
//					.add(teamPropertyNameFinder.getPricerangeName(id));
//		}
//		team.setPricerangenames(pricerangenameList);
//		logger.debug("[TeamCreateAction][execute] pricerangenameListsize="
//				+ pricerangenameList.size() + ", pricerangeidList size="
//				+ pricerangeidList.size());

		return ActionSupport.SUCCESS;

	}
	
	public String execute2() {
		logger.debug("[TeamDetailAction][execute] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamDetailAction][execute] teamid="
				+ teamQuery.getTeamid());

		// get team
		team = getSqlSession().selectOne(
				"hdca.TeamMapper.getTeamBasicInfoByTeamId", teamQuery);
		if (team == null) {
			logger.debug("[TeamCreateAction][execute] hdca.TeamMapper.getTeamById failure: ret=null");
			return ActionSupport.ERROR;
		}

		List<Integer> districtareaidList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamDistrictByTeamId", teamQuery);
		team.setDistrictareas(districtareaidList);
		List<String> districtnameList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamDistrictNamesByTeamId", teamQuery);
		team.setDistrictnames(districtnameList);

		List<Integer> typeidList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamTypeByTeamId", teamQuery);
		team.setTypes(typeidList);
		List<String> typenameList = new ArrayList<String>();
		for (Integer id : typeidList) {
			typenameList.add(teamPropertyNameFinder.getTypeName(id));
		}
		team.setTypenames(typenameList);

		List<Integer> basepackageidList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamBasePackageByTeamId", teamQuery);
		team.setBasepackages(basepackageidList);
		List<String> basepackageList = new ArrayList<String>();
		for (Integer id : basepackageidList) {
			basepackageList.add(teamPropertyNameFinder.getBasepackageName(id));
		}
		team.setBasepackagenames(typenameList);

		List<Integer> pricerangeidList = getSqlSession().selectList(
				"hdca.TeamMapper.getTeamPriceRangeByTeamId", teamQuery);
		team.setPriceranges(pricerangeidList);
		List<String> pricerangenameList = new ArrayList<String>();
		for (Integer id : pricerangeidList) {
			pricerangenameList
					.add(teamPropertyNameFinder.getPricerangeName(id));
		}
		team.setPricerangenames(pricerangenameList);
		logger.debug("[TeamCreateAction][execute] pricerangenameListsize="
				+ pricerangenameList.size() + ", pricerangeidList size="
				+ pricerangeidList.size());

		return ActionSupport.SUCCESS;

	}

	public String oldexecute() {
		logger.debug("[TeamDetailAction][execute] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamDetailAction][execute] teamid="
				+ teamQuery.getTeamid());

		// get team
		team = getSqlSession().selectOne("hdca.TeamMapper.getTeamById",
				teamQuery);
		if (team == null) {
			logger.debug("[TeamCreateAction][create] hdca.TeamMapper.getTeamById failure: ret=null");
			return ActionSupport.ERROR;
		}

		return ActionSupport.SUCCESS;

	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<String> getMyList() {
		return myList;
	}

	public void setMyList(List<String> myList) {
		this.myList = myList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TeamQuery getTeamQuery() {
		return teamQuery;
	}

	public void setTeamQuery(TeamQuery teamQuery) {
		this.teamQuery = teamQuery;
	}

	public TeamPropertyNameFinder getTeamPropertyNameFinder() {
		return teamPropertyNameFinder;
	}

	public void setTeamPropertyNameFinder(
			TeamPropertyNameFinder teamPropertyNameFinder) {
		this.teamPropertyNameFinder = teamPropertyNameFinder;
	}

}
