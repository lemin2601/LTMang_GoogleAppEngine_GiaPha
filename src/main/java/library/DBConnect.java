package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class DBConnect {
	public static Connection instance = null;

	// public static Connection getConnection() {
	// Connection instance = null;
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	//
	// instance = DriverManager.getConnection(
	// //
	// "jdbc:google:mysql://giapha-181215:giapha-181215:asia-northeast1:dbgiapha/db_giapha","root","123456");
	// "jdbc:mysql://localhost:3306/db_giapha?useUnicode=true&characterEncoding=utf-8",
	// "root", "");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return instance;
	// }

	public static Connection getConnection() {
		Connection conn = null;
		String url;
		if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
			// Check the System properties to determine if we are running on
			// appengine or not
			// Google App Engine sets a few system properties that will reliably
			// be present on a remote
			// instance.
			url = System.getProperty("ae-cloudsql.cloudsql-database-url");
			try {
				// Load the class that provides the new "jdbc:google:mysql://"
				// prefix.
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
			}
		} else {
			// Set the url with the local MySQL database connection url when
			// running locally
			url = System.getProperty("ae-cloudsql.local-database-url");
		}
		try {
			conn = DriverManager.getConnection(url);

		} catch (SQLException e) {
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(DBConnect.getConnection());
	}
}