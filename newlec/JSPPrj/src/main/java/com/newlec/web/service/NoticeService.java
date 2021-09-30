// 생성자 오버로드
// 생성자가 여러개인 경우에 매개변수가 제일 많은 함수에
// 구현을 하고 접근하는 방식으로 구현한다
package com.newlec.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.newlec.web.entity.Notice;
import com.newlec.web.entity.NoticeView;

public class NoticeService {
	/* Admin Pages */
	// 일괄삭제요청 메서드
	public int deleteNoticeAll(int[] ids){
		int result = 0;
		String params = "";
		
		for(int i = 0; i < ids.length; i++) {
			params += ids[i];
			
			if(ids.length-1 > i) params += ",";
		}
		
		String sql = "DELETE NOTICE WHERE ID IN ("+ params + ")";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);

			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	// 일괄공개요청 메서드
	public int pubNoticeAll(int[] oids, int[] cids){
		List<String> oidsList = new ArrayList<>();
		List<String> cidsList = new ArrayList<>();
		
		for(int i = 0; i < oids.length; i++) oidsList.add(String.valueOf(oids[i]));
		for(int i = 0; i < oids.length; i++) cidsList.add(String.valueOf(cids[i]));
		
		return pubNoticeAll(oidsList, cidsList);
	}
	
	public int pubNoticeAll(List<String> oids, List<String> cids){
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		
		return pubNoticeAll(oidsCSV, oidsCSV);
	}
	
