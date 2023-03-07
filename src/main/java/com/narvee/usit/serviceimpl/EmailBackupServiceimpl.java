package com.narvee.usit.serviceimpl;


/*
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Email;
import com.narvee.usit.repository.IEmailRepository;
import com.narvee.usit.service.IEmailBackupService;

//commented for mail error
@Service
public class EmailBackupServiceimpl  implements IEmailBackupService{

	
	@Autowired
	private IEmailRepository repo;

	// @Autowired(required = true)
	MimeMessage mm;

	private String saveDirectory = "C:/Users/polak/OneDrive/Desktop/swamy";
	
	Email email = new Email();

	@Override
	public void saveBackup(Email email) {

	}

	@Override
	public void saveBackup(String host, String port, String userName, String password) {
		Properties properties = new Properties();
		// server setting
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);
		Email e = null;
		// SSL setting
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("pop3");
			store.connect(userName, password);

			// opens the INBOX folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = 0; i < arrayMessages.length; i++) {
				e = new Email();
				Message message = arrayMessages[i];

				// Getting Emailed_From
				Address[] fromAdd = message.getFrom();
				String from = fromAdd[0].toString();
				if (from.contains("<")) {

					String value = from.substring(from.indexOf("<") + 1, from.indexOf(">", from.indexOf(">")));

					// System.out.println("From======>" + value);
					e.setFrom(value);

				} else {
					// System.out.println("From======>" + from);
					e.setFrom(from);
				}

				// Getting Emailed_To
				Address[] to = message.getRecipients(Message.RecipientType.TO);

				String to1 = to[0].toString();

				if (to1.contains("<")) {

					String value = to1.substring(to1.indexOf("<") + 1, to1.indexOf(">", to1.indexOf(">")));

					// System.out.println("To=======> " + value);
					e.setTo(value);

				} else {
					// System.out.println("To=======> " + to1);
					e.setTo(to1);
				}

				// Getting Emailed_CC
				Address[] cc = message.getRecipients(Message.RecipientType.CC);

				String finalCC = "";

				try {
					for (int j = 0; j < cc.length; j++) {

						String fromCC = cc[j].toString();

						if (fromCC.contains("<")) {

							String value = fromCC.substring(fromCC.indexOf("<") + 1,
									fromCC.indexOf(">", fromCC.indexOf(">")));

							value = value.concat(", ");

							finalCC = finalCC.concat(value);

						} else {
							fromCC = fromCC.concat(",");
							finalCC = finalCC.concat(fromCC);
						}
					}
					finalCC = finalCC.substring(0, finalCC.length() - 1);

					// System.out.println("CC=====>" + finalCC);

				} catch (NullPointerException e1) {

				}

				String subject = message.getSubject();
				String sentDate = message.getSentDate().toString();

				String contentType = message.getContentType();
				String messageContent = "";

				//System.out.println(subject + "=============================================" + contentType+" ============================== "+messageContent);

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(saveDirectory + File.separator + fileName);
						} 
						else if (part.isMimeType("multipart/*")) {
							MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
							 messageContent = getTextFromMimeMultipart(mimeMultipart);
							 System.out.println("message content after else if=====>"+messageContent);
						}
						
						else {
							// this part may be the message content
							messageContent = part.getContent().toString();
							// System.out.println("message content before else if====>"+messageContent);
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				}
				else if (contentType.contains("text/plain") || contentType.contains("text/html")
						|| contentType.contains("multipart/mixed") || contentType.contains("application/octet-stream") ) 
				{
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
						 System.out.println("message content after else if=====>"+messageContent);
					}
				}
				e.setSubject(subject);
				e.setCc(finalCC);
				// e.setBcc(ebcc);
				// e.setBody2(messageContent);
				e.setAttachment(attachFiles);
				e.setBody(messageContent);
				repo.save(e);
				arrayMessages[i].setFlag(Flags.Flag.DELETED, true);
			}
			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			// System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			// System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	private String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text().toString();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}

	@Override
	public List<Email> getAllEmails() {

		return repo.findAll();
	}

	@Override
	public Email getEmail(long id) {

		return repo.findById(id).get();
	}

	@Override
	public List<Email> findEmailByFilter(String keyword) {
		if (keyword != null) {
			return repo.getAllEmailBasedOnFilter(keyword);
		}
		return repo.findAll();
	}
	
	@Override
	public String searchEmail(String host, String port, String userName,
            String password, final String keyword) {
        Properties properties = new Properties();
 
        // server setting
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.host", port);
 
        // SSL setting
        properties.setProperty("mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        properties.setProperty("mail.pop3.socketFactory.port",
                String.valueOf(port));
 
        Session session = Session.getDefaultInstance(properties);
 
        try {
            // connects to the message store
            Store store = session.getStore("pop3");
            store.connect(userName, password);
 
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
 
            // creates a search criterion
            SearchTerm searchCondition = new SearchTerm() {
            	  @Override
                  public boolean match(Message message) {
            		  try {
            	            Address[] fromAddress = message.getFrom();
            	            if (fromAddress != null && fromAddress.length > 0) {
            	                if (fromAddress[0].toString().contains(keyword)) {
            	                    return true;
            	                }
            	            }
            	        } catch (MessagingException ex) {
            	            ex.printStackTrace();
            	        }
            	         
            	        return false;
            	  
                  }
            };
 
            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);
 
            for (int i = 0; i < foundMessages.length; i++) {
                Message message = foundMessages[i];
                if(message != null) {
                String subject = message.getSubject();
                System.out.println("Found message #" + i + ": " + subject);

                }

            }
 
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        } 
		return null;
	
    }
	
	@Override
	  public void deleteMessages(String host, String port,
	            String userName, String password, String subjectToDelete) {
	        Properties properties = new Properties();
	 
	        // server setting
	        properties.put("mail.pop3.host", host);
	        properties.put("mail.pop3.port", port);
	 
	        // SSL setting
	        properties.setProperty("mail.pop3.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
	        properties.setProperty("mail.pop3.socketFactory.port",
	                String.valueOf(port));
	 
	        Session session = Session.getDefaultInstance(properties);
	 
	        try {
	            // connects to the message store
	            Store store = session.getStore("pop3");
	            store.connect(userName, password);
	 
	            // opens the inbox folder
	            Folder folderInbox = store.getFolder("INBOX");
	            folderInbox.open(Folder.READ_WRITE);
	 
	            // fetches new messages from server
	            Message[] arrayMessages = folderInbox.getMessages();
	 
	            for (int i = 0; i < arrayMessages.length; i++) {
	                Message message = arrayMessages[i];
	                String subject = message.getSubject();
	                if (subject.contains(subjectToDelete)) {
	                    message.setFlag(Flags.Flag.DELETED, true);
	                    System.out.println("Marked DELETE for message: " + subject);
	                }
	 
	            }
	 
	            // expunges the folder to remove messages which are marked deleted
	            boolean expunge = true;
	            folderInbox.close(expunge);
	 
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
	    
	    

}

*/
//package com.narvee.usit.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.narvee.usit.entity.Email;
import com.narvee.usit.repository.IEmailRepository;
import com.narvee.usit.service.IEmailBackupService;
//commented for mail error
@Service
public class EmailBackupServiceimpl implements IEmailBackupService {

