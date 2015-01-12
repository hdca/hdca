package com.hdca.logic.admin.action.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdca.domain.Customer;
import com.hdca.logic.BaseAction;
import com.hdca.service.IHttpService;
import com.hdca.service.gson.processor.CustomerProcessor;

public class CustomerUpdateAction extends BaseAction {
	private IHttpService httpService;

	private static final Logger logger = LogManager
			.getLogger(CustomerUpdateAction.class.getName());

	public String update() {

		logger.debug("[CustomerUpdateAction][update] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		String data = httpService.getRequestBody(req);
		logger.debug("[CustomerUpdateAction][update] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Customer.class, new CustomerProcessor());
		Gson gson = gb.create();

		Customer customer = null;
		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);

		// single object
		JsonObject jsoPutawayDetail = jse.getAsJsonObject();
		customer = gson.fromJson(jsoPutawayDetail, Customer.class);

		int ret = getSqlSession().update(
				"hjmall.CustomerMapper.updateCustomer", customer);
		if (ret != 1) {
			logger.debug("[CustomerUpdateAction][update] failure: email="
					+ customer.getEmail());
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{ success: false, msg: \"更新失败!\"}");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		logger.debug("[CustomerUpdateAction][update] after update");

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"ok!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}
