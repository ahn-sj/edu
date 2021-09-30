package com.newlec.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class CalcAdd2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String[] nums = req.getParameterValues("num");
		String op = req.getParameter("oper");
		

		int sum = 0;
		
		for(int i = 0; i < nums.length; i++) {
			int temp = Integer.parseInt(nums[i]);
			sum += temp;
		}
				
		resp.getWriter().printf("result is %d\n", sum);
	}

}
