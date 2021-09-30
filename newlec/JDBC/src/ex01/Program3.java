package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title = "J11DBC UPDATE TEST";
//		String writer_ID = "jaesa";
		String content = "update test";
		String files = "";
		int id = 41;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "UPDATE NOTICE "
				+ "SET"
				+ "    TITLE=?,"
				+ "    CONTENT = ?,"
				+ "    FILES = ?"
				+ "WHERE ID=?";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int rowCount = st.executeUpdate();
		
		System.out.println(rowCount);


//		rs.close();
		st.close();
		con.close();
	}

}
