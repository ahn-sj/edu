package com.newlec.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class CalcAdd extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		int lv_ = Integer.parseInt(req.getParameter("lv"));
		int rv_ = Integer.parseInt(req.getParameter("rv"));
		int sum = lv_ + rv_;
		
		PrintWriter out = resp.getWriter();
		out.println("µ¡¼À °á°ú : " + sum);
	}

}
