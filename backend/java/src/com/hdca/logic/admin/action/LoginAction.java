package com.hdca.logic.admin.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

import com.hdca.domain.User;
import com.hdca.logic.BaseAction;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -3369875299120377549L;
	private static final Logger logger = LogManager.getLogger(LoginAction.class
			.getName());

	public String execute() {
		// System.out.println("inside execute");
		logger.debug("[LoginAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User userToCheck;

		logger.debug("[LoginAction][execute] username=" + username);
		User userQuery = new User();
		userQuery.setUsername(username);
		userToCheck = getSqlSession().selectOne(
				"hdca.UserMapper.getUserByUsername", userQuery);
		if (userToCheck != null) {
			logger.debug("[LoginAction][execute] fetched: username="
					+ userToCheck.getUsername() + ",pswhash="
					+ userToCheck.getPswhash());
		}

		if (userToCheck != null
				&& BCrypt.checkpw(password, userToCheck.getPswhash())) {
			getSession().put("user", userToCheck);
			return "success";
		}
		return "login";
	}

	/**
	 * for password generation use
	 * 
	 * @return
	 */
	public String executeTemp() {
		logger.debug("[LoginAction][executetemp] start");

		HttpServletRequest req = ServletActionContext.getRequest();
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		logger.debug("[LoginAction][executetemp] username=" + username
				+ ",password=" + password);

		User userQuery = new User();
		userQuery.setUsername(username);
		String pswhash = BCrypt.hashpw(password, BCrypt.gensalt());
		userQuery.setPswhash(pswhash);
		int ret = getSqlSession().insert("hdca.UserMapper.addUser", userQuery);
		logger.debug("[LoginAction][executetemp] hdca.UserMapper.addUser: ret="
				+ ret);

		return "success";
	}
}
