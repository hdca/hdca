package com.hdca.domain.page;

public class Page {
	int start;
	int limit;
	int page;
	String orderby;
	String orderbydirection;
	String like;

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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOrderbydirection() {
		return orderbydirection;
	}

	public void setOrderbydirection(String orderbydirection) {
		this.orderbydirection = orderbydirection;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

}
