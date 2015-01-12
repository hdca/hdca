package com.hdca.logic.action.team;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.logic.BaseAction;

@Deprecated
public class TeamLogoUploadAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamCreateAction.class.getName());

	public String execute() {
		logger.debug("[TeamCreateAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		return null;
	}

}
