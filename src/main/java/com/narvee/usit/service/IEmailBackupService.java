package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.Email;

public interface IEmailBackupService {

	public void saveBackup(Email email);
	
	//public void saveBackup(String host, String port, String username, String password);

	public void saveBackup(String protocol,String host, String port, String username, String password);

	public List<Email> getAllEmails();

	public Email getEmail(long id);
	
	public List<Email> findEmailByFilter(String keyword);
	
	public String searchEmail(String host, String port, String userName, String password, final String keyword);

	public void deleteMessages(String host, String port, String userName, String password, String subjectToDelete);
	
	public void downloadEmails(String protocol, String host, String port, String userName, String password);

}
