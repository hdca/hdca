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

public class CustomerPswResetAction extends BaseAction {
	private static final long serialVersionUID = -3369875299120377549L;
	private static final Logger logger = LogManager
			.getLogger(CustomerPswResetAction.class.getName());

	String token;
	String email;

	public String execute() {
		logger.debug("[CustomerPswResetAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();

		logger.debug("[CustomerPswResetAction][execute] token=" + token);

		PswResetToken pswResetTokenQuery = new PswResetToken();
		pswResetTokenQuery.setToken(token);

		PswResetToken pswResetTokenToCheck = getSqlSession().selectOne(
				"hdca.PswRestTokenMapper.getPswRestToken", pswResetTokenQuery);
		logger.debug("[CustomerPswResetAction][execute] hdca.PswRestTokenMapper.getPswRestToken: ret="
				+ pswResetTokenToCheck);
		if (pswResetTokenToCheck != null) {
			logger.debug("[CustomerPswResetAction][execute] hdca.PswRestTokenMapper.getPswRestToken: email found="
					+ pswResetTokenToCheck.getName());
			email = pswResetTokenToCheck.getName();
			return ActionSupport.SUCCESS;
		} else {
			info = "密码重置失败，请确认重置链接是否正确和有效!";
			return "failure";
		}

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
