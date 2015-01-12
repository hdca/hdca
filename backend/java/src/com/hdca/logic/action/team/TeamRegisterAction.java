package com.hdca.logic.action.team;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hdca.domain.Team;
import com.hdca.domain.ui.NavModule;
import com.hdca.logic.BaseAction;
import com.opensymphony.xwork2.Action;

public class TeamRegisterAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamRegisterAction.class.getName());

	private List<Team> teams;

	public String execute() {

		navmodule = NavModule.NAME_REGISTER;
		// add your login procedure here...
		return Action.SUCCESS;
	}

	public String getStyle() {
		// return "www";
		// return "测试样式";
		return msgsrc.getMessage("style");
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
