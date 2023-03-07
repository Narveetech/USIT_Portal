package com.narvee.usit.controller;

import java.util.Date;
import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.quartz.Job;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
* This program demonstrates how to remove e-mail messages on a mail server
* using JavaMail API.
* @author www.codejava.net
*
*/
@Service
@Component
public class EmailMessageRemover {

   /**
    * Deletes all e-mail messages whose subject field contain
    * a string specified by 'subjectToDelete'
    * @param host
    * @param port
    * @param userName
    * @param password
    * @param subjectToDelete delete if the message's subject contains this value.
    */
	//@Scheduled(cron = "* * * * * * ")
   public void deleteMessages() {
		String host = "imap.gmail.com";
		String port = "993";
        String userName = "polakayerriswamy1234@gmail.com";
        String password = "sredinxhfykbncis";
       Properties properties = new Properties();
       System.out.println("ggggggggggggggggggggg");
       // server setting
       properties.put("mail.imap.host", host);
       properties.put("mail.imap.port", port);

       // SSL setting
       properties.setProperty("mail.imap.socketFactory.class",
               "javax.net.ssl.SSLSocketFactory");
       properties.setProperty("mail.imap.socketFactory.fallback", "false");
       properties.setProperty("mail.imap.socketFactory.port",
               String.valueOf(port));

       Session session = Session.getDefaultInstance(properties);
System.out.println("vbvvvvvvvvvvvvvvv");
       try {
           // connects to the message store
           Store store = session.getStore("imap");
           store.connect(userName, password);

           // opens the inbox folder
           Folder folderInbox = store.getFolder("Spam2");
           folderInbox.open(Folder.READ_ONLY);
           
           // Open destination folder, create if reqd
           Folder destfolder = store.getFolder("[Gmail]/Trash");
           if (!destfolder.exists())
             destfolder.create(Folder.HOLDS_MESSAGES);
           System.out.println("Hello cron Scheduler Three :" +new Date());
           Message []inMessages = folderInbox.getMessages();
           if (inMessages .length != 0) {
        	   folderInbox.copyMessages(inMessages , destfolder);
           
           
           

           // fetches new messages from server
           Message[] arrayMessages = folderInbox.getMessages();
           System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+arrayMessages.length);
           for (int i = 0; i < arrayMessages.length; i++) {
        	   System.out.println("ooooooooooooooooooooooooooo");
               Message message = arrayMessages[i];
               //String subject = message.getSubject();
               //if (subject.contains(subjectToDelete)) {
                  // message.setFlag(Flags.Flag.DELETED, true);
                  
                //   System.out.println("Marked DELETE for message: " + subject);
              // }

           }
           
           }

           // expunges the folder to remove messages which are marked deleted
           boolean expunge = true;
           folderInbox.close(expunge);
System.out.println("bvnnnnnnnnnnnnnnnnnnnnnnn");
           // another way:
           //folderInbox.expunge();
           //folderInbox.close(false);

           // disconnect
           store.close();
       } catch (NoSuchProviderException ex) {
           System.out.println("No provider.");
           ex.printStackTrace();
       } catch (MessagingException ex) {
           System.out.println("Could not connect to the message store.");
           ex.printStackTrace();
       }
   }

   /**
    * Runs this program to delete e-mail messages on a Gmail account
    * via IMAP protocol.
    */

//   public static void main(String[] args) {
//       String host = "imap.gmail.com";
//       String port = "993";
//       //String userName = "your_email";
//       //String password = "your_password";
//       String userName = "polakayerriswamy1234@gmail.com";// change accordingly
//       String password = "sredinxhfykbncis";// change accordingly     
//       EmailMessageRemover remover = new EmailMessageRemover();
//System.out.println("kkkk");
//       // try to delete all messages contain this string its Subject field
//       //String subjectToDelete = "Recruiters can't find you";
//       remover.deleteMessages(host, port, userName, password);
//
//   }
}