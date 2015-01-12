package com.hdca.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.support.MessageSourceAccessor;

import com.hdca.domain.Team;
import com.opensymphony.xwork2.Action;

public class FmPropsExampleAction extends SqlSessionDaoSupport {
	private static final Logger logger = LogManager
			.getLogger(FmPropsExampleAction.class.getName());

	private MessageSourceAccessor msgsrc;

	private List<Team> teams;

	public String execute() {

		teams = new ArrayList<Team>();
		Team team1 = new Team();
		team1.setId(1);
		team1.setName("wanger");
		team1.setTypes(Arrays.asList(new Integer[] { 1, 2, 5 }));
		team1.setBasepackages(Arrays.asList(new Integer[] { 6, 8 }));
		Team team2 = new Team();
		team2.setId(2);
		team2.setName("zhangsan");
		team2.setTypes(Arrays.asList(new Integer[] { 3, 6 }));
		team2.setBasepackages(Arrays.asList(new Integer[] { 5, 7, 13 }));
		teams.add(team1);
		teams.add(team2);

		// add your login procedure here...
		return Action.SUCCESS;
	}

	public String getStyle() {
		// return "www";
		// return "测试样式";
		return msgsrc.getMessage("style");
	}

	public MessageSourceAccessor getMsgsrc() {
		return msgsrc;
	}

	public void setMsgsrc(MessageSourceAccessor msgsrc) {
		this.msgsrc = msgsrc;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
