package com.hdca.logic.action.team;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.support.MessageSourceAccessor;

import com.hdca.domain.ui.NavModule;
import com.hdca.logic.BaseAction;
import com.hdca.service.IGeoAreaService;
import com.opensymphony.xwork2.Action;

public class TeamRegisterStep1Action extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamRegisterStep1Action.class.getName());

	// ---------------------------frontend accessor-------------
	private IGeoAreaService gaservice;

	// private List<Team> teams;

	public String execute() {

		// teams = new ArrayList<Team>();
		// Team team1 = new Team();
		// team1.setId(1);
		// team1.setName("wanger");
		// team1.setTypes(new Integer[] { 1, 2, 5 });
		// team1.setBasepackages(new Integer[] { 6, 8 });
		// Team team2 = new Team();
		// team2.setId(2);
		// team2.setName("zhangsan");
		// team2.setTypes(new Integer[] { 3, 6 });
		// team2.setBasepackages(new Integer[] { 5, 7, 13 });
		// teams.add(team1);
		// teams.add(team2);

		navmodule = NavModule.NAME_REGISTER;
		
		return Action.SUCCESS;
	}

	public String getStyle() {
		// return "www";
		// return "测试样式";
		return msgsrc.getMessage("style");
	}


	public IGeoAreaService getGaservice() {
		return gaservice;
	}

	public void setGaservice(IGeoAreaService gaservice) {
		this.gaservice = gaservice;
	}

	// public List<Team> getTeams() {
	// return teams;
	// }
	//
	// public void setTeams(List<Team> teams) {
	// this.teams = teams;
	// }

}
