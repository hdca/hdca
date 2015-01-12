package com.hdca.service;

import javax.servlet.http.HttpServletResponse;

public interface IMailService {
	void sendMail(String to, String title, String content,HttpServletResponse resp);
}
