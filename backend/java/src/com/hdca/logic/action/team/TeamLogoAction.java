package com.hdca.logic.action.team;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.logic.BaseAction;
import com.hdca.service.ITeamLogoService;
import com.opensymphony.xwork2.ActionContext;

public class TeamLogoAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamLogoAction.class.getName());

	private ITeamLogoService teamLogoService;

	public String execute() {
		logger.debug("[TeamLogoAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamLogoAction][execute] start, action name="
				+ ActionContext.getContext().getName() + ", withext="
				+ req.getRequestURI());

		String strActionName = ActionContext.getContext().getName();
		String[] tkActionName = strActionName.split("/");
		String strTeamId = tkActionName[tkActionName.length - 1];
		long teamId = Long.parseLong(strTeamId);

		logger.debug("[TeamLogoAction][execute] teamid=" + strTeamId);

		resp.setContentType("image/jpeg");
		// resp.setContentLength(imageBytes.length);

		try { //
				// InputStream is = new FileInputStream(folder + "/" + serial +
				// ".jpg");
			InputStream is = new FileInputStream(
					teamLogoService.getTeamLogoPath(teamId));
			OutputStream os = resp.getOutputStream();
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0)
				os.write(buffer, 0, count);
			os.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ITeamLogoService getTeamLogoService() {
		return teamLogoService;
	}

	public void setTeamLogoService(ITeamLogoService teamLogoService) {
		this.teamLogoService = teamLogoService;
	}

}
