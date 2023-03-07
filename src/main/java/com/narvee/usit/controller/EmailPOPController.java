package com.narvee.usit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.serviceimpl.EmailPOPServerServiceImpl;

@RestController
public class EmailPOPController {
	
	@Autowired
	private EmailPOPServerServiceImpl serviceImpl;
	
	@GetMapping("popmailreader")
	public void popMaiReciever(){
	        // for POP3
	        String protocol = "pop3";
	        String host = "pop.narveetech.com";
	        String port = "995";
	 
	        // for IMAP
//	        String protocol = "imap";
//	        String host = "imap.gmail.com";
//	        String port = "993";
	 
	 
	        String userName = "swamy@narveetech.com";
	        String password = "Narvee123$";
	 
	     
	        serviceImpl.saveBackup(protocol, host, port, userName, password);
	    }
	
	@GetMapping("deletepopmails")
	public void deletemails() {
		String username = "swamy@narveetech.com";
		String password = "Narvee123$";
		serviceImpl.deleteMessages(username, password);
	}
}
