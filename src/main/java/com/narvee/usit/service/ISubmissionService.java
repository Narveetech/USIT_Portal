package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.helper.SalesSubmissionHelper;
public interface ISubmissionService {
	public Boolean saveSubmission(SalesSubmission con);
//	public List<SalesSubmissionHelper> findAllSubmission();
//	public Page<List<SalesSubmissionHelper>> findPaginated(int pageNo,int pageSize);	 
//	public List<SalesSubmission> dupsubmission(String id, String loc, String client);
	public SalesSubmission getSubmissionByID(long sid);
	public boolean deleteSalesSubmission(long sid);
	
//	public List<Object[]> getConDetailsBySubmission(); 
}
