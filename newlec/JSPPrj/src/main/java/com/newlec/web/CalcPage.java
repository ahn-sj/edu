package com.newlec.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcPage")
public class CalcPage extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
//		<!DOCTYPE html>
//		<html>
//		<head>
//		<meta charset="UTF-8">
//		<title>Insert title here</title>
//		<style>
//		input {
//			width: 50px;
//			height: 50px;
//			}
//			
//		.output {
//			height: 50px;
//			background: #e9e9e9;
//			font-size: 24px;
//			font-weight: bold;
//			text-align: right;
//			padding: 0px 5px;
//		}	
//		</style>
//		</head>
//		<body>
//			<div>
//				<form action="calcPrj" method="post">
//				<!-- 행 6개 열 4개 -->
//					<table> 
//						<tr>
//							<td class="output" colspan="4">0</td>				
//						</tr>
//						<tr>
//							<td><input type="submit" name="oper" value="CE"></td>
//							<td><input type="submit" name="oper" value="C"></td>
//							<td><input type="submit" name="oper" value="BS"></td>
//							<td><input type="submit" name="oper" value="÷"></td>					
//						</tr>
//						<tr>
//							<td><input type="submit" name="value" value="7"></td>
//							<td><input type="submit" name="value" value="8"></td>
//							<td><input type="submit" name="value" value="9"></td>
//							<td><input type="submit" name="oper" value="X"></td>					
//						</tr>
//						<tr>
//							<td><input type="submit" name="value" value="4"></td>
//							<td><input type="submit" name="value" value="5"></td>
//							<td><input type="submit" name="value" value="6"></td>
//							<td><input type="submit" name="oper" value="-"></td>					
//						</tr>
//						<tr>
//							<td><input type="submit" name="value" value="1"></td>
//							<td><input type="submit" name="value" value="2"></td>
//							<td><input type="submit" name="value" value="3"></td>
//							<td><input type="submit" name="oper" value="+"></td>					
//						</tr>
//						<tr>
//							<td></td>
//							<td><input type="submit" name="value" value="0"></td>
//							<td><input type="submit" name="dot" value="."></td>
//							<td><input type="submit" name="oper" value="="></td>					
//						</tr>
//					</table>
//				</form>
//			</div>
//		</body>
//		</html>
	}
}
