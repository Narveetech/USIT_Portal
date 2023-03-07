package com.narvee.usit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.service.IEmailBackupService;

@RestController
@RequestMapping("/usit/mail")
public class EmailBackupController {
	
	public static final Logger logger = LoggerFactory.getLogger(EmailBackupController.class);
	@Autowired
	private IEmailBackupService service;
	
	@Autowired
	private EmailMessageRemover remover;
	
	@GetMapping("deleteEmail")
	public void deleteMessage() {
		String host = "imap.gmail.com";
	       String port = "993";
	       //String userName = "your_email";
	       //String password = "your_password";
	       String userName = "polakayerriswamy1234@gmail.com";// change accordingly
	       String password = "sredinxhfykbncis";// change accordingly     
	System.out.println("kkkk");
	       // try to delete all messages contain this string its Subject field
	       //String subjectToDelete = "Recruiters can't find you";
	       remover.deleteMessages();
	}
	
	
	@GetMapping("getAllEmail")
	public void readEmailServer() {
		 String protocol = "imap";
	     String host = "imap.gmail.com";
	     String port = "993";
	     String userName = "polakayerriswamy1234@gmail.com";
	     String password = "sredinxhfykbncis";
	     logger.debug("readEmailServer");
	     logger.trace("readEmailServer");
	     logger.error("inside class: !!EmailBackupController: !!!method :!readEmailServer");
	     service.downloadEmails(protocol, host, port, userName, password);
	}
	
	@GetMapping(value = "/mail3", produces = MediaType.APPLICATION_JSON_VALUE)
	public void save() {
		 String protocol = "imap";
	     String host = "imap.gmail.com";
	     String port = "993";
	     String userName = "polakayerriswamy1234@gmail.com";
	     String password = "sredinxhfykbncis";
//		System.out.println("hello kiran");
//		String host = "smtp.narveetech.com";
//        String port = "995";
//        final String username= "saikiran@narveetech.com";  
//		final String password= "Narvee123$";//change accordingly  
		//service.saveBackup(host, port, username, password);
		service.saveBackup(protocol,host, port, userName, password);
	}
	
	@RequestMapping(value = "/mail", method = RequestMethod.GET, produces = "multipart/mixed")
	public ResponseEntity<RestAPIResponse> savemsd() {
		
		System.out.println("hello kiran");
		String host = "smtp.narveetech.com";
        String port = "587";
        final String username= "saikiran@narveetech.com";  
		final String password= "Narvee123$";//change accordingly  
		//service.saveBackup(host, port, username, password);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched All Emails", "jjjj"),HttpStatus.OK);
	}
	
	@GetMapping("/searchemail/{keyword}")
	public String searchEmail(@PathVariable String keyword) {
		System.out.println("hi hello welcome to gmail");
		String host = "smtp.narveetech.com";
        String port = "587";
        final String username= "saikiran@narveetech.com";  
		final String password= "Narvee123$";
		
       return service.searchEmail(host, port, username, password,keyword);
	}
	
	@DeleteMapping("delete")
	public void deleteEmails() {
		System.out.println("deleted emails done");
		String host = "smtp.gmail.com";
        String port = "587";
        final String username= "polakayerriswamy1234@gmail.com";  
		final String password= "haucomcocfqmsckn";
		String subjectToDelete = "deleted";
		service.deleteMessages(host, port, username, password, subjectToDelete);
		System.out.println("etdtfy");
	}
	
//	@DeleteMapping("subjectToDelete")
//	public void deleteEmails() {
//		System.out.println("deleted emails done");
//		String host = "smtp.gmail.com";
//        String port = "995";
//        final String username= "polakayerriswamy1234@gmail.com";  
//		final String password= "haucomcocfqmsckn";
//		String subjectToDelete = "deleted";
//		service.deleteMessages(host, port, username, password, subjectToDelete);
//	}
	
	@RequestMapping(value = "/getMails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllMails() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched All Emails", service.getAllEmails()),HttpStatus.OK);
	}
	

	@RequestMapping(value = "/getMails/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getMail(@PathVariable long id)  {
		
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fethed Emails By ID",service.getEmail(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getMailsK/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getMailByKeyord(@PathVariable String keyword) {
		
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully Fetched By Keyword", service.findEmailByFilter(keyword)),HttpStatus.OK);
	}
	
	
}
