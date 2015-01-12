package com.hdca.domain;

public class Customer {
	long id;
	// 包括名字、电话、所在城市及区域、户型、面积、备注
	String nickname;
	String mobile;
	String password;
	String pswhash;
	String email;
	String vcode;
	int districtareaid;

	// for display
	String districtname;
	String cityname;

	public Customer() {
	}

	public Customer(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPswhash() {
		return pswhash;
	}

	public void setPswhash(String pswhash) {
		this.pswhash = pswhash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDistrictareaid() {
		return districtareaid;
	}

	public void setDistrictareaid(int districtareaid) {
		this.districtareaid = districtareaid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", nickname=" + nickname + ", mobile=" + mobile
				+ ", password=" + password + ", pswhash=" + pswhash
				+ ", email=" + email + ", vcode=" + vcode + ", districtareaid="
				+ districtareaid + ", districtname=" + districtname
				+ ", cityname=" + cityname + "]";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
