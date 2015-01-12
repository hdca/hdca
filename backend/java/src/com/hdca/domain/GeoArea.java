package com.hdca.domain;

import java.util.ArrayList;
import java.util.List;

public class GeoArea {
	int areaid;
	int parentid;
	String name;
	int type;

	// synthesized data
	List<GeoArea> children;

	public GeoArea() {
		children = new ArrayList<GeoArea>();
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<GeoArea> getChildren() {
		return children;
	}

	public void setChildren(List<GeoArea> children) {
		this.children = children;
	}

}
