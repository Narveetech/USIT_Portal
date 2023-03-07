package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.helper.RecInterviewHelper;
import com.narvee.usit.repository.IRecConsultantRepository;
import com.narvee.usit.repository.IRecInterviewRepository;
import com.narvee.usit.repository.IRecSubmissionRepository;
import com.narvee.usit.service.IRecInterviewService;

@Service
public class RecInterviewImpl implements IRecInterviewService{
	
	@Autowired
	private IRecInterviewRepository repository;
	
	@Autowired
	private IRecConsultantRepository consultantRepository;
	
	@Autowired
	private IRecSubmissionRepository submissionRepository;

	@Override
	public boolean saveInterviews(RecInterviews interviews) {
		RecInterviews recruitingInterviews = repository.save(interviews);
		if(recruitingInterviews != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Object[]> getAllRecInterviewDetails() {
		return repository.getAllRecInterviewDetails();
	}

	@Override
	public RecInterviews getRecInterviewById(long id) {
		System.out.println("dsabdbghds");
		return repository.findById(id).get();
	}

	@Override
	public boolean deleteRecInterviewByID(long id) {
		RecInterviews interviews = repository.findById(id).get();
		if(interviews != null) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Page<List<RecInterviews>> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<RecInterviews>> findAll = repository.getAllRecruitingInterviewByPageNo(pageable);
		return findAll;
	}

//	@Override
//	public List<Object[]> getRecSubDetails() {
//		return repository.recSubmissiondetails();
//	}
	
	@Override
	public List<Object[]> getRecConsultantDetails() {
		return repository.getConsultantdetails();
	}
	
	@Override
	public List<Object[]> getUsersDetails() {
		return repository.getUsersDetails();
	}
	
	@Override
	public List<Object[]> getRecRequirmentsDetails() {
		return repository.getRequirmentDetails();
	}
	
	@Override
	public RecConsultant getConsultantById(long id) {
		return consultantRepository.findById(id).get();
	}

	@Override
	public ReqSubmission getSubmissionByID(long id) {
		return submissionRepository.findById(id).get();
	}


}

