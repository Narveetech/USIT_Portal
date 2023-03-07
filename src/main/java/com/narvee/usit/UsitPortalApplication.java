package com.narvee.usit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class UsitPortalApplication extends SpringBootServletInitializer{
	
	public static final Logger logger = LoggerFactory.getLogger(UsitPortalApplication.class);
	
	public static void main(String[] args) {
		logger.info("inside UsitPortalApplicatio class, Method !!!: main method");
		System.out.println("updated usit_portal");
		SpringApplication.run(UsitPortalApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UsitPortalApplication.class);
    }
	
//	public static void delete(String[] args) {
//		String user= "sal";//change accordingly  
//		 String password="xxxxx";//change accordingly  
//		  
//		 //1) get the session object  
//		 Properties properties = System.getProperties();  
//		 Session session = Session.getDefaultInstance(properties);  
//		  
//		 //2) create the store object and connect to the current host   
//		 Store store = session.getStore("pop3");  
//		 store.connect("mail.javatpoint.com",user,password);  
//		  
//		 //3) create the folder object and open it  
//		 Folder folder = store.getFolder("inbox");  
//		  
//		 if (!folder.exists()) {  
//		 System.out.println("inbox not found");  
//		 System.exit(0);  
//		 }  
//		  
//		 folder.open(Folder.READ_WRITE);  
//		  
//		 //4) Get the message to delete  
//		 Message[] msg = folder.getMessages();  
//		  
//		 //System.out.println((messages.length+1)+" message found");  
//		 for (int i = 0; i < msg.length; i++) {  
//		   System.out.println("--------- " + (i + 1) + "------------");  
//		   String from = InternetAddress.toString(msg[i].getFrom());    
//		   
//		   if (from != null) {  
//		     System.out.println("From: " + from);  
//		   }  
//		  
//		   String replyTo = InternetAddress.toString(  
//		   msg[i].getReplyTo());  
//		   if (replyTo != null) {  
//		    System.out.println("Reply-to: " + replyTo);  
//		   }  
//		  
//		   String to = InternetAddress.toString(  
//		   msg[i].getRecipients(Message.RecipientType.TO));  
//		    
//		   if (to != null) {  
//		    System.out.println("To: " + to);  
//		   }  
//		  
//		   String subject = msg[i].getSubject();  
//		   if (subject != null) {  
//		    System.out.println("Subject: " + subject);  
//		   }  
//		   Date sent = msg[i].getSentDate();  
//		   if (sent != null) {  
//		    System.out.println("Sent: " + sent);  
//		   }  
//		   System.out.println("Message : ");  
//		   System.out.println(msg[i].getContent());  
//		  
//		 }//end of for loop  
//		  
//		  // get the message number to delete (optional)  
//		  System.out.println("Enter message number to delete :");  
//		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		  String no = br.readLine();  
//		  //5) delete the message using setFlag method  
//		  msg[Integer.parseInt(no) - 1].setFlag(FLAGS.Flag.DELETED, true);  
//		    
//		  System.out.println("Message Deleted .....");  
//		  
//		  folder.close(true);  
//		  store.close();  
//	}
}
