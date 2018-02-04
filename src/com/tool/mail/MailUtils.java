package com.tool.mail;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


/**
 * 邮件工具类提供发送邮件等操作
 * @author Administrator
 *
 */
public class MailUtils {
/**
 * 提供创建邮件发送邮件与邮件服务器的链接
 * @param host
 * @param userName
 * @param passWord
 * @return
 */
	public static Session createSession(String host, final String userName, final String passWord){
		
		Properties properties = new Properties();
		properties.put("mail.host", host);
		properties.put("mail.smtp.auth", "true");
		
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(userName, passWord);
			}
		};
		
		
		return Session.getInstance(properties, authenticator);
		
	}
	
	
	/**
	 * 发送邮件
	 * @param session
	 * @param mail
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public static void sendMail(Session session, Mail mail) throws MessagingException, IOException{
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mail.getFromPerson()));
		
		msg.addRecipients(RecipientType.TO, mail.getToPerson());
		String cc = mail.getCcPerson();
		if (cc !=null&&!cc.isEmpty()) {
			
			msg.addRecipients(RecipientType.CC, cc);
		}
		String bcc = mail.getBcPerson();
		if (bcc != null && !bcc.isEmpty()) {
			msg.addRecipients(RecipientType.BCC, bcc);
			
		}
		String subject = mail.getSubject();
		
		if (subject != null && !subject.isEmpty()) {
			msg.setSubject(subject);
		}
		
		MimeMultipart parts = new MimeMultipart();
		MimeBodyPart body1 = new MimeBodyPart();
		
		String context = mail.getContext();
		if (context !=null && !context.isEmpty()) {
			body1.setContent(context, "text/html;charset=utf-8");
		}
		parts.addBodyPart(body1);
		
		
		List<AttachBean> attachBeans = mail.getList();
		if (attachBeans !=null && attachBeans.size() >0) {
			for(AttachBean attachBean : attachBeans){
				MimeBodyPart body2 = new MimeBodyPart();
				body2.attachFile(attachBean.getFile());
				body2.setFileName(MimeUtility.encodeText(attachBean.getFileName()));
				String cid = attachBean.getcId();
				if (cid != null &&!cid.isEmpty()) {
					body2.setContentID(cid);
				}
				parts.addBodyPart(body2);
			}
		}
	
		msg.setContent(parts);
		Transport.send(msg);
	}
}
