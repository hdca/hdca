package com.hdca.domain;

public class TeamWanted {
	long id;
	String customername;
	String contactname;
	String contactnumber;
	int districtareaid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public int getDistrictareaid() {
		return districtareaid;
	}

	public void setDistrictareaid(int districtareaid) {
		this.districtareaid = districtareaid;
	}

	@Override
	public String toString() {
		return "TeamWanted [id=" + id + ", contactname=" + contactname
				+ ", contactnumber=" + contactnumber + ", districtareaid="
				+ districtareaid + "]";
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
	

}
