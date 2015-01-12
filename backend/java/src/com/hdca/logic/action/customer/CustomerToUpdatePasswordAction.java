package com.hdca.logic.action.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.Customer;
import com.hdca.domain.PswResetToken;
import com.hdca.logic.BaseAction;
import com.hdca.service.IMailService;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerToUpdatePasswordAction extends BaseAction {
	private static final long serialVersionUID = -3369875299120377549L;
	private static final Logger logger = LogManager
			.getLogger(CustomerToUpdatePasswordAction.class.getName());

	String email;

	public String execute() {
		logger.debug("[CustomerToUpdatePasswordAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();

		logger.debug("[CustomerToUpdatePasswordAction][execute] customer email="
				+ email);

		return ActionSupport.SUCCESS;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
