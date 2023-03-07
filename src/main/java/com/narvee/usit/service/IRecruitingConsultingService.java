package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.RecConsultant;

public interface IRecruitingConsultingService {
	
	public boolean saveRecruitingConsutant(RecConsultant consultant);
	
	public List<RecConsultant> getAllRecruitingConsultant();
	
	public RecConsultant getConsultantByID(long id);
	
	public boolean deleteRecruitingConsultantByID(long id);
	
//	public RecruitingConsultant getDetailsByConsultantName(String name, long id);
	
	public Page<List<RecConsultant>> findPaginated(int pageNo, int pageSize);
	
	public long changeStatus(String status, long id);
	
	public List<RecConsultant> getAllRecConsultantByFilter(String keyword);

}