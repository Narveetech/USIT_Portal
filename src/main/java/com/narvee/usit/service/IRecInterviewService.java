package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.entity.ReqSubmission;

public interface IRecInterviewService {
	
	public boolean saveInterviews(RecInterviews interviews);
	
	public List<Object[]> getAllRecInterviewDetails();
	
	public RecInterviews getRecInterviewById(long id);
	
	public boolean deleteRecInterviewByID(long id);
	
	public Page<List<RecInterviews>> findPaginated(int pageNo, int pageSize);
	
//	public List<Object[]> getRecSubDetails();
	
	public List<Object[]> getRecConsultantDetails();
	
	public List<Object[]> getUsersDetails();
	
	public List<Object[]> getRecRequirmentsDetails();
	
	public RecConsultant getConsultantById(long id);
	
	public ReqSubmission getSubmissionByID(long id);
}
