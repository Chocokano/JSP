package kr.co.kmarket.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBHelper {
	
	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	public Connection getConnection() {
		
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/dbcp_kmarket");
			conn = ds.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close() {
		try {
			
			if (psmt != null) {
			      try { psmt.close(); } catch (SQLException e) { ; }
			      psmt = null;
				 }
			if (rs != null) {
		      try { rs.close(); } catch (SQLException e) { ; }
		      rs = null;
			 }
		    if (stmt != null) {
		      try { stmt.close(); } catch (SQLException e) { ; }
		      stmt = null;
		    }
		    if (conn != null) {
		      try { conn.close(); } catch (SQLException e) { ; }
		      conn = null;
		    }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
