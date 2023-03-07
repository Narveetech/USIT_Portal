package com.narvee.usit.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Technologies;
import com.narvee.usit.repository.TechnologyRepository;
import com.narvee.usit.service.TechnologyService;

@Service
public class TechnologyServiceImpl implements TechnologyService{
	
	@Autowired
	private TechnologyRepository repository;
 	
	@Override
	public Technologies saveTechnology(Technologies technologies) {
		return repository.save(technologies);
	}

}