	@Autowired
	private IEmailRepository repo;
	private String saveDirectory = "D:\\swamy\\email";

	//private String saveDirectory = "E:/stores";

	 private Properties getServerProperties(String protocol, String host,
	            String port) {
	        Properties properties = new Properties();
	 
	        // server setting
	        properties.put(String.format("mail.%s.host", protocol), host);
	        properties.put(String.format("mail.%s.port", protocol), port);
	 
	        // SSL setting
	        properties.setProperty(
	                String.format("mail.%s.socketFactory.class", protocol),
	                "javax.net.ssl.SSLSocketFactory");
	        properties.setProperty(
	                String.format("mail.%s.socketFactory.fallback", protocol),
	                "false");
	        properties.setProperty(
	                String.format("mail.%s.socketFactory.port", protocol),
	                String.valueOf(port));
	 
	        return properties;
	    }
	
	Email getemailsss; 
	@Override
	public void downloadEmails(String protocol, String host, String port,
            String userName, String password) {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);
 
        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            store.connect(userName, password);
 
            // opens the inbox folder
            Folder folderInbox = store.getFolder("[Gmail]/Drafts");
            folderInbox.open(Folder.READ_ONLY);
 
            // fetches new messages from server
            Message[] messages = folderInbox.getMessages();
 
