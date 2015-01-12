package com.hdca.logic.pub.action.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdca.domain.Customer;
import com.hdca.domain.Team;
import com.hdca.domain.jqgrid.JqGridPage;
import com.hdca.domain.jqgrid.JqGridResult;
import com.hdca.domain.page.Page;
import com.hdca.service.IJqGridPagingService;
import com.hdca.service.gson.processor.CustomerProcessor;

public class CustomerListDataAction extends SqlSessionDaoSupport {
	private static final Logger logger = LogManager
			.getLogger(CustomerListDataAction.class.getName());

	private IJqGridPagingService jqGridPagingService;

	String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	Team team;

	public String execute() {
		logger.debug("[JqDataAction][execute] start=====================");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		// req k=type, v=BS21 7RH
		// req k=_search, v=false
		// req k=nd, v=1418181803625
		// req k=rows, v=20
		// req k=page, v=1
		// req k=sidx, v=
		// req k=sord, v=asc

		JqGridPage page = jqGridPagingService.getPageFromHttpRequest(req);

		// Enumeration params = req.getParameterNames();
		// while (params.hasMoreElements()) {
		// String paramName = (String) params.nextElement();
		// logger.debug("[JqDataAction][execute] req k=" + paramName + ", v="
		// + req.getParameter(paramName));
		// }

		// StringBuilder buffer = new StringBuilder();
		// BufferedReader reader = null;
		// try {
		// reader = req.getReader();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// String line;
		// try {
		// while ((line = reader.readLine()) != null) {
		// buffer.append(line);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// String data = buffer.toString();
		// logger.debug("[JqDataAction][execute] post req=" + data);

		logger.debug("[JqDataAction][execute] page=" + page);

		// get data (list) and format to json
		List<Customer> customerList = null;
		Page customerPage = new Page();
		customerPage.setStart((page.getPage() - 1) * page.getRows());
		customerPage.setLimit(page.getRows());
		customerList = getSqlSession().selectList(
				"hdca.CustomerMapper.getCustomerByPage", customerPage);

		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Customer.class, new CustomerProcessor());
		Gson gson = gb.create();

		String jsonData = gson.toJson(customerList);

		// get count
		int count = getSqlSession().selectOne(
				"hdca.CustomerMapper.getCustomerCount");// mmm: temp
		int totalCountOfPage = count / page.getRows();// mmm: wrong

		// build result
		JqGridResult jqGridResult = new JqGridResult();
		jqGridResult.setPage(page.getPage());
		jqGridResult.setTotal(totalCountOfPage);
		jqGridResult.setRecords(count);
		jqGridResult.setJsonData(jsonData);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jqGridResult.toJsonStr());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --------------

		// int totalNumberOfPages = 3;
		// int currentPageNumber = 1;
		// int totalNumberOfRecords = 48;
		//
		// JqGridData<Person> gridData = new JqGridData<Person>(
		// totalNumberOfPages, currentPageNumber, totalNumberOfRecords,
		// personList);
		// System.out.println("Grid Data: " + gridData.getJsonString());
		// try {
		// resp.getWriter().write(gridData.getJsonString());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return null;
	}

	public String executeOld() {
		logger.debug("[TeamAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamAction][create] team=" + team);

		// String name = req.getParameter("name");

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
		logger.debug("[TeamAction][create] post req=" + data);

		resp.setContentType("application/json");
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ success: true, msg: \"添加成功111!\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public IJqGridPagingService getJqGridPagingService() {
		return jqGridPagingService;
	}

	public void setJqGridPagingService(IJqGridPagingService jqGridPagingService) {
		this.jqGridPagingService = jqGridPagingService;
	}

}
