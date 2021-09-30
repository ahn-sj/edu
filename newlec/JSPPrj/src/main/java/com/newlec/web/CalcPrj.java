package com.newlec.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcPrj")
public class CalcPrj extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		
		String exp = "0";		
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
		PrintWriter out = resp.getWriter();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		
		// 사용자가 입력한 표현식
		String value = req.getParameter("value");
		String operator = req.getParameter("oper");
		String dot = req.getParameter("dot");
		
		// 쿠키값 (이전 값)
		String exp = "";		
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
				// 계산
		if(operator != null && operator.equals("=")) {
			// 자바 스크립트 구문 실행자
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		// C(Clear) 기능
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		
		else { 	// 값 쌓기  
			// 숫자를 누르면 숫자만 넘어오고 연산자를 누르면 연산자만 넘어온다
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		// 쿠키를 완전히 제거
		if(operator != null && operator.equals("C")) expCookie.setMaxAge(0);
		
		expCookie.setPath("/calcPrj");
		resp.addCookie(expCookie);			
		resp.sendRedirect("calcPrj");
	}
}
