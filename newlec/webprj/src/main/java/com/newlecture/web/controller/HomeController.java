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
	반환값을 void로 반환 ViewResolver를 사용하지 않겠다는 의미
	*/
	
	/* 1번 HttpServletResponse */
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
	
	/* 2번 ResponseBody */
	/* ResponseBody가 없으면 "Hello Index"를 jsp로 인식하는데
	 * ResponseBody를 추가하면 ResponseBody로 인식한다
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
////		상대경로를 쓸 경우 404에러가 쓸 확률이 높아진다
////		mv.setViewName("/WEB-INF/view/index.jsp");
//		
//		ModelAndView mv = new ModelAndView("root.index");
//		mv.addObject("data", "Hello Spring MVC~");
//		
//		return mv;
//	}

}
