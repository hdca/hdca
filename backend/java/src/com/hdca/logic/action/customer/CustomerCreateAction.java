package com.hdca.logic.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

import com.hdca.domain.Customer;
import com.hdca.logic.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerCreateAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(CustomerCreateAction.class.getName());

	private Customer customer;

	public String execute() {
		logger.debug("[CustomerCreateAction][execute] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[CustomerCreateAction][execute] customer=" + customer);

		String pswhash = BCrypt
				.hashpw(customer.getPassword(), BCrypt.gensalt());
		customer.setPswhash(pswhash);
		
		int ret;

		// add customer record
		ret = getSqlSession().insert("hdca.CustomerMapper.addCustomer",
				customer);
		if (ret != 1) {
			logger.debug("[CustomerCreateAction][execute] hdca.TeamMapper.addTeam failure: ret="
					+ ret);
			return ActionSupport.ERROR;
		}
		logger.debug("[CustomerCreateAction][execute] team added, generated id="
				+ customer.getId());

		info = "注册成功!";
		
		return ActionSupport.SUCCESS;

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
