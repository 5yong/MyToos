package com.tool.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tool.servlet.BasicServlet;

public class AServlet extends BasicServlet {	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String vcode = (String) request.getSession().getAttribute("vCode");
		if (vcode.equalsIgnoreCase(code)) {
			System.out.println(name);
			request.setAttribute("name", name);
			return "f:/error.jsp";
			
		}
		return "r:/index.jsp";
 
	}
	public String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("update()...");
		return "r:/index.jsp";
	}
}
