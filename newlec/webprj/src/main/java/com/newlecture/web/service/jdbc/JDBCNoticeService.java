package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@Component
public class JDBCNoticeService implements NoticeService{
//	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
//	private String uid = "NEWLEC";
//	private String pwd = "1207";
//	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	@Autowired
	private DataSource dataSource;
		
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	// SELECT 구현
	// field -> 검색 범주, query -> 검색어
	public List<Notice> getList(int page, String field, String query) throws SQLException, ClassNotFoundException {
		// 등차수열 공식 : a + (n-1)d
		int start = 1 + (page-1) * 10; // 1, 11, 21 , ...
		int end = 10 * page; // 10, 20, 30, ...
		
		
		String sql = "SELECT * FROM NOTICE_VIEW WHERE "
				+ field + " LIKE ? AND NUM BETWEEN ? AND ?";

//		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+ query + "%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();

		List<Notice> list = new ArrayList<Notice>();
		// 조회수가 10이상인 게시글만 출력되도록 하시오
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");

			Notice notice = new Notice(id, title, writerId, regDate, content, hit, files);
			list.add(notice);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}
	
	// 게시글 총 개수 구하기
	// Scalar 함수 --> 단일 값을 얻는 함수
	public int getCount() throws ClassNotFoundException, SQLException {
			int count = 0;
			
			String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";

//			Class.forName(driver);
//			Connection con = DriverManager.getConnection(url, uid, pwd);
			Connection con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if(rs.next())
				count = rs.getInt("COUNT");

			rs.close();
			st.close();
			con.close();

			return count;
		}

	// INSERT 구현
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writer_ID = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String sql = "INSERT INTO NOTICE(TITLE, WRITER_ID, CONTENT, FILES)"
				+ "VALUES (?,?,?,?)";

//		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writer_ID);
		st.setString(3, content);
		st.setString(4, files);
		
		int rowCount = st.executeUpdate();
		
		System.out.println(rowCount);


//		rs.close();
		st.close();
		con.close();
		
		return rowCount;
	}
	
	// UPDATE 구현
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
//		String writer_ID = "jaesa";
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String sql = "UPDATE NOTICE "
				+ "SET"
				+ "    TITLE=?,"
				+ "    CONTENT = ?,"
				+ "    FILES = ?"
				+ "WHERE ID=?";

//		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int rowCount = st.executeUpdate();
		
//		rs.close();
		st.close();
		con.close();
		
		return rowCount;
	}
	
	// DELETE 구현
	public int delete(int id) throws SQLException, ClassNotFoundException {
	String sql = "DELETE NOTICE WHERE ID=?";

//	Class.forName(driver);
//	Connection con = DriverManager.getConnection(url, uid, pwd);
	Connection con = dataSource.getConnection();
//	Statement st = con.createStatement();
//	ResultSet rs = st.executeQuery(sql);
	PreparedStatement st = con.prepareStatement(sql);
	st.setInt(1, id);
	
	int rowCount = st.executeUpdate();

//	rs.close();
	st.close();
	con.close();
	
		return rowCount;
	}


	
}
