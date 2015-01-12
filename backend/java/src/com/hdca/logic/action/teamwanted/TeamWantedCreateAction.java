package com.hdca.logic.action.teamwanted;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.TeamWanted;
import com.hdca.logic.BaseAction;
import com.hdca.service.IMailService;
import com.opensymphony.xwork2.ActionSupport;

public class TeamWantedCreateAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamWantedCreateAction.class.getName());


	TeamWanted teamWanted;
	IMailService mailService;


	/**
	 * HTML response
	 * 
	 * @return
	 */
	public String execute() {
		logger.debug("[TeamWantedCreateAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamWantedCreateAction][create] teamWanted=" + teamWanted);

		int ret;

		// add team record
		ret = getSqlSession().insert("hdca.TeamWantedMapper.addTeamWanted", teamWanted);
		if (ret != 1) {
			logger.debug("[TeamWantedCreateAction][create] hdca.TeamWantedMapper.addTeamWanted failure: ret="
					+ ret);
			return ActionSupport.ERROR;
		}
		logger.debug("[TeamWantedCreateAction][create] team added, generated id="
				+ teamWanted.getId());
		
//		mailService.sendMail("jinrift@126.com", teamWanted.toString(), resp);

		return ActionSupport.SUCCESS;

	}


	public TeamWanted getTeamWanted() {
		return teamWanted;
	}

	public void setTeamWanted(TeamWanted teamWanted) {
		this.teamWanted = teamWanted;
	}


	public IMailService getMailService() {
		return mailService;
	}


	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

}
