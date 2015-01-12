package com.hdca.logic.action.servicerequest;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hdca.domain.ServiceRequest;
import com.hdca.logic.BaseAction;
import com.hdca.service.IMailService;
import com.hdca.service.IServiceRequestService;
import com.opensymphony.xwork2.ActionSupport;

public class ServiceRequestAction extends BaseAction {

	IServiceRequestService serviceRequestService;
	IMailService mailService;

	private static final String adminemail = "hdca2014@126.com";

	// ===================params==========
	String customername;
	String teamname;

	String housetype;
	String housesize;
	String contactname;
	String contactnumber;
	String comment;

	public String createTeamWantedRequest() {

		ServiceRequest serviceRequest = serviceRequestService
				.createTeamwantedRequest(housetype, housesize, customername,
						contactname, contactnumber, new Date());

		int ret;

		// add team record
		ret = getSqlSession().insert(
				"hdca.ServiceRequestMapper.addServiceRequest", serviceRequest);
		if (ret != 1) {
			logger.debug("[ServiceRequestAction][createTeamWantedRequest] hdca.TeamWantedMapper.addTeamWanted failure: ret="
					+ ret);
			return ActionSupport.ERROR;
		}

		mailService.sendMail(adminemail, "HDCA网站提醒",
				serviceRequest.getRequest(), null);
		
		info = "需求发布成功!";

		return ActionSupport.SUCCESS;

	}

	public String createApplicationRequest() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		ServiceRequest serviceRequest = serviceRequestService
				.createApplicationRequest(customername, teamname, contactname,
						contactnumber, comment, new Date());

		int ret;

		// add team record
		ret = getSqlSession().insert(
				"hdca.ServiceRequestMapper.addServiceRequest", serviceRequest);
		if (ret != 1) {
			logger.debug("[ServiceRequestAction][createApplicationRequest] hdca.TeamWantedMapper.addTeamWanted failure: ret="
					+ ret);
			return null;
		}

		mailService.sendMail(adminemail, "HDCA网站提醒",
				serviceRequest.getRequest(), null);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ \"success\": true, \"msg\": \"添加成功!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String createPricingRequest() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		ServiceRequest serviceRequest = serviceRequestService
				.createPricingRequest(customername, teamname, contactname,
						contactnumber, new Date());

		int ret;

		// add team record
		ret = getSqlSession().insert(
				"hdca.ServiceRequestMapper.addServiceRequest", serviceRequest);
		if (ret != 1) {
			logger.debug("[ServiceRequestAction][createPricingRequest] hdca.TeamWantedMapper.addTeamWanted failure: ret="
					+ ret);
			return null;
		}

		mailService.sendMail(adminemail, "HDCA网站提醒",
				serviceRequest.getRequest(), null);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ \"success\": true, \"msg\": \"添加成功!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// ===================setters and getters==========

	public IServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public void setServiceRequestService(
			IServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getHousetype() {
		return housetype;
	}

	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}

	public String getHousesize() {
		return housesize;
	}

	public void setHousesize(String housesize) {
		this.housesize = housesize;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

}
