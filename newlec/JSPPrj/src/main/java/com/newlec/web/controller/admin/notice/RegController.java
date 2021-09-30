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
		// location�� fileSizeThreshold�Ӽ��� �ѽ��̶�� ����
		// ���� �뷮�� Ŭ ��� DISK�� ����ؼ� �ӽ�����
		// ������ �����Ͱ� 1MB�� �Ѵ� ��쿡 �޸𸮸� �������� ��ũ�� ���ڴ� �ǹ�

		// maxFileSize�� �ϳ��� ���� ������(50MB)
		// maxRequestSize�� ��� ���ϵ��� ������(�������� ÷�ε� ���)(250MB)
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
			// ���� ���� ���ε带 �̿��� �� ������ 1���� ������ ��쿡�� ����� �� �ֵ���
			if(p.getSize() == 0) continue;
			
			// ���� ���� ���ε�
			Part filePart = p;		
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			// ���� ���� ���ε�
//			Part filePart = request.getPart("file"); // ÷�����ϸ�(name��)�� �޾ƿ´�
			InputStream fis = filePart.getInputStream(); // Part�� binary�� ��ȯ�Ѵ�

			// getServletContext��ü�� ������ �Լ��� �����θ� �˷��ش�
			String realPath = request.getServletContext().getRealPath("/member/upload");
			System.out.printf("realPath > %s", realPath);
			
			// upload���͸��� ���������� �����ϴ����� Ȯ���ϰ� �������� ������ ���͸��� �����Ѵ�
			File path = new File(realPath);
			if(!path.exists()) path.mkdirs();
			
			/*
			 * C:\project\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\
			 * tmp0\wtpwebapps\JSPPrj\\upload
			 */

			
			String filePath = realPath + File.separator + fileName; // File.separtor �ڹٿ��� �����ϴ� ��� ���� ���
			FileOutputStream fos = new FileOutputStream(filePath);

			byte[] buf = new byte[1024];
			int size = 0;
			// read()�� 1byte�� ��ȯ�ϱ⿡ �ݺ����� ����ؾ� �Ѵ�
			while ((size = fis.read(buf)) != -1) {
				// �������� 1024�� ���� ���� ���� �ֱ� ������ 0~123(���ð�)�� ����ϵ��� �� �� ����
				fos.write(buf, 0, size);
			}
			// filePath���� ������ ��ҿ� ����� ������ ����ȴ�
			fos.close();
			fis.close();

		}
		// ������ ��ǥ�� ���ִ� �۾�
		builder.delete(builder.length()-1, builder.length());

		// check�� �ƴϸ� null, check�� �Ǹ� true
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

		// /admin/board/notice/list�� �̵�
		response.sendRedirect("list");

//		response.getWriter().printf("%s %s %s", title_, content_, isOpen_);
	}
}
