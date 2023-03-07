package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.narvee.usit.entity.RecConsultantNew;
import com.narvee.usit.helper.IRecConsDTO;

public interface IRecConsultantRepo extends JpaRepository<RecConsultantNew, Serializable>{
	
	@Query(value = "SELECT rcn.id, rcn.name,rcn.email, rcn.experience,rcn.technology,rcn.addedby,rcn.status, v.vid FROM rec_consult rcn, tb_visa v  WHERE rcn.visa_vid = v.vid", nativeQuery = true)
	public List<IRecConsDTO> getAllRecCOns();
}
