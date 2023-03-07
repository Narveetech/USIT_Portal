package com.narvee.usit.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.repository.IRecSubmissionRepository;
import com.narvee.usit.service.IRecSubmissionService;

@Service
public class RecSubmissionImpl implements IRecSubmissionService {

	@Autowired
	private IRecSubmissionRepository repo;
	
	

	@Override
	public ReqSubmission saveSubmission(ReqSubmission submission) {
		return repo.save(submission);
	}

	@Override
	public List<ReqSubmission> getAllsubmission() {
		return repo.getAllSubmission();
	}

	@Override
	public Optional<ReqSubmission> getSubmissionByID(long id) {
		return repo.findById(id);
	}

	@Override
	public boolean deleteSubmissionByID(long id) {
		ReqSubmission requirements = repo.findById(id).get();
		if(requirements != null) {
			repo.deleteById(id);
			 return true;
		}
		return false;
	}

	@Override
	public Page<List<ReqSubmission>> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<ReqSubmission>> findAll = repo.AllsubmissionsByPageNo(pageable);
		return findAll;
	}

	@Override
	public List<Object[]> getAllRecRequirments() {
		return repo.getAllRequirments();
	}

	@Override
	public List<Object[]> getAllRecConsultants() {
		return repo.getAllRecConsultants();
	}

	@Override
	public List<Object[]> getAllUsers() {
		return repo.getAllUsers();
	}

//	@Override
//	public List<ReqSubmission> getSubmissionByFiletr(String keyword) {
//		return repo.getAllSubmissionByFilter(keyword);
//	}

	
}
