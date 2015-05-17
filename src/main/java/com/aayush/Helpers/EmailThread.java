package com.aayush.Helpers;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

import javax.mail.MessagingException;

/**
 * @author aahuja
 */

public class EmailThread implements Runnable{
	
	private Thread thread;
	private String host;
	private Integer id;
	private AtomicInteger emailRunning;
	private Connection conn;
	private String from_address;
	private String to_address;
	private String subject;
	private String body;
	private EmailSender sender = null;
	
	public EmailThread(String host, Integer id, AtomicInteger emailRunning, Connection conn, String from_address, String to_address, String subject, String body) {
		this.host = host;
		this.id = id;
		this.emailRunning = emailRunning;
		this.conn = conn;
		this.from_address = from_address;
		this.to_address = to_address;
		this.subject = subject;
		this.body = body;
	}
	public void run(){
		this.sender = new EmailSender(host, from_address, to_address, subject, body);
		try {
			this.sender.send(); // send the email
			DBConnection.setStatusSent(this.conn, (this.id)); // update status are 0 (sent)
			System.out.println("Sent! " + this.id.toString());
		} catch (MessagingException e) {
			DBConnection.setStatusFailed(this.conn, this.id); // update status are -1 (failed/pending)
			System.out.println("failed: " + this.id.toString());
		} finally{
			this.emailRunning.decrementAndGet();
		}
		
	}
	
	public void start() {
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
}

