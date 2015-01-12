package com.hdca.logic.pub.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hdca.domain.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerCreateAction extends SqlSessionDaoSupport {
	private static final Logger logger = LogManager
			.getLogger(CustomerCreateAction.class.getName());

	String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	Customer customer;

	/**
	 * HTML response
	 * 
	 * @return
	 */
	public String execute() {
		logger.debug("[CustomerCreateAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[CustomerCreateAction][create] customer=" + customer);

		String pswhash = BCrypt
				.hashpw(customer.getPassword(), BCrypt.gensalt());
		customer.setPswhash(pswhash);

		int ret;

		// add customer record
		ret = getSqlSession().insert("hdca.CustomerMapper.addCustomer",
				customer);
		if (ret != 1) {
			logger.debug("[CustomerCreateAction][create] hdca.CustomerMapper.addCustomer failure: ret="
					+ ret);
			return ActionSupport.ERROR;
		}
		logger.debug("[CustomerCreateAction][create] customer added, generated id="
				+ customer.getId());

		return ActionSupport.SUCCESS;

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
