package com.hdca.logic.action.team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.Team;
import com.hdca.domain.TeamBasePackage;
import com.hdca.domain.TeamDistrict;
import com.hdca.domain.TeamPriceRange;
import com.hdca.domain.TeamType;
import com.hdca.logic.BaseAction;
import com.hdca.service.ITeamLogoService;
import com.opensymphony.xwork2.ActionSupport;

public class TeamCreateAction extends BaseAction {
	private static final Logger logger = LogManager
			.getLogger(TeamCreateAction.class.getName());

	Team team;

	private List<String> myList;

	private String title;

	private ITeamLogoService teamLogoService;

	// private List<String> myList = Arrays.asList(new
	// String[]{"111","222","333"});

	// @Resource(name="commonProperties1")
	// @Value("#{commonProperties}")

	/**
	 * HTML response
	 * 
	 * @return
	 */
	public String execute() {
		logger.debug("[TeamCreateAction][create] start");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();

		logger.debug("[TeamCreateAction][create] team=" + team + ", logo="
				+ logo + ", logo.fn=" + logoFileName);

		int ret;

		// add team record
		ret = getSqlSession().insert("hdca.TeamMapper.addTeam", team);
		if (ret != 1) {
			logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeam failure: ret="
					+ ret);
			return ActionSupport.ERROR;
		}
		logger.debug("[TeamCreateAction][create] team added, generated id="
				+ team.getId());

		// add team districtareas
		for (Integer daid : team.getDistrictareas()) {
			TeamDistrict tdTemp = new TeamDistrict();
			tdTemp.setDistrictareaid(daid);
			tdTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamDistrict",
					tdTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamDistrict failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// add team types
		for (Integer tpid : team.getTypes()) {
			TeamType ttTemp = new TeamType();
			ttTemp.setTypeid(tpid);
			ttTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamType", ttTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamType failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// add team basepackages
		for (Integer bpid : team.getBasepackages()) {
			TeamBasePackage tpTemp = new TeamBasePackage();
			tpTemp.setBasepackageid(bpid);
			tpTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamBasePackage",
					tpTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamBasePackage failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// add team priceranges
		for (Integer prid : team.getPriceranges()) {
			TeamPriceRange tpTemp = new TeamPriceRange();
			tpTemp.setPricerangeid(prid);
			tpTemp.setTeamid(team.getId());
			ret = getSqlSession().insert("hdca.TeamMapper.addTeamPriceRange",
					tpTemp);
			if (ret != 1) {
				logger.debug("[TeamCreateAction][create] hdca.TeamMapper.addTeamPriceRange failure: ret="
						+ ret);
				return ActionSupport.ERROR;
			}
		}

		// ----------file upload start
		try { //
			InputStream is = new FileInputStream(logo);
			// OutputStream os = new FileOutputStream(folder + "/" + serial
			// + ".jpg");
			OutputStream os = new FileOutputStream(
					teamLogoService.getTeamLogoPath(team.getId()));
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0)
				os.write(buffer, 0, count);
			os.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ----------file upload end

		info = "注册成功!";

		return ActionSupport.SUCCESS;

	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<String> getMyList() {
		return myList;
	}

	public void setMyList(List<String> myList) {
		this.myList = myList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// ---------------- file upload stuff -------------
	private File logo;
	private String logoContentType;
	private String logoFileName;

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		System.out.println("in set logo!");
		this.logo = logo;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		System.out.println("in set setLogoContentType!");
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		System.out.println("in set setLogoFileName!");
		this.logoFileName = logoFileName;
	}

	public ITeamLogoService getTeamLogoService() {
		return teamLogoService;
	}

	public void setTeamLogoService(ITeamLogoService teamLogoService) {
		this.teamLogoService = teamLogoService;
	}

}
