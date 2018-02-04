package com.tool.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {
private String charset = null;
	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (request.getMethod().equalsIgnoreCase("GET")) {
			if (!(request instanceof GETRequest)) {
				
				request = new GETRequest(request, charset);
			}
		}else if (request.getMethod().equalsIgnoreCase("POST")) {
			request.setCharacterEncoding(charset);
		}
arg2.doFilter(request, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.charset = "UTF-8";
		String charset = arg0.getInitParameter("charSet");
		if (charset != null && !charset.trim().isEmpty()) {
			
			this.charset=charset;
		}
		
	}

}
