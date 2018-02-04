package com.tool.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示邮件类，你需要设置：账户名和密码、收件人、抄送(可选)、暗送(可选)、主题、内容，以及附件(可选)
 * 
 * 在创建了Mail对象之后
 * 可以调用它的setSubject()、setContent()，设置主题和正文
 * 也可以调用setFrom()和　addToAddress()，设置发件人，和添加收件人。
 * 也可以调用addAttch()添加附件
 * 创建AttachBean：new AttachBean(new File("..."), "fileName");
 */
public class Mail {

	private String fromPerson;
	private StringBuilder toPerson = new StringBuilder();
	
	private StringBuilder bcPerson = new StringBuilder();
	
	private StringBuilder ccPerson = new StringBuilder();
	
	private String subject;
	
	private  String context;
	
	List<AttachBean> list = new ArrayList<AttachBean>();
	
	
	
	
	public Mail() {
		super();
	}

	public Mail(String fromPerson, String toPerson){
		this(fromPerson, toPerson, null, null);
	}
	
	public Mail(String fromPerson, String toPerson,String subject,String context){
		this.fromPerson=fromPerson;
		this.toPerson.append(toPerson);
		this.subject = subject;
		this.context = context;
	}
	

	public Mail(String fromPerson, String toPerson,
			String bcPerson, String ccPerson, String subject,
			String context, List<AttachBean> list) {
		super();
		this.fromPerson = fromPerson;
		this.toPerson.append(toPerson);
		this.bcPerson.append(bcPerson);
		this.ccPerson.append(ccPerson);
		this.subject = subject;
		this.context = context;
		this.list = list;
	}

	public String getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(String fromPerson) {
		this.fromPerson = fromPerson;
	}

	public String getToPerson() {
		return toPerson.toString();
	}

	public void setToPerson(StringBuilder toPerson) {
		this.toPerson = toPerson;
	}

	
	public void addToPerson(String to){
		if (toPerson.length() > 0) {
			toPerson.append(",").append(to);
		}else {
			toPerson.append(to);
		}
	}
	
	
	public String getBcPerson() {
		return bcPerson.toString();
	}

	public void setBcPerson(StringBuilder bcPerson) {
		this.bcPerson = bcPerson;
	}

	public void addBcPerson(String bc){
		if (bcPerson.length() > 0) {
			bcPerson.append(",").append(bc);
		}else {
			bcPerson.append(bc);
		}
	}
	public String getCcPerson() {
		return ccPerson.toString();
	}

	public void setCcPerson(StringBuilder cc) {
		this.ccPerson=cc;
	}

	public void addCcPerson(String cc){
		if (ccPerson.length() > 0) {
			ccPerson.append(",").append(cc);
		}else {
			ccPerson.append(cc);
		}
	}
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		
		this.subject = subject;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<AttachBean> getList() {
		return list;
	}

	public void setList(List<AttachBean> list) {
		this.list = list;
	}
	
	public void addAttachBean(AttachBean attach){
		this.list.add(attach);
	}
	
}