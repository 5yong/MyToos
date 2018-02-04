package com.tool.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GETRequest extends HttpServletRequestWrapper {
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	private String charset;
	public GETRequest(HttpServletRequest request,String charset) {
		super(request);
		this.request=request;
		this.charset = charset;
	}

	public String getParameter(String name){
		String nameVal = request.getParameter(name);
		if (nameVal == null) {
			return null;
		}
		try {
			byte[] namebyte = nameVal.getBytes("ISO-8859-1");
			return new String(namebyte, charset);
		} catch (UnsupportedEncodingException e) {

			throw new RuntimeException(e);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public Map getParameterMap(){
		@SuppressWarnings("unchecked")
		Map<String,String[]> map = request.getParameterMap();
		if (map == null) {
			return null;
		}
		for(String key : map.keySet()){
			String[] values = map.get(key);
			for (int i = 0; i < values.length; i++) {
				try {
					values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return map;
	}
	
	public String[] getParameterValues(String name){
		String[] values = request.getParameterValues(name);
		if (values == null) {
			return null;
		}
		for(int i = 0; i < values.length; i++) {
			try {
				byte[] byt = values[i].getBytes("ISO-8859-1");
				values[i] = new String(byt, charset);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return values;
	}
}
