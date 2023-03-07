package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.RecConsultantNew;
import com.narvee.usit.helper.IRecConsDTO;

public interface IRecConsService {
	
	public boolean saveRecCons(RecConsultantNew new1);
	
	public RecConsultantNew getByID(long id);
	
	public List<IRecConsDTO> getAll();
}
