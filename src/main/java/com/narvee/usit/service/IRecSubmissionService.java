package com.narvee.usit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.ReqSubmission;

public interface IRecSubmissionService {
	
	public ReqSubmission saveSubmission(ReqSubmission submission);
	
	public List<ReqSubmission> getAllsubmission();
	
	public Optional<ReqSubmission> getSubmissionByID(long id);
	
	public boolean deleteSubmissionByID(long id);
	
	public Page<List<ReqSubmission>> findPaginated(int pageNo, int pageSize);
	
	public List<Object[]> getAllRecRequirments();
	
	public List<Object[]> getAllRecConsultants();
	
	public List<Object[]> getAllUsers();
	
//	public List<ReqSubmission> getSubmissionByFiletr(String keyword);
}
