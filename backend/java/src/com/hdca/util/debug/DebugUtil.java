package com.hdca.util.debug;

import org.springframework.context.support.MessageSourceAccessor;

public class DebugUtil {
	public static boolean isDebugMode(MessageSourceAccessor debugmsgsrc) {
		return Boolean.valueOf(debugmsgsrc.getMessage("debug", "false"));
	}
}
