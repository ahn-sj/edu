package com.newlec.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		ServletContext application = req.getServletContext();
		
		String v_ = req.getParameter("v");
		String op = req.getParameter("oper");
		
		int v = 0;
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		

		if(op.equals("=")) {
			// 이전에 계산 결과값
			int x = (Integer)application.getAttribute("value");
			// 현재 입력값
			int y = v;
			
			String oper = (String)application.getAttribute("op");
			

			int sum = 0;
			
			if(oper.equals("+")) sum = x + y;
			else if(oper.equals("-")) sum = x - y;
			
			resp.getWriter().printf("result is %d\n", sum);
		}
		else {
			application.setAttribute("value", v);
			application.setAttribute("op", op);
		}
	}

}
