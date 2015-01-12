package com.hdca.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.io.Resource;

import com.hdca.domain.Customer;
import com.hdca.domain.ui.NavModule;
import com.hdca.domain.ui.UIResource;
import com.hdca.service.IHttpService;
import com.hdca.util.ui.ResourceUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends SqlSessionDaoSupport {
	protected String method;
	protected MessageSourceAccessor msgsrc;
	protected MessageSourceAccessor debugmsgsrc;
	// private Map<String, Object> session = null;

	/**
	 * info to be displayed at the topmost of page
	 */
	protected String info;
	/**
	 * name of module in navigation bar
	 */
	protected String navmodule;

	protected UIResource uiResource;

	public BaseAction() {
		info = " ";
		navmodule = NavModule.NAME_HOME;
	}

	public String execute() {

		return ActionSupport.SUCCESS;
	}

	protected IHttpService httpService;

	public void setHttpService(IHttpService httpService) {
		this.httpService = httpService;
	}

	// -------------------------------
	public Customer getCustomer() {
		Map<String, Object> session = getSession();
		Customer customer = (Customer) session.get("customer");

		return customer;
	}

	public String getCustomerEmail() {
		Map<String, Object> session = getSession();
		// System.out.println("in getCustomerName!+ss=" + session);
		Customer customer = (Customer) session.get("customer");
		// System.out.println("in getCustomerName!+c=" + customer);
		if (customer == null) {
			// System.out.println("customer null!");
			return "null";
		} else {
			return customer.getEmail();
		}
	}

	public void setUiResourceFile(Resource resource) {
		System.out.println("in setFile!");
		try {
			File fileUr = resource.getFile();
			uiResource = ResourceUtil.getUiResourceFromFile(fileUr);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<NavModule> getNavModuleList() {
		return uiResource.getNavModules();
	}

	public Map<String, Object> getSession() {
		return ServletActionContext.getContext().getSession();
	}

	

	

	public String getWebroot() {
		return msgsrc.getMessage("webroot");
	}

	// ---------------------------------

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public MessageSourceAccessor getMsgsrc() {
		return msgsrc;
	}

	public void setMsgsrc(MessageSourceAccessor msgsrc) {
		this.msgsrc = msgsrc;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNavmodule() {
		return navmodule;
	}

	public void setNavmodule(String navmodule) {
		this.navmodule = navmodule;
	}

	public UIResource getUiResource() {
		return uiResource;
	}

	public void setUiResource(UIResource uiResource) {
		this.uiResource = uiResource;
	}

	public MessageSourceAccessor getDebugmsgsrc() {
		return debugmsgsrc;
	}

	public void setDebugmsgsrc(MessageSourceAccessor debugmsgsrc) {
		this.debugmsgsrc = debugmsgsrc;
	}

}
