package com.hdca.logic.action.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

import com.hdca.domain.Customer;
import com.hdca.logic.BaseAction;

public class CustomerLoginAction extends BaseAction {
	private static final long serialVersionUID = -3369875299120377549L;
	private static final Logger logger = LogManager
			.getLogger(CustomerLoginAction.class.getName());

	Customer customer;

	public String execute() {
		// System.out.println("inside execute");
		logger.debug("[LoginAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();

		logger.debug("[LoginAction][execute] email=" + customer.getEmail());
		Customer customerFetched = getSqlSession().selectOne(
				"hdca.CustomerMapper.getCustomerByEmail", customer);
		logger.debug("[LoginAction][execute] fetched: nickname="
				+ customerFetched.getNickname() + ",pswhash=" + customerFetched.getPswhash());

		if (BCrypt.checkpw(customer.getPassword(), customerFetched.getPswhash())) {
			getSession().put("customer", customer);
			info="登录成功!";
			return "success";
		}
		return "failure";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
