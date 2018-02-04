package com.tool.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		resp.setContentType("text/html;charset=utf-8");
		String methodName = req.getParameter("method");
		Method method = null;
		try {
			method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			
			method.setAccessible(true);
		} catch (Exception e) {
			throw new RuntimeException("您要调用的方法：" + methodName + "它不存在！", e);
		}
		try {
			String result = (String) method.invoke(this, req,resp);
			if (result != null && !result.trim().isEmpty()) {
				int index = result.indexOf(":");
				if (index == -1) {
					req.getRequestDispatcher(result).forward(req, resp);
				}else {
					String start = result.substring(0, index);
					String path = result.substring(index+1);
					if (start.equalsIgnoreCase("f")) {
						req.getRequestDispatcher(path).forward(req, resp);
					}else if(start.equalsIgnoreCase("r")) {
						resp.sendRedirect(req.getContextPath()+path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);// TODO: handle exception
		}
		
	}

}
