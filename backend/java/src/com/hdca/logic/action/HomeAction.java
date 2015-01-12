package com.hdca.logic.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.ServiceRequest;
import com.hdca.domain.Team;
import com.hdca.logic.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends BaseAction {
	private static final Logger logger = LogManager.getLogger(HomeAction.class
			.getName());

	private List<Team> teamAdList;
	private List<ServiceRequest> serviceRequestList;

	public String execute() {
		logger.debug("[HomeAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		teamAdList = getSqlSession().selectList("hdca.TeamMapper.getTeamAd");

		serviceRequestList = getSqlSession().selectList(
				"hdca.ServiceRequestMapper.getServiceRequestForNews");

		return ActionSupport.SUCCESS;
	}

	public List<Team> getTeamAdList() {
		return teamAdList;
	}

	public void setTeamAdList(List<Team> teamAdList) {
		this.teamAdList = teamAdList;
	}

	public List<ServiceRequest> getServiceRequestList() {
		return serviceRequestList;
	}

	public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
		this.serviceRequestList = serviceRequestList;
	}
}
