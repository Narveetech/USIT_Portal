package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.RecConsultantNew;
import com.narvee.usit.helper.IRecConsDTO;
import com.narvee.usit.repository.IRecConsultantRepo;
import com.narvee.usit.service.IRecConsService;

@Service
public class RecConsServiceImpl implements IRecConsService{
	
	@Autowired
	private IRecConsultantRepo repo;
	@Override
	public boolean saveRecCons(RecConsultantNew new1) {
		RecConsultantNew consultantNew = repo.save(new1);
		if(consultantNew != null) {
			return true;
		}
		return false;
	}
	@Override
	public RecConsultantNew getByID(long id) {
		return repo.findById(id).get();
	}
	@Override
	public List<IRecConsDTO> getAll() {
		return repo.getAllRecCOns();
	}

}
