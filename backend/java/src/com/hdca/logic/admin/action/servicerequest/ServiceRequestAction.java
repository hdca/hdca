package com.hdca.logic.admin.action.servicerequest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdca.domain.ServiceRequest;
import com.hdca.domain.page.Page;
import com.hdca.logic.BaseAction;
import com.hdca.service.IServiceRequestService;
import com.hdca.service.gson.processor.admin.ServiceRequestProcessor;

public class ServiceRequestAction extends BaseAction {

	IServiceRequestService serviceRequestService;

	// ===================params==========
	int start;
	int limit;
	int page;

	// ===================params==========

	public String read() {
		logger.debug("[ServiceRequestAction][read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[ServiceRequestAction][read] param: start=" + start
				+ ", limit=" + limit + ",page=" + page);

		Page p = new Page();
		p.setStart(start);
		p.setLimit(limit);
		p.setPage(page);

		// get total count
		Integer count = (Integer) getSqlSession().selectOne(
				"hdca.ServiceRequestMapper.getServiceRequestCount");
		logger.debug("[CustomerAction][read] count=" + count);

		// get items
		List<ServiceRequest> customerList = getSqlSession().selectList(
				"hdca.ServiceRequestMapper.getServiceRequestByPage", p);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(ServiceRequest.class, new ServiceRequestProcessor());
		Gson gson = gb.create();

		String jsonCommodities = gson.toJson(customerList);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"totalCount\":").append(count).append(", \"data\":")
				.append(jsonCommodities).append("}");

		// ---------------

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ===================setters and getters==========

	public IServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public void setServiceRequestService(
			IServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

}
