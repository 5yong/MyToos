package com.tool.mail;

import java.io.File;

/**
 * 附件封装类
 * @author Administrator
 *
 */
public class AttachBean {

	private String fileName;
	private File file;
	private String cId;
	
	public AttachBean(String fileName, File file) {
		super();
		this.fileName = fileName;
		this.file = file;
	}
	public AttachBean() {
		super();
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	
	
	
}
