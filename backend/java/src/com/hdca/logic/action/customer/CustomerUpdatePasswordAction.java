package com.hdca.logic.action.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

import com.hdca.domain.Customer;
import com.hdca.logic.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerUpdatePasswordAction extends BaseAction {
	private static final long serialVersionUID = -3369875299120377549L;
	private static final Logger logger = LogManager
			.getLogger(CustomerUpdatePasswordAction.class.getName());

	Customer customer;

	public String execute() {
		logger.debug("[CustomerUpdatePasswordAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();

		logger.debug("[CustomerUpdatePasswordAction][execute] customer email="
				+ customer.getEmail());

		String pswhash = BCrypt
				.hashpw(customer.getPassword(), BCrypt.gensalt());
		customer.setPswhash(pswhash);

		int ret = getSqlSession().update(
				"hdca.CustomerMapper.updateCustomerPswhash", customer);
		logger.debug("[CustomerUpdatePasswordAction][execute] hdca.CustomerMapper.updateCustomerPswhash: ret="
				+ ret);
		if (ret == 1) {
			logger.debug("[CustomerUpdatePasswordAction][execute] hdca.CustomerMapper.updateCustomerPswhash ok");
			info = "密码重置成功!";
			return ActionSupport.SUCCESS;
		} else {
			info = "密码重置失败，请确认重置链接是否正确和有效!";
			return "failure";
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
