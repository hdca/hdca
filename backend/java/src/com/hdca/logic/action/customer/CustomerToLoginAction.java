package com.hdca.logic.action.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hdca.domain.ui.NavModule;
import com.hdca.logic.BaseAction;
import com.opensymphony.xwork2.Action;

public class CustomerToLoginAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(CustomerToLoginAction.class.getName());

	public String execute() {

		navmodule = NavModule.NAME_LOGIN;
		return Action.SUCCESS;
	}

}
