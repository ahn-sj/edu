package com.newlecture.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
//public class IndexController implements Controller {
public class HomeController {
	
	/*
	@RequestMapping("index")
	public String index() {
		
//		ModelAndView mv = new ModelAndView("root.index");
		return "root.index";		
	}
	��ȯ���� void�� ��ȯ ViewResolver�� ������� �ʰڴٴ� �ǹ�
	*/
	
	/* 1�� HttpServletResponse */
	/*
	@RequestMapping("index")
	public void index(HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("Hello Index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/* 2�� ResponseBody */
	/* ResponseBody�� ������ "Hello Index"�� jsp�� �ν��ϴµ�
	 * ResponseBody�� �߰��ϸ� ResponseBody�� �ν��Ѵ�
	 * */
	@RequestMapping("index")
	@ResponseBody
	public String index() {
		return "Hello Index 1";
	}
	
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
////		ModelAndView mv = new ModelAndView();
////		
////		mv.addObject("data", "Hello Spring MVC~");
////		����θ� �� ��� 404������ �� Ȯ���� ��������
////		mv.setViewName("/WEB-INF/view/index.jsp");
//		
//		ModelAndView mv = new ModelAndView("root.index");
//		mv.addObject("data", "Hello Spring MVC~");
//		
//		return mv;
//	}

}
