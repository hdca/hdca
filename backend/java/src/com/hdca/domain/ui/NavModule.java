package com.hdca.domain.ui;

public class NavModule {
	int id;
	String href;
	String text;
	String name;
	boolean hidewhenlogin;

	public static final String NAME_HOME = "home";
	public static final String NAME_SEARCH = "search";
	public static final String NAME_LOGIN = "login";
	public static final String NAME_REGISTER = "register";

	public NavModule() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHidewhenlogin() {
		return hidewhenlogin;
	}

	public void setHidewhenlogin(boolean hidewhenlogin) {
		this.hidewhenlogin = hidewhenlogin;
	}

}
