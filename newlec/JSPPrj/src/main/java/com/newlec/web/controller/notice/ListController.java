package com.newlec.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.web.entity.Notice;
import com.newlec.web.entity.NoticeView;
import com.newlec.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색기능 구현
		// list?f=title&q=a
		String field = "title";
		String query = "";		
		int page = 1;
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		// page는 정수형인데 String으로 해주는 이유는?
		//   ㄴ 만약 입력이 되지않으면 null이 전달되게 되는데 int는 null을 받지 못한다.
		//   ㄴ 또한, Integer는 변환에서 문제가 되기 때문에 String이 바람직하다
		
		if(field_ != null && !field_.equals("")) field = field_;
		if(query_ != null && !query_.equals("")) query = query_;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticePubList(field, query, page);
		// 페이지 번호
		int count = service.getNoticeCount(field, query);
				
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}
}
