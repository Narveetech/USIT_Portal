package com.narvee.usit.helper;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.narvee.usit.controller.EmailMessageRemover;
import com.narvee.usit.serviceimpl.EmailBackupServiceimpl;

public class Job1 implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		EmailMessageRemover remover = new EmailMessageRemover();
		//remover.deleteMessages( "imap.gmail.com", "993", "polakayerriswamy1234@gmail.com", "sredinxhfykbncis");
	}

}
