package com.hdca.service.impl;

import java.util.Date;

import com.hdca.domain.ServiceRequest;
import com.hdca.service.IServiceRequestService;

public class ServiceRequestServiceImpl implements IServiceRequestService {

	@Override
	public ServiceRequest createTeamwantedRequest(String housetype,
			String housesize, String customername, String contactname,
			String contactnumber, Date time) {

		ServiceRequest serviceRequest = new ServiceRequest();
		String request = String
				.format("[需求] %s 发布了一个需求, 户型是\"%s\", 面积是\"%s\", 联系人是\"%s\", 联系电话是\"%s\"",
						customername, housetype, housesize, contactname,
						contactnumber);
		serviceRequest.setCustomername(customername);
		serviceRequest.setRequest(request);
		serviceRequest.setType(ServiceRequest.TYPE_TEAMWANTED);
		serviceRequest.setCreatetime(new Date());

		return serviceRequest;
	}

	@Override
	public ServiceRequest createApplicationRequest(String customername,
			String teamname, String contactname, String contactnumber,
			String comment, Date time) {
		ServiceRequest serviceRequest = new ServiceRequest();
		String request = String
				.format("[预约] 用户 \"%s\" 预约了施工队\"%s\", 联系人是\"%s\", 联系电话是\"%s\", 备注是\"%s\"",
						customername, teamname, contactname, contactnumber,
						comment);
		serviceRequest.setCustomername(customername);
		serviceRequest.setTeamname(teamname);
		serviceRequest.setRequest(request);
		serviceRequest.setType(ServiceRequest.TYPE_APPLICATION);
		serviceRequest.setCreatetime(new Date());

		return serviceRequest;
	}

	public ServiceRequest createPricingRequest(String customername,
			String teamname, String contactname, String contactnumber, Date time) {
		ServiceRequest serviceRequest = new ServiceRequest();
		String request = String.format(
				"[报价] 用户 \"%s\" 需要施工队\"%s\"的报价, 联系人是\"%s\", 联系电话是\"%s\"",
				customername, teamname, contactname, contactnumber);
		serviceRequest.setCustomername(customername);
		serviceRequest.setTeamname(teamname);
		serviceRequest.setRequest(request);
		serviceRequest.setType(ServiceRequest.TYPE_PRICING);
		serviceRequest.setCreatetime(new Date());

		return serviceRequest;
	}

	@Override
	public ServiceRequest createRequest(String customername, String teamname,
			Date time) {
		return null;
	}

}
