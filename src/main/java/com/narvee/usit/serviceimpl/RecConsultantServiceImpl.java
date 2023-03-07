package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.repository.IRecConsultantRepository;
import com.narvee.usit.service.IRecruitingConsultingService;

@Service
public class RecConsultantServiceImpl implements IRecruitingConsultingService {

	@Autowired
	private IRecConsultantRepository repository;

	@Override
	public boolean saveRecruitingConsutant(RecConsultant consultant) {
		RecConsultant recConsutant = repository.save(consultant);
		if (recConsutant != null) {
			return true;
		}
		return false;
	}
	
	@Transactional
	@Override
	public List<RecConsultant> getAllRecruitingConsultant() {
		return repository.getAllRequirments();
	}

	@Override
	public RecConsultant getConsultantByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean deleteRecruitingConsultantByID(long id) {
		RecConsultant consultant = repository.findById(id).get();
		if (consultant != null) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

//	@Override
//	public RecruitingConsultant getDetailsByConsultantName(String name, long id) {
//		return repository.getDetailsConsultantByName(name,id);
//	}

	@Override
	public Page<List<RecConsultant>> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<RecConsultant>> findAll = repository.getAllRequrimentsConsultantByPageNo(pageable);
		return findAll;
	}

	@Override
	public long changeStatus(String status, long id) {
		return repository.toggleStatus(status, id);
	}

	@Override
	public List<RecConsultant> getAllRecConsultantByFilter(String keyword) {
		List<RecConsultant> consultants = repository.getAllRecConsultantFilter(keyword);
		return consultants;
	}

}

