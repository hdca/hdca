package com.hdca.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;

import com.hdca.service.mybatis.typehandler.IntArrayTypeHandler;

public class TeamPropertyNameFinder {
	private static final Logger logger = LogManager
			.getLogger(TeamPropertyNameFinder.class.getName());

	private MessageSourceAccessor msgsrc;

	private Map<Integer, String> typeNameMap = new HashMap<Integer, String>();
	private Map<Integer, String> basepackageNameMap = new HashMap<Integer, String>();
	private Map<Integer, String> pricerangeNameMap = new HashMap<Integer, String>();

	public String getTypeName(Integer value) {
		return typeNameMap.get(value);
	}

	public String getBasepackageName(Integer value) {
		return basepackageNameMap.get(value);
	}

	public String getPricerangeName(Integer value) {
		return pricerangeNameMap.get(value);
	}

	public MessageSourceAccessor getMsgsrc() {
		return msgsrc;
	}

	public void setMsgsrc(MessageSourceAccessor msgsrc) {
		this.msgsrc = msgsrc;

		typeNameMap.clear();
		basepackageNameMap.clear();
		pricerangeNameMap.clear();

		String[] tkNames = null;
		String[] tkValues = null;

		// types
		tkNames = msgsrc.getMessage("team.type.names").split(
				IntArrayTypeHandler.SEPARATOR);
		tkValues = msgsrc.getMessage("team.type.values").split(
				IntArrayTypeHandler.SEPARATOR);
		if (tkNames.length == tkValues.length) {
			for (int i = 0; i < tkNames.length; i++) {
				typeNameMap.put(Integer.parseInt(tkValues[i]), tkNames[i]);
			}
		} else {
			logger.error("(type)lengths of names and values are not equal!");
		}

		// basepackages
		tkNames = msgsrc.getMessage("team.basepackage.names").split(
				IntArrayTypeHandler.SEPARATOR);
		tkValues = msgsrc.getMessage("team.basepackage.values").split(
				IntArrayTypeHandler.SEPARATOR);
		if (tkNames.length == tkValues.length) {
			for (int i = 0; i < tkNames.length; i++) {
				basepackageNameMap.put(Integer.parseInt(tkValues[i]),
						tkNames[i]);
			}
		} else {
			logger.error("(basepackage)lengths of names and values are not equal!");
		}

		// pricerangeNameMap
		tkNames = msgsrc.getMessage("team.pricerange.names").split(
				IntArrayTypeHandler.SEPARATOR);
		tkValues = msgsrc.getMessage("team.pricerange.values").split(
				IntArrayTypeHandler.SEPARATOR);
		if (tkNames.length == tkValues.length) {
			for (int i = 0; i < tkNames.length; i++) {
				pricerangeNameMap
						.put(Integer.parseInt(tkValues[i]), tkNames[i]);
			}
		} else {
			logger.error("(pricerange)lengths of names and values are not equal!");
		}

	}

}
