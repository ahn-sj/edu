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
		
		// ����ڰ� �Է��� ǥ����
		String value = req.getParameter("value");
		String operator = req.getParameter("oper");
		String dot = req.getParameter("dot");
		
		// ��Ű�� (���� ��)
		String exp = "";		
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
				// ���
		if(operator != null && operator.equals("=")) {
			// �ڹ� ��ũ��Ʈ ���� ������
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		// C(Clear) ���
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		
		else { 	// �� �ױ�  
			// ���ڸ� ������ ���ڸ� �Ѿ���� �����ڸ� ������ �����ڸ� �Ѿ�´�
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		// ��Ű�� ������ ����
		if(operator != null && operator.equals("C")) expCookie.setMaxAge(0);
		
		expCookie.setPath("/calcPrj");
		resp.addCookie(expCookie);			
		resp.sendRedirect("calcPrj");
	}
}
