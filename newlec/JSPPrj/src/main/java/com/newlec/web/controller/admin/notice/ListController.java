package com.newlec.web.controller.admin.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.web.entity.NoticeView;
import com.newlec.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field = "title";
		String query = "";		
		int page = 1;
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		if(field_ != null && !field_.equals("")) field = field_;
		if(query_ != null && !query_.equals("")) query = query_;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticeList(field, query, page);
		// 페이지 번호
		int count = service.getNoticeCount(field, query);
				
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 다중 선택값 POST
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");		
		String cmd = request.getParameter("cmd");
		String ids_ = request.getParameter("ids");
		String[] ids = ids_.trim().split(" ");
		
		NoticeService service = new NoticeService();
		
		switch(cmd) {
		case "일괄공개":
			for(String openId : openIds) 
				System.out.printf("open id : %s \n", openId);
			
			// Arrays.asList --> array to List
			List<String> oids = Arrays.asList(openIds);
			
			List<String> cids = new ArrayList(Arrays.asList(ids));
			cids.removeAll(oids);
			System.out.println(Arrays.asList(ids));
			System.out.println(oids);
			System.out.println(cids);
			
			
			
//			for(int i = 0; i < ids.length; i++) {
//				// 1. 현재 open된 상태인 id
//				// if(ids = 1,2,3,4,5,6,7,8,9,10) (openIds = 3,5,7)
//				// ids - openIds --> 1,2,4,6,8,9,10
//				if(oids.contains(ids[i]))  // pub -> 1
//					
//				else // pub -> 0
//			}
			
			// Transaction 처리 (두개의 함수를 하나의 함수처럼 묶는 것)
//			service.pubNoticeList(opnIds);		// UPDATE NOTICE SET PUB=1 WHERE ID IN (...)
//			service.closeNoticeList(clsIds);	// UPDATE NOTICE SET PUB=0 WHERE ID IN (...)
			
			// 트랜잭션 처리를 위해 위 두 함수를 하나의 함수로 만든다
			// UPDATE NOTICE SET PUB=1 WHERE ID IN (...) or UPDATE NOTICE SET PUB=0 WHERE ID IN (...)
			service.pubNoticeAll(oids, cids);
			
			break;
		case "일괄삭제":
			
			int[] ids1 = new int[delIds.length];
			for(int i = 0; i < delIds.length; i++) ids1[i] = Integer.parseInt(delIds[i]);
			int result = service.deleteNoticeAll(ids1);
			break;
		}
		
		// 아래 명령은 GET요청을 한다 = doGet()이 실행된다
		response.sendRedirect("list");
	}
}
