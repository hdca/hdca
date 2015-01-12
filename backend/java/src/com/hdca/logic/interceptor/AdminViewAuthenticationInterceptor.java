package com.hdca.logic.interceptor;

import java.util.Map;

import com.hdca.domain.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AdminViewAuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = -5011962009065225959L;

	@Override
	public void destroy() {
		// release resources here
	}

	@Override
	public void init() {
		// create resources here
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("inside admin auth interceptor");
		Map<String, Object> sessionAttributes = actionInvocation
				.getInvocationContext().getSession();

		User user = (User) sessionAttributes.get("user");

		if (user == null) {
			// return actionInvocation.invoke();
			return Action.LOGIN;
		} else {
			// Object action = actionInvocation.getAction();
			// if (action instanceof UserAware) {
			// ((UserAware) action).setUser(user);
			// }
			return actionInvocation.invoke();
		}
	}
}
