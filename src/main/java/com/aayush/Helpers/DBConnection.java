package com.aayush.Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author aahuja
 */


/*
 * Helper methods to help in communicating with Database
 * 
 */
public class DBConnection {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static Config conf = ConfigFactory.load();
	private static String db_url = conf.getString("db.url");
	private static String username = conf.getString("db.user");
	private static String password = conf.getString("db.password");
	
	
	/*
	 * status = 0 -> sent
	 * status = 1 -> failed/not sent
	 * status > 1 -> not sent / failed(went for retry)
	 * status = -1 -> sending state
	 */
	
	/*
	 * Fetch pending email. Locks the rows till they are updated.
	 * Emails which have had more than max_retries retries are discarded.
	 */
	public static ResultSet fetchPendingEmails(Connection conn, Integer count, Integer max_retries){
		
		ResultSet rs = null;
		try {
			// fetch defined number of not sent emails (status >= 1). Make transaction for select and update them as running (status=1)
			String query = String.format("SELECT * FROM emailqueue WHERE status>=1 and status<=%s LIMIT %s FOR UPDATE", max_retries, count);
			rs = conn.createStatement().executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/*
	 * Set the status of a row as Running (status = -1)
	 */
	public static void setStatusRunning(Connection conn, Integer id){
		String query = "UPDATE emailqueue SET status=-1 WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Set the status of a row as Sent (status = 0)
	 */
	public static void setStatusSent(Connection conn, Integer id){
		String query = "UPDATE emailqueue SET status=0 WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Set the status of a row as Failed (status > 1)
	 */
	public static void setStatusFailed(Connection conn, Integer id){
		String query = "UPDATE emailqueue SET status=status+1 WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getDb_url() {
		return db_url;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static String getPassword() {
		return password;
	}
}

