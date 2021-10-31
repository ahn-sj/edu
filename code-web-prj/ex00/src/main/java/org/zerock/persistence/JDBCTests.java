package org.zerock.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/XE";
		String uid = "c##book_ex";
		String pwd = "book_ex";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		
		
//		Class clz = Class.forName("oracle.jdbc.driver.OracleDriver");
//		log.info(clz);
		
		Class.forName(driver);
		
		
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		log.info(con);
		con.close();
		
		
	}
}
