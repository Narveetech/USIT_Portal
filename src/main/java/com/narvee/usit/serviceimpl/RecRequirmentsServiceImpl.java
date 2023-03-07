package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.RecRequirements;
import com.narvee.usit.repository.IRecRequirmentRepository;
import com.narvee.usit.service.IRecRequirmentService;

@Service
public class RecRequirmentsServiceImpl implements IRecRequirmentService{
	
	@Autowired
	private IRecRequirmentRepository repository;

	@Override
	public RecRequirements saveRequirments(RecRequirements requirements) {
		
		return repository.save(requirements);
		 
	}

	@Override
	public RecRequirements getRequrimentByID(long reqID) {
		RecRequirements requirements = repository.findById(reqID).get();
		if(requirements != null) {
			return requirements;
		}
		return null;
	}

	@Override
	public boolean updateRequirments(RecRequirements requirements) {
		RecRequirements req = repository.save(requirements);
		if(req != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<RecRequirements> getAllRequirments() {
		return repository.getAllRequirments();
	}

	@Override
	public boolean deleteRequirmentsByID(long reqID) {
		RecRequirements requirements = repository.findById(reqID).get();
		if(requirements != null) {
			 repository.deleteById(reqID);
			 return true;
		}
		return false;
	}

	@Override
	public Page<RecRequirements> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<RecRequirements> findAll = repository.getAllRequrimentsByPageNo(pageable);
		return findAll;
	}

	@Override
	public List<Object[]> getRequirmentByFiletr(String keyword) {
		
			return repository.getAllRequirmentsByFilter(keyword);
		
	}

}