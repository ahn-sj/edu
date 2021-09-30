package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program4 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int id = 41;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "DELETE NOTICE WHERE ID=?";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "1207");
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int rowCount = st.executeUpdate();
		
		System.out.println(rowCount);


//		rs.close();
		st.close();
		con.close();
	}

}
