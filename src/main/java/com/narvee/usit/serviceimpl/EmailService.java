package com.narvee.usit.serviceimpl;

import java.net.http.HttpRequest;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Configuration
public class EmailService {
	// commented for  mail error
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMailWithInlineResources(String to, String subject, String fileToAttach){
	    MimeMessagePreparator preparator = new MimeMessagePreparator()
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            mimeMessage.setFrom(new InternetAddress("arun@narveetech.com"));
	            mimeMessage.setSubject(subject);
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            String ar =  null;
	            if(fileToAttach.equals("Resetpassword")) {
	             ar = "Please click below link to change the reset the password </br></br><a href='http://localhost:4200/reset-password'>Click here </a>";
	            }
	            else {
	            ar = "This is your new password = >"+fileToAttach;
	            }
	            //String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	           // sendEmail(email, resetPasswordLink);
	            
	            
	            helper.setText(ar, true);
	            System.out.println(ar);
//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
	        }
	    };
	    try {
	        mailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	}


}
