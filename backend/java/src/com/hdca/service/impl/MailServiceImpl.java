package com.hdca.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import com.hdca.service.IMailService;

public class MailServiceImpl implements IMailService {

	@Override
	public void sendMail(String to, String title, String content,
			HttpServletResponse resp) {
		// Sender's email ID needs to be mentioned
		String from = "hdca2014@126.com";

		// Assuming you are sending email from localhost
		// String host = "localhost";
		String username = "hdca2014@126.com";
		String password = "zhuangxiu";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.host", "smtp.126.com");
		// props.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		Authenticator auth = new SMTPAuthenticator(username, password);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties, auth);

		// Set response content type
		// resp.setContentType("text/html");
		// PrintWriter out;
		// try {
		// out = resp.getWriter();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			// Set Subject: header field
			message.setSubject(title);
			// Now set the actual message
			message.setText(content);
			// Send message
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private class SMTPAuthenticator extends Authenticator {

		private PasswordAuthentication authentication;

		public SMTPAuthenticator(String login, String password) {
			authentication = new PasswordAuthentication(login, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}

	public static void main(String[] args) {
		MailServiceImpl msi = new MailServiceImpl();
		msi.sendMail("jinrift@126.com", "test from localhost", "sometitle",
				null);
	}
}
