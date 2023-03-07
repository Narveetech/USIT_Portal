package com.narvee.usit.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailScheduler {
	
	public static void sendEmail(String toEmail, String subject, String body){
		try
	    {
		  	
		  final String fromEmail = "polakayerriswamy1234@gmail.com"; //requires valid gmail id
		  final String password = "sredinxhfykbncis"; // correct password for gmail id
		 
		  Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
		  };
		  
		  Properties props = new Properties();
		  props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		  props.put("mail.smtp.port", "587"); //TLS Port
	      props.put("mail.smtp.auth", "true"); //enable authentication
		  props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			
		  Session session = Session.getInstance(props, auth); 
		  MimeMessage msg = new MimeMessage(session);
		  
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("polakayerriswamy1234@gmail.com", "NoReply-JD"));

	      msg.setReplyTo(InternetAddress.parse("polakayerriswamy1234@gmail.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
		
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
}
