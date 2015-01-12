<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>临时登录页面</title>
</head>
<body>
	<h3>请登录</h3>
	<s:form action="admin/login">
		<s:textfield name="username" label="用户名"></s:textfield>
		<s:textfield name="password" label="密码" type="password"></s:textfield>
		<s:submit value="Login"></s:submit>
	</s:form>
</body>
</html>