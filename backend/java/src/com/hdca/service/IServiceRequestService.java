package com.hdca.service;

import java.util.Date;

import com.hdca.domain.ServiceRequest;

public interface IServiceRequestService {
	ServiceRequest createTeamwantedRequest(String housetype, String housesize,
			String customername, String contactname, String contactnumber,
			Date time);

	ServiceRequest createApplicationRequest(String customername,
			String teamname, String contactname, String contactnumber,
			String comment, Date time);
	
	ServiceRequest createPricingRequest(String customername,
			String teamname, String contactname, String contactnumber,
			Date time);

	ServiceRequest createRequest(String customername, String teamname, Date time);
}
