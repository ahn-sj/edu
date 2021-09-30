// ������ �����ε�
// �����ڰ� �������� ��쿡 �Ű������� ���� ���� �Լ���
// ������ �ϰ� �����ϴ� ������� �����Ѵ�
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
	// �ϰ�������û �޼���
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
	
	// �ϰ�������û �޼���
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
	
	// CSV�� '�޹ٷ� ���е� ��' --> "20, 30, 43, 45"
	public int pubNoticeAll(String oidsCSV, String cidsCSV){
		int result = 0;
		String sqlOpen = String.format("UPDATE NOTICE SET PUB=1 WHERE ID IN (%s)", oidsCSV);
		String sqlClose = String.format("UPDATE NOTICE SET PUB=0 WHERE ID IN (%s)", cidsCSV);
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
			//���1
			Statement stOpen = con.createStatement();
			result += stOpen.executeUpdate(sqlOpen);
			
			Statement stClose = con.createStatement();			
			result += stClose.executeUpdate(sqlClose);
			//���2
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
	
	// insert delete update�޼���� ��ȯ������ �Ϸ�� 1, �̿Ϸ�� 0
	// ������Ͽ�û �޼���
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
	
	// ����������û �޼���
	public int deleteNotice(int id){
		return 0;
	}
	
	// ����������û �޼���
	public int updateNotice(Notice notice){
		return 0;
	}
	
	// ��������û �޼���(���������� -> ��������������)
	List<Notice> getNoticeNewestList(){
		return null;
	}
	
	
	/* Notice Pages */
	// getNoticeList�� list�������� ��û�� ó���ϴ� �޼���
	// ���� ��û��(default)
	public List<NoticeView> getNoticeList() {
		return getNoticeList("title", "", 1);

	}

	// ������ ��û��
	public List<NoticeView> getNoticeList(int page) {
		return getNoticeList("title", "", page);
	}

	// �˻� ��û��
	public List<NoticeView> getNoticeList(String field/*TITLE, WRITER_ID*/, String query/*A*/, int page) {
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, N.*"
				+ "    FROM "
				+ "        (SELECT * FROM NOTICE_VIEW "
				// ?�� �ƴ϶� field�� �־��� ������ ?�� �ϰ� �� ��� field�� 
				// TITLE�̶�� ���� ���޵Ǹ� WHERE 'TITLE'�� �ν��ϰ� �ż� ������ �ִ´�
				// ��, ���� �ƴ϶� �������� �־��ش�
				+ "			WHERE " + field + " LIKE ? " 
				+ "			ORDER BY REGDATE DESC) N"
				+ "    ) "
				+ "WHERE NUM BETWEEN ? AND ?";
	
		// 1�������� 1~10(10��)������ Notice�� ���
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
		
		// pub = 1�̶�� ������ �߰� ��Ų��
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

	// getNoticeCount�� list�� ����
	// ���� ��û��(default)
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}

	// �˻� ��û��
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
	
	// getNotice�� detail�������� ��û�� ó���ϴ� �޼���
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
	
	
	// detail���������� �� ������
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
	
	// detail���������� �� ������	
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
