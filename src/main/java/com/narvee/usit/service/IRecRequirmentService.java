package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.RecRequirements;

public interface IRecRequirmentService {

	public RecRequirements saveRequirments(RecRequirements requirements);

	public RecRequirements getRequrimentByID(long reqID);

	public List<RecRequirements> getAllRequirments();

	public boolean updateRequirments(RecRequirements requirements);

	public boolean deleteRequirmentsByID(long reqID);

	public Page<RecRequirements> findPaginated(int pageNo, int pageSize);
	
	public List<Object[]> getRequirmentByFiletr(String keyword);
}