package com.newlec.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcCookie")
public class CalcCookie extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Cookie[] cookies = req.getCookies();
		
		String v_ = req.getParameter("v");
		String oper = req.getParameter("oper");
		
		int v = 0;
		
		if(!v_.equals("")) 
			v = Integer.parseInt(v_);
		
		
		if(oper.equals("=")) {
			int sum = 0;
			String operator = "";
			int x = 0;

			for(Cookie c : cookies) 
				if(c.getName().equals("v")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			
			for(Cookie c : cookies) 
				if(c.getName().equals("oper")) {
					operator = c.getValue();
					break;
				}
			
			if(operator.equals("+")) sum = x + v;
			else if(operator.equals("-")) sum = x - v;
			
			resp.getWriter().printf("result is %d\n", sum);
		}
		else {			
			Cookie vCookie = new Cookie("v", String.valueOf(v));
			Cookie opCookie = new Cookie("oper", oper);
			
			vCookie.setPath("/calcCookie");
			opCookie.setPath("/calcCookie");
			
			vCookie.setMaxAge(24*60*60);
			
			resp.addCookie(vCookie);
			resp.addCookie(opCookie);
		}
	}

}
