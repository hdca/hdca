package com.hdca.logic.action.customer;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdca.domain.Customer;
import com.hdca.domain.PswResetToken;
import com.hdca.logic.BaseAction;
import com.hdca.service.IMailService;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerRequestPasswordResetAction extends BaseAction {
	private static final long serialVersionUID = -3369875299120377549L;
	private static final Logger logger = LogManager
			.getLogger(CustomerRequestPasswordResetAction.class.getName());

	Customer customer;
	IMailService mailService;

	public String execute() {
		// System.out.println("inside execute");
		logger.debug("[CustomerRequestPasswordResetAction][execute] start");

		HttpServletRequest req = ServletActionContext.getRequest();

		logger.debug("[CustomerRequestPasswordResetAction][execute] email="
				+ customer.getEmail());

		// String token = "123456677";

		long x = 0x100000L;
		long y = 0xFFFFFFL;
		Random r = new Random();
		long lPart1 = x + ((long) (r.nextDouble() * (y - x)));
		long lPart2 = x + ((long) (r.nextDouble() * (y - x)));
		long lPart3 = x + ((long) (r.nextDouble() * (y - x)));
		String token = Long.toHexString(lPart1) + Long.toHexString(lPart2)
				+ Long.toHexString(lPart3);

		PswResetToken pswResetToken = new PswResetToken();
		pswResetToken.setName(customer.getEmail());
		pswResetToken.setToken(token);

		int ret = getSqlSession().insert(
				"hdca.PswRestTokenMapper.addPswRestToken", pswResetToken);
		logger.debug("[CustomerRequestPasswordResetAction][execute] hdca.PswRestTokenMapper.addPswRestToken: ret="
				+ ret);

		mailService.sendMail(customer.getEmail(), "[shigongdui110.com] 重设密码",
				"http://shigongdui110.com:8080/hdca/public/customer/resetpsw?token="
						+ token, null);

		info = "重设密码链接已发送至" + customer.getEmail() + "!";

		return ActionSupport.SUCCESS;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

}
