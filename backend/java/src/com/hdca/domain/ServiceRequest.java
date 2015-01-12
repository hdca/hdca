package com.hdca.domain;

import java.util.Date;

public class ServiceRequest {
	long id;
	String customername;
	String teamname;
	String request;
	int type;
	Date createtime;

	public static final int TYPE_TEAMWANTED = 1;
	public static final int TYPE_APPLICATION = 2;
	public static final int TYPE_PRICING = 3;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
