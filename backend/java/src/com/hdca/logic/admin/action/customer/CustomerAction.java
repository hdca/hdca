package com.hdca.logic.admin.action.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdca.domain.Customer;
import com.hdca.domain.page.Page;
import com.hdca.logic.BaseAction;
import com.hdca.service.gson.processor.CustomerProcessor;

public class CustomerAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(CustomerAction.class.getName());

	int start;
	int limit;
	int page;

	// ===================params==========

	public String read() {
		logger.debug("[CustomerAction][read] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[CustomerAction][read] param: start=" + start
				+ ", limit=" + limit + ",page=" + page);

		Page p = new Page();
		p.setStart(start);
		p.setLimit(limit);
		p.setPage(page);

		// get total count
		Integer count = (Integer) getSqlSession().selectOne(
				"hdca.CustomerMapper.getCustomerCount");
		logger.debug("[CustomerAction][read] count=" + count);

		// get items
		List<Customer> customerList = getSqlSession().selectList(
				"hdca.CustomerMapper.getCustomerByPage", p);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Customer.class, new CustomerProcessor());
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

	// -------------------------------------

	public String create() {

		logger.debug("[CustomerAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = buffer.toString();
		logger.debug("[CustomerAction][create] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Customer.class, new CustomerProcessor());
		Gson gson = gb.create();

		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);
		// single object
		JsonObject jsoCommodity = jse.getAsJsonObject();
		Customer customer = gson.fromJson(jsoCommodity, Customer.class);

		String pswhash = BCrypt
				.hashpw(customer.getPassword(), BCrypt.gensalt());
		customer.setPswhash(pswhash);

		int ret;

		ret = getSqlSession().insert("hdca.CustomerMapper.addCustomer",
				customer);
		if (ret != 1) {
			logger.debug("[CustomerAction][create] hjmall.CustomerMapper.addCustomer failure: ret="
					+ ret);
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{ success: false, msg: \"添加失败!\"}");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"添加成功!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public String update() {
		logger.debug("[CustomerAction][update] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = sb.toString();
		logger.debug("[CustomerAction][update] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Customer.class, new CustomerProcessor());
		Gson gson = gb.create();

		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);
		// single object
		JsonObject jsoTeam = jse.getAsJsonObject();
		Customer team = gson.fromJson(jsoTeam, Customer.class);

		int ret;

		// add team record
		ret = getSqlSession()
				.update("hdca.CustomerMapper.updateCustomer", team);
		if (ret != 1) {
			logger.debug("[CustomerAction][update] hdca.CustomerMapper.updateCustomer failure: ret="
					+ ret);
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(
						"{ success: false, msg: \"hdca.CustomerMapper.updateCustomer failure: ret="
								+ ret + "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
		logger.debug("[CustomerAction][update] team updated, id="
				+ team.getId());

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"ok!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public String delete() {
		logger.debug("[CustomerAction][delete] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = buffer.toString();
		logger.debug("[CustomerAction][delete] post req=" + data);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Customer.class, new CustomerProcessor());
		Gson gson = gb.create();

		Customer putawayDetail = null;
		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(data);

		// single object
		JsonObject jsoPutawayDetail = jse.getAsJsonObject();
		putawayDetail = gson.fromJson(jsoPutawayDetail, Customer.class);

		int ret = getSqlSession().delete("hdca.CustomerMapper.deleteCustomer",
				putawayDetail);

		if (ret != 1) {
			logger.error("[CustomerAction][delete] failure: id="
					+ putawayDetail.getId());
			resp.setContentType("application/json");
			try {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("{ success: false, msg: \"删除失败!\"}");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		logger.debug("[CustomerAction][delete] after delete");

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"ok!\"}");
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

}
