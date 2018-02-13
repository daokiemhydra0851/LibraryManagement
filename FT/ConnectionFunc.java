package FT;

import java.sql.*;

public class ConnectionFunc {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/OurProject";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "dddddddd"; // static final =  CONST
	 
	private static Connection conn;
	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch(Exception e){
			
		}
		return conn;
	}
	
	
}