            for (int i = 0; i < messages.length; i++) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(RecipientType.CC));
                String sentDate = msg.getSentDate().toString();
 
                String contentType = msg.getContentType();
                String messageContent = "";
                
                if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (Exception ex) {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }
                
                String attachFiles = "";
                
                if (contentType.contains("multipart")) {
                    // content may contain attachments
                    Multipart multiPart = (Multipart) msg.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // this part is attachment
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
                            part.saveFile(saveDirectory + File.separator+ fileName);
                        } else {
                            // this part may be the message content
                            messageContent = part.getContent().toString();
                        }
                    }
 
                    if (attachFiles.length() > 1) {
                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                    }
                } else if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    Object content = msg.getContent();
                    if (content != null) {
                        messageContent = content.toString();
                    }
                }
 
                getemailsss = new Email();
                // print out details of each message
              
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                getemailsss.setFrom(from);
                System.out.println("\t To: " + toList);
                getemailsss.setTo(toList);
                System.out.println("\t CC: " + ccList);
                getemailsss.setCc(ccList);
                System.out.println("\t Subject: " + subject);
                getemailsss.setSubject(subject);
                System.out.println("\t Sent Date: " + sentDate);
                getemailsss.setDate(sentDate);
                System.out.println("\t Message: " + messageContent);
                getemailsss.setBody(messageContent);
                System.out.println("\t Attachments: " + attachFiles);
                getemailsss.setAttachment(attachFiles);
                repo.save(getemailsss);
            }
 
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
	
	private String parseAddresses(Address[] address) {
        String listAddress = "";
 
        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }
 
        return listAddress;
    }
 
	
	
	
	
	

	@Override
	public void saveBackup(Email email) {

	}

	@Override
	public void saveBackup(String protocol ,String host, String port, String userName, String password) {
//		Properties properties = new Properties();
//		// server setting
//		properties.put("mail.pop3.host", host);
//		properties.put("mail.pop3.port", port);
		Email savemail = null;
//		// SSL setting
//		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
//		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));
//		Session session = Session.getDefaultInstance(properties);
		 Properties properties = getServerProperties(protocol, host, port);
	        Session session = Session.getDefaultInstance(properties);
		List<Email> listsaveem = new ArrayList<>();
		try 
		{
			// connects to the message store
			Store store = session.getStore(protocol);
			store.connect(userName, password);

			// opens the INBOX folder
			Folder folderInbox = store.getFolder("[Gmail]/Drafts");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();
			savemail = new Email();
			for (int i = 0; i < arrayMessages.length; i++) {
				 
				Message message = arrayMessages[i];

				// Getting Emailed_From
				Address[] fromAdd = message.getFrom();
				String from = fromAdd[0].toString();
				if (from.contains("<")) {
					String value = from.substring(from.indexOf("<") + 1, from.indexOf(">", from.indexOf(">")));
					 savemail.setFrom(value);
					 try {
						 String result = value.substring(value.indexOf("@") + 1, value.indexOf("."));
						 if(result.equalsIgnoreCase("gmail")) {
							 savemail.setCompany(value);
						 }
						 else {
							 savemail.setCompany(value);
						 }
						 
				       } catch(StringIndexOutOfBoundsException e3) {
				       }
				} else {
					savemail.setFrom(from);
					 try {
						 String result = from.substring(from.indexOf("@") + 1, from.indexOf("."));
						 if(result.contains("gmail")) {
							 savemail.setCompany(from);
						 }
						 else {
							 savemail.setCompany(result);
						 }
				       } catch(StringIndexOutOfBoundsException e4) {
				       }
				}
				// Getting Emailed_To
				Address[] to = message.getRecipients(Message.RecipientType.TO);
				//Address[] fromAddress = message.getFrom();
			//	String from1 = fromAddress[0].toString();
               // String subject = message.getSubject();
               
//				String to1 = to[0].toString();
//				if (to1.contains("<")) {
//					String value = to1.substring(to1.indexOf("<") + 1, to1.indexOf(">", to1.indexOf(">")));
//					// System.out.println("To=======>==== " + value);
//					//e.setTo(value);
//					 savemail.setTo(value);
//				} else {
//					// System.out.println("To=======> " + to1);
//					//e.setTo(to1);
//					savemail.setTo(to1);
//				}
				// Getting Emailed_CC
				Address[] cc = message.getRecipients(Message.RecipientType.CC);
				String finalCC = "";
				try {
					for (int j = 0; j < cc.length; j++) {
						String fromCC = cc[j].toString();
						if (fromCC.contains("<")) {
							String value = fromCC.substring(fromCC.indexOf("<") + 1,
									fromCC.indexOf(">", fromCC.indexOf(">")));
							value = value.concat(", ");
							finalCC = finalCC.concat(value);
						} else {
							fromCC = fromCC.concat(",");
							finalCC = finalCC.concat(fromCC);
						}
					}
					finalCC = finalCC.substring(0, finalCC.length() - 1);
				} catch (NullPointerException e1) {
				}
				String subject = message.getSubject();
				
//				 String toList = parseAddresses(message
//	                        .getRecipients(RecipientType.TO));
//	                savemail.setTo(toList);
//	                System.out.println("\t toList"+" "+toList);
//	             String ccList = parseAddresses(message
//	                        .getRecipients(RecipientType.CC)); 
//	             savemail.setCc(ccList);
//	             System.out.println("\t ccList"+" "+ccList);
				
				if(subject!=null) {
				if(subject. contains("Java")) {
					//e.setSubjectcategory("Java Developer");
					savemail.setSubjectcategory("Java Developer");
				}
				else if(subject.contains("Password")) {
					//e.setSubjectcategory("Password");
					savemail.setSubjectcategory("Password");
				}
				else if(subject.contains("Hello")) {
				//	e.setSubjectcategory("rHello");
					savemail.setSubjectcategory("Hello");
				}
				else {
					//e.setSubjectcategory("Others123");
					savemail.setSubjectcategory("Others123");
				}
				}
				else {
					//e.setSubjectcategory("Others==");
					savemail.setSubjectcategory("Others==");
				}
				
				
				
				//String sentDate = message.getSentDate().toString();
				String contentType = message.getContentType();
				String messageContent = "";
				// store attachment file name, separated by comma
				String attachFiles = "";
				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						
						if (part.isMimeType("text/plain")) {
							  //System.out.println("plain text");
							} else if (part.isMimeType("multipart/*")) {
								MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
								 messageContent = getTextFromMimeMultipart(mimeMultipart);
								 //System.out.println("message content after else if=====>"+messageContent);
							} else if (part.isMimeType("message/rfc822")) {
								//System.out.println("message/rfc822");
							} else {
							//	System.out.println("text/html content");
							}
						
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(saveDirectory + File.separator + fileName);
						} 
						else if (part.isMimeType("multipart/*")) {
							MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
							 messageContent = getTextFromMimeMultipart(mimeMultipart);
							// System.out.println("message content after else if=====>"+messageContent);
						}
						else 
						{
							// this part may be the message content
							messageContent = part.getContent().toString();
							// System.out.println("message content before else if====>"+messageContent);
						}
					}
					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} 

				//e.setSubject(subject);
				System.out.println(subject);
				savemail.setSubject(subject);
				//savemail.setCc(finalCC);
				//e.setCc(finalCC);
				// e.setBcc(ebcc);
				// e.setBody2(messageContent);
				//e.setAttachment(attachFiles);
				savemail.setAttachment(attachFiles);
				System.out.println("body :"+messageContent);
				savemail.setBody(messageContent);
				listsaveem.add(savemail);
				//e.setBody(messageContent);
				//repo.save(e);
				
		
				//arrayMessages[i].setFlag(Flags.Flag.DELETED, true);
			}
			
			saveMail(listsaveem);
			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			// System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			// System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	
	public void saveMail(List<Email> email) throws MessagingException {
		System.out.println("kiran");
		//System.out.println(email);
		email.forEach(g->{
			System.out.println(g.getSubject());
		});
		List<Email> newemail = new ArrayList();
		//check already exists or not
		
		for (ListIterator<Email> it = email.listIterator(); it.hasNext();){
			Email value = it.next();
			System.out.println(value.getSubjectcategory()+" ======INNNNNNNNNN================");
			if(value.getSubjectcategory().equals("Others123")) {
//				// send these records to other mails				
				//it.remove();
			}
			List<Email> findBySubject = repo.findBySubject(value.getSubject());
			if(findBySubject == null || findBySubject.isEmpty()) {
			}
			else {
				 //it.remove();
			}
//		    if (value.equals("4")) {
//		        it.remove();
//		       // it.add("6");
//		    }
//
//		    System.out.println("List Value: " + value);
		}
		
		
//		email.forEach(e->{
//			 List<Email> findBySubject = repo.findBySubject(e.getSubject());
//			 if(findBySubject == null || findBySubject.isEmpty()) {
//				newemail.addAll(email);
//				// newemail.add((Email) findBySubject);
//				 
//			 }
//			 else {
//				 findBySubject.forEach(em->{
//					 email.removeAll(email);
//					 email.forEach(g1->{
//							System.out.println(g1.getSubject()+" -----------------------------");
//						});
//				 });
//				// email.removeAll(findBySubject);
//				 System.out.println("0000000000000000000000000000000000");
//			 }
//		});
		
		System.out.println("=====================================================================================");
		email.forEach(g1->{
			System.out.println(g1.getSubjectcategory()+" ====================");
			repo.save(g1);
		});
		//System.out.println(email);
		//repo.saveAll(email);
	}
	private String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	
	@Override
	public List<Email> getAllEmails() {

		return repo.findAll();
	}

	@Override
	public Email getEmail(long id) {

		return repo.findById(id).get();
	}

	@Override
	public List<Email> findEmailByFilter(String keyword) {
		if (keyword != null) {
			return repo.getAllEmailBasedOnFilter(keyword);
		}
		return repo.findAll();
	}

	@Override
	public String searchEmail(String host, String port, String userName, String password, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMessages(String host, String port, String userName, String password, String subjectToDelete) {
		// TODO Auto-generated method stub
		
	}

	

}

