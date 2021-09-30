package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title = "JDBC INSERT TEST";
		String writer_ID = "jaesa";
		String content = "insert test";
		String files = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "INSERT INTO NOTICE(TITLE, WRITER_ID, CONTENT, FILES)"
				+ "VALUES (?,?,?,?)";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
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
	}

}
