package com.newlecture.web.controller.customer;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
// @RequestMapping("/customer/notice/list")·Î ¸ÅÄª	
	@RequestMapping("list")
//	public String list(HttpServletRequest request) throws ClassNotFoundException, SQLException {
//	public String list(String p) throws ClassNotFoundException, SQLException {
//	public String list(@RequestParam("p") String page) throws ClassNotFoundException, SQLException {
	public String list(@RequestParam(name="p", defaultValue = "1") String page) throws ClassNotFoundException, SQLException {
//		String p = request.getParameter("p");
//		System.out.println("page > " + p);
		System.out.println("page > " + page);
		//List<Notice> list = noticeService.getList(1, "TITLE", "");
		
		return "notice.list";
	}
	
	// @RequestMapping("/customer/notice/detail")·Î ¸ÅÄª
	@RequestMapping("detail")
	public String detail() {
		return "notice.detail";
	}
}
