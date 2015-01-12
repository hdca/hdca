package com.hdca.service.impl;

import org.springframework.context.support.MessageSourceAccessor;

import com.hdca.service.ITeamLogoService;
import com.hdca.util.debug.DebugUtil;

public class TeamLogoServiceImpl implements ITeamLogoService {
	protected MessageSourceAccessor msgsrc;
	protected MessageSourceAccessor debugmsgsrc;

	@Override
	public String getTeamLogoDir() {
		System.out.println("=====================> getTeamLogoDir");
		String tld = null;
		if (DebugUtil.isDebugMode(debugmsgsrc)) {
			tld = debugmsgsrc.getMessage("system.teamlogodir");
		} else {
			tld= msgsrc.getMessage("system.teamlogodir");
		}
		System.out.println("=====================> tld=="+tld);
		return tld;
	}

	@Override
	public String getTeamLogoPath(long teamId) {
		System.out.println("=====================> getTeamLogoPath");
		String tlp = getTeamLogoDir() + "/" + teamId + ".jpg";
		
		System.out.println("=====================> tlp=="+tlp);
		
		return tlp;
	}

	public MessageSourceAccessor getMsgsrc() {
		return msgsrc;
	}

	public void setMsgsrc(MessageSourceAccessor msgsrc) {
		this.msgsrc = msgsrc;
	}

	public MessageSourceAccessor getDebugmsgsrc() {
		return debugmsgsrc;
	}

	public void setDebugmsgsrc(MessageSourceAccessor debugmsgsrc) {
		this.debugmsgsrc = debugmsgsrc;
	}
}
