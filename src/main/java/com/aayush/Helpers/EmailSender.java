package com.aayush.Helpers;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * @author aahuja
 */


/*
 * A standard Email Sending Helper
 */
public class EmailSender {
	
	private String from_address;
	private String to_address;
	private String subject;
	private String body;
	private Session session;
	
	public EmailSender(String host, String from_address, String to_address, String subject, String body) {
		
		this.from_address = from_address;
		this.to_address = to_address;
		this.subject = subject;
		this.body = body;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		this.session = Session.getInstance(props);
	}
	/*
	 * Standard SMTP sending method
	 */
	public void send() throws MessagingException{
		
		MimeMessage msg = new MimeMessage(session);
		msg.setSubject(subject);
		msg.setText(body);
		msg.setSentDate(new Date());
		msg.addFrom(InternetAddress.parse(from_address));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_address, false));
		Transport.send(msg);
		System.out.println("Email Sent Successfully!!");
        
	}
}

