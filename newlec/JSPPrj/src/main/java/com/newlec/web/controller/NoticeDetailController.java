package com.newlec.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//	    물음표가 존재하기 때문에 Statement를 PreparedStatement를 사용해야 함
	    int id = Integer.parseInt(request.getParameter("id"));

	    String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String sql = "SELECT * FROM NOTICE WHERE ID = ? ORDER BY ID";

	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setInt(1, id);
		    ResultSet rs = st.executeQuery();

		    rs.next();
		    
//		    MVC 모델중에서 M(Model)변수 아래에서
//		    title, writerId, regDate, content, files, hit이다
		    String title = rs.getString("TITLE");
		    String writerId = rs.getString("WRITER_ID");
		    Date regDate = rs.getDate("REGDATE");
		    String content = rs.getString("CONTENT");
		    String files = rs.getString("FILES");
		    int hit = rs.getInt("HIT");
		    
		    Notice notice = new Notice(
		    		id,
		    		title,
		    		writerId,
		    		regDate,
		    		content,
		    		files,
		    		hit
		    		);
		    
		    request.setAttribute("n", notice);
		    
		    /* request 저장소
		    request.setAttribute("TITLE", title);
		    request.setAttribute("WRITER_ID", writerId);
		    request.setAttribute("REGDATE", regDate);
		    request.setAttribute("CONTENT", content);
		    request.setAttribute("FILES", files);
		    request.setAttribute("HIT", hit);
		    */
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    //forward
	    request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
	    
	    
	}

}
