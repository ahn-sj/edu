package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE HIT >= 10";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		// 조회수가 10이상인 게시글만 출력되도록 하시오
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("hit");

			System.out.println(id + " " + title + " " 
			+ writerId + " " + regDate + " " + content + " " + hit);			
		}

		rs.close();
		st.close();
		con.close();
	}

}
