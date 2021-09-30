package com.newlec.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		String op = req.getParameter("oper");
		
		int x = 0;
		int y = 0;
		
		if(!x_.equals("")) x = Integer.parseInt(x_);
		if(!y_.equals("")) y = Integer.parseInt(y_);
		

		int sum = 0;
		
		if(op.equals("µ¡¼À")) sum = x + y;
		else if(op.equals("»¬¼À")) sum = x - y;
		
		resp.getWriter().printf("result is %d\n", sum);
	}

}
