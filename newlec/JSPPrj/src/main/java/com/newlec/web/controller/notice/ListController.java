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
		// �˻���� ����
		// list?f=title&q=a
		String field = "title";
		String query = "";		
		int page = 1;
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		// page�� �������ε� String���� ���ִ� ������?
		//   �� ���� �Է��� ���������� null�� ���޵ǰ� �Ǵµ� int�� null�� ���� ���Ѵ�.
		//   �� ����, Integer�� ��ȯ���� ������ �Ǳ� ������ String�� �ٶ����ϴ�
		
		if(field_ != null && !field_.equals("")) field = field_;
		if(query_ != null && !query_.equals("")) query = query_;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticePubList(field, query, page);
		// ������ ��ȣ
		int count = service.getNoticeCount(field, query);
				
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}
}
