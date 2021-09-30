package com.newlec.web.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.web.entity.Notice;
import com.newlec.web.service.NoticeService;

@WebServlet("/notice/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//	    물음표가 존재하기 때문에 Statement를 PreparedStatement를 사용해야 함
	    int id = Integer.parseInt(request.getParameter("id"));
	    
	    NoticeService service = new NoticeService();
	    Notice notice = service.getNotice(id);
	    request.setAttribute("n", notice);
	    
	    //forward
	    request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
	    
	    
	}

}