	// CSV는 '콤바로 구분된 값' --> "20, 30, 43, 45"
	public int pubNoticeAll(String oidsCSV, String cidsCSV){
		int result = 0;
		String sqlOpen = String.format("UPDATE NOTICE SET PUB=1 WHERE ID IN (%s)", oidsCSV);
		String sqlClose = String.format("UPDATE NOTICE SET PUB=0 WHERE ID IN (%s)", cidsCSV);
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			//방법1
			Statement stOpen = con.createStatement();
			result += stOpen.executeUpdate(sqlOpen);
			
			Statement stClose = con.createStatement();			
			result += stClose.executeUpdate(sqlClose);
			//방법2
//			int resultOpen = stOpen.executeUpdate(sqlOpen);
//			int resultClose = stOpen.executeUpdate(sqlClose);
//
			stOpen.close();
			stClose.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
	
	// insert delete update메서드는 반환값으로 완료시 1, 미완료시 0
	// 공지등록요청 메서드
	public int insertNotice(Notice notice){
		int result = 0;
		String params = "";
		
		String sql = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID, PUB, FILES) VALUES (?,?,?,?,?)";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriterId());
			st.setBoolean(4, notice.getPub());
			st.setString(5, notice.getFiles());
			result = st.executeUpdate();

			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	// 공지삭제요청 메서드
	public int deleteNotice(int id){
		return 0;
	}
	
	// 공지수정요청 메서드
	public int updateNotice(Notice notice){
		return 0;
	}
	
	// 페이지요청 메서드(메인페이지 -> 공지사항페이지)
	List<Notice> getNoticeNewestList(){
		return null;
	}
	
	
	/* Notice Pages */
	// getNoticeList는 list페이지의 요청을 처리하는 메서드
	// 문서 요청시(default)
	public List<NoticeView> getNoticeList() {
		return getNoticeList("title", "", 1);

	}

	// 페이지 요청시
	public List<NoticeView> getNoticeList(int page) {
		return getNoticeList("title", "", page);
	}

	// 검색 요청시
	public List<NoticeView> getNoticeList(String field/*TITLE, WRITER_ID*/, String query/*A*/, int page) {
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, N.*"
				+ "    FROM "
				+ "        (SELECT * FROM NOTICE_VIEW "
				// ?가 아니라 field를 넣어준 이유는 ?로 하게 될 경우 field에 
				// TITLE이라는 값이 전달되면 WHERE 'TITLE'로 인식하게 돼서 변수로 넣는다
				// 즉, 값이 아니라서 변수명을 넣어준다
				+ "			WHERE " + field + " LIKE ? " 
				+ "			ORDER BY REGDATE DESC) N"
				+ "    ) "
				+ "WHERE NUM BETWEEN ? AND ?";
	
		// 1페이지에 1~10(10개)까지의 Notice를 출력
		// 1, 11, 21, 31, ... -> an = a1 + (n-1) * 10 -> 1 + (page-1) * 10
		// 10, 20, 30, 40 -> page * 10
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1 + (page-1) * 10);
			st.setInt(3, page * 10);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				//String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				boolean pub = rs.getBoolean("PUB");
				int cmtCount = rs.getInt("CMT_COUNT");
				
				NoticeView notice = new NoticeView( 
			    		id,
			    		title,
			    		writerId,
			    		regDate,
			    		//content,
			    		files,
			    		hit,
			    		pub,
			    		cmtCount
			    		);
				
				list.add(notice);
			}
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
		return list;
	}
	
	public List<NoticeView> getNoticePubList(String field, String query, int page) {
		List<NoticeView> list = new ArrayList<>();
		
		// pub = 1이라는 조건을 추가 시킨다
		String sql = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, N.*"
				+ "    FROM "
				+ "        (SELECT * FROM NOTICE_VIEW "
				+ "			WHERE " + field + " LIKE ? " 
				+ "			ORDER BY REGDATE DESC) N"
				+ "    ) "
				+ "WHERE PUB = 1 AND NUM BETWEEN ? AND ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1 + (page-1) * 10);
			st.setInt(3, page * 10);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				//String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				boolean pub = rs.getBoolean("PUB");
				int cmtCount = rs.getInt("CMT_COUNT");
				
				NoticeView notice = new NoticeView( 
			    		id,
			    		title,
			    		writerId,
			    		regDate,
			    		//content,
			    		files,
			    		hit,
			    		pub,
			    		cmtCount
			    		);
				
				list.add(notice);
			}
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
		return list;
	}

	// getNoticeCount는 list의 개수
	// 문서 요청시(default)
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}

	// 검색 요청시
	public int getNoticeCount(String field, String query) {
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM ("
					+ "    SELECT ROWNUM NUM, N.*"
					+ "    FROM "
					+ "        (SELECT * FROM NOTICE "
					+ "			WHERE " + field + " LIKE ? " 
					+ "			ORDER BY REGDATE DESC) N"
					+ "    ) ";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();

			if(rs.next())
				count = rs.getInt("count");
//			int lastcount/10
//			int lastPage = count/10; // 100 -> 10, 93/10 -> 9
//			lastPage = count%10 == 0? lastPage: lastPage+1;
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
		
		return count;
	}
	
	// getNotice는 detail페이지의 요청을 처리하는 메서드
	public Notice getNotice(int id) {
		Notice notice = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(
			    		nid,
			    		title,
			    		writerId,
			    		regDate,
			    		content,
			    		files,
			    		hit,
			    		pub
			    		);		
			}
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
		return notice;
	}
	
	
	// detail페이지에서 앞 페이지
	public Notice getPrevNotice(int id) {
		Notice notice = null;

		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT ID FROM "
				+ "    (SELECT * FROM NOTICE ORDER BY REGDATE DESC) "
				+ "WHERE REGDATE < "
				+ "    (SELECT REGDATE FROM NOTICE WHERE ID=?)"
				+ "    AND ROWNUM=1";
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(
			    		nid,
			    		title,
			    		writerId,
			    		regDate,
			    		content,
			    		files,
			    		hit,
			    		pub
			    		);		
			}
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
		
		return notice;
	}
	
	// detail페이지에서 뒤 페이지	
	public Notice getNextNotice(int id) {
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE "
				+ "WHERE ID = ("
				+ "    SELECT ID FROM NOTICE"
				+ "    WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID=?)"
				+ "    AND ROWNUM=1"
				+ ")";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(
			    		nid,
			    		title,
			    		writerId,
			    		regDate,
			    		content,
			    		files,
			    		hit,
			    		pub
			    		);		
			}
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
		return notice;
	}

	
}
