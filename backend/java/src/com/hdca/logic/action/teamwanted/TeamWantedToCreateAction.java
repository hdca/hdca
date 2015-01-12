package com.hdca.logic.action.teamwanted;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hdca.domain.ui.NavModule;
import com.hdca.logic.BaseAction;
import com.hdca.service.IGeoAreaService;
import com.opensymphony.xwork2.Action;

public class TeamWantedToCreateAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamWantedToCreateAction.class.getName());

	// ---------------------------frontend accessor-------------
	private IGeoAreaService gaservice;

	public String execute() {

		navmodule = NavModule.NAME_REGISTER;
		return Action.SUCCESS;
	}

	public IGeoAreaService getGaservice() {
		return gaservice;
	}

	public void setGaservice(IGeoAreaService gaservice) {
		this.gaservice = gaservice;
	}

}
