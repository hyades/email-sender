package com.aayush;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import com.aayush.Helpers.DBConnection;
import com.aayush.Helpers.EmailThread;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class Main {
	
    public static void main(String[] args){
    	
    	
    	Config conf = ConfigFactory.load();
    	String host = conf.getString("smtp.host");
    	Integer max_retries = conf.getInt("thread.max_retries");
    	max_retries++;
    	
    	Integer maxEmailRunning = conf.getInt("thread.concurrency"); 
    	AtomicInteger emailRunning = new AtomicInteger(); // maintain a count of running emails
    	
    	Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBConnection.getDb_url(), DBConnection.getUsername(), DBConnection.getPassword());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	
        while(true){ // runs forever polling the db for new emails
        	if(emailRunning.get() < maxEmailRunning){ // only run if less number of threads are running
	        	ResultSet pendingEmails = null;
				try {
					
					pendingEmails = DBConnection.fetchPendingEmails(conn, maxEmailRunning - emailRunning.get(), max_retries); // fetch some emails from db which are pending
					while(pendingEmails.next()){
						emailRunning.incrementAndGet();
						Integer id = pendingEmails.getInt("id");
						String from_address = pendingEmails.getString("from_email_address");
						String to_address = pendingEmails.getString("to_email_address");
						String subject = pendingEmails.getString("subject");
						String body = pendingEmails.getString("body");
						DBConnection.setStatusRunning(conn, id);
						// create thread for each email
						EmailThread emailThread = new EmailThread(host, id, emailRunning, conn, from_address, to_address, subject, body);
						emailThread.start();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
        
        
        
    }
}
