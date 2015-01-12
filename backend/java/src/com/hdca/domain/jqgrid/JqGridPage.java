package com.hdca.domain.jqgrid;

public class JqGridPage {
	/**
	 * rows per page
	 */
	int rows;
	/**
	 * current page
	 */
	int page;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "JqGridPage [rows=" + rows + ", page=" + page + "]";
	}
}
