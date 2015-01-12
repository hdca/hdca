package com.hdca.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.hdca.domain.jqgrid.JqGridPage;
import com.hdca.service.IJqGridPagingService;

public class JqGridPagingServiceImpl implements IJqGridPagingService {

	@Override
	public JqGridPage getPageFromHttpRequest(HttpServletRequest req) {
		JqGridPage jgPage = new JqGridPage();

		String sRows = req.getParameter("rows");
		String sPage = req.getParameter("page");

		int rows = Integer.parseInt(sRows);
		int page = Integer.parseInt(sPage);

		jgPage.setRows(rows);
		jgPage.setPage(page);

		return jgPage;
	}

}
