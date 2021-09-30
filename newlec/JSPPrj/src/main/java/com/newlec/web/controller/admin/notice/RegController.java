package com.newlec.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlec.web.entity.Notice;
import com.newlec.web.service.NoticeService;

@MultipartConfig(
		// location과 fileSizeThreshold속성은 한쌍이라고 생각
		// 파일 용량이 클 경우 DISK를 사용해서 임시저장
		// 전송할 데이터가 1MB가 넘는 경우에 메모리를 쓰지말고 디스크를 쓰자는 의미

		// maxFileSize는 하나의 파일 사이즈(50MB)
		// maxRequestSize는 모든 파일들의 사이즈(여러개가 첨부된 경우)(250MB)
		fileSizeThreshold = 1024 * 1024, // 1KB * 1KB = 1MB
		maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String title = request.getParameter("title");
		System.out.println("title > " + title);
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open"); // checkbox
		boolean pub = false;

		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		
		for (Part p : parts) {
			if (!p.getName().equals("file")) continue;
			// 다중 파일 업로드를 이용할 때 파일을 1개만 선택한 경우에도 등록할 수 있도록
			if(p.getSize() == 0) continue;
			
			// 다중 파일 업로드
			Part filePart = p;		
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			// 단일 파일 업로드
//			Part filePart = request.getPart("file"); // 첨부파일명(name값)을 받아온다
			InputStream fis = filePart.getInputStream(); // Part는 binary를 반환한다

			// getServletContext객체가 가지는 함수가 절대경로를 알려준다
			String realPath = request.getServletContext().getRealPath("/member/upload");
			System.out.printf("realPath > %s", realPath);
			
			// upload디렉터리가 물리적으로 존재하는지를 확인하고 존재하지 않으면 디렉터리를 생성한다
			File path = new File(realPath);
			if(!path.exists()) path.mkdirs();
			
			/*
			 * C:\project\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\
			 * tmp0\wtpwebapps\JSPPrj\\upload
			 */

			
			String filePath = realPath + File.separator + fileName; // File.separtor 자바에서 제공하는 경로 구분 방법
			FileOutputStream fos = new FileOutputStream(filePath);

			byte[] buf = new byte[1024];
			int size = 0;
			// read()는 1byte씩 반환하기에 반복문을 사용해야 한다
			while ((size = fis.read(buf)) != -1) {
				// 마지막에 1024가 되지 않을 수도 있기 때문에 0~123(예시값)만 사용하도록 할 수 있음
				fos.write(buf, 0, size);
			}
			// filePath에서 지정한 장소에 등록한 파일이 저장된다
			fos.close();
			fis.close();

		}
		// 마지막 쉼표를 빼주는 작업
		builder.delete(builder.length()-1, builder.length());

		// check가 아니면 null, check가 되면 true
		if (isOpen != null)
			pub = true;

		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("newlec");
		notice.setFiles(builder.toString());

		NoticeService service = new NoticeService();
		service.insertNotice(notice);

		// /admin/board/notice/list로 이동
		response.sendRedirect("list");

//		response.getWriter().printf("%s %s %s", title_, content_, isOpen_);
	}
}
