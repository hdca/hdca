package com.hdca;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hdca.domain.Team;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(TestAction.class
			.getName());
	
	private Team team;

	public String execute() {
        
        // add your login procedure here...
		logger.debug("[TeamAction][create] team=" + team);
        return SUCCESS;
    }

	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
