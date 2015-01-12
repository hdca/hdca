package com.hdca.domain;

import java.util.Date;

class Favorite {
	long id;
	String customerid;
	long serial;
	int type;
	Date createtime;
	String comment;


	public long getId() {
		 return id;
	}
	public void setId(long id) {
		 this.id = id;	}
	public String getCustomerid() {
		 return customerid;
	}
	public void setCustomerid(String customerid) {
		 this.customerid = customerid;	}
	public long getSerial() {
		 return serial;
	}
	public void setSerial(long serial) {
		 this.serial = serial;	}
	public int getType() {
		 return type;
	}
	public void setType(int type) {
		 this.type = type;	}
	public Date getCreatetime() {
		 return createtime;
	}
	public void setCreatetime(Date createtime) {
		 this.createtime = createtime;	}
	public String getComment() {
		 return comment;
	}
	public void setComment(String comment) {
		 this.comment = comment;	}
}
