package com.newlec.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcTest")
public class CalcTest extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		ServletContext application = req.getServletContext();
		
		String v_ = req.getParameter("v");
		String oper = req.getParameter("oper");
		
		int v = 0;
		
		if(!v_.equals("")) 
			v = Integer.parseInt(v_);
		
		
		if(oper.equals("=")) {
			int sum = 0;
			
			int prev = (int) application.getAttribute("v");
			String op = (String) application.getAttribute("oper");
						
			if(op.equals("+")) sum = prev + v;
			else if(op.equals("-")) sum = prev - v;
			
			resp.getWriter().printf("result is %d\n", sum);
		}
		else {
			application.setAttribute("v", v);
			application.setAttribute("oper", oper);
		}
	}

}
