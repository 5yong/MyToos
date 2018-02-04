package com.tool.test;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Test;

import com.tool.mail.AttachBean;
import com.tool.mail.Mail;
import com.tool.mail.MailUtils;

public class MailUtilsTest {

	@Test
	public void test() throws MessagingException, IOException {

		AttachBean attachBean = new AttachBean("手机", new File("E:\\logs\\debug.log"));
	
	 Mail mail = new Mail("hjy199055@163.com", "862666252@qq.com", "测试邮件", "<a href='http://www.baidu.com'>百度</a>");
	mail.addAttachBean(attachBean);
	
	Session session = MailUtils.createSession("smtp.163.com", "hjy199055", "13130305204");
	
	MailUtils.sendMail(session, mail);
	}

}
