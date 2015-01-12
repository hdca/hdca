package com.hdca.service;

import javax.servlet.http.HttpServletRequest;

import com.hdca.domain.jqgrid.JqGridPage;

public interface IJqGridPagingService {
	JqGridPage getPageFromHttpRequest(HttpServletRequest req);
}
