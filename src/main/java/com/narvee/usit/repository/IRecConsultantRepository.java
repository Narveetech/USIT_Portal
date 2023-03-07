package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.narvee.usit.entity.RecConsultant;

public interface IRecConsultantRepository extends JpaRepository<RecConsultant, Serializable> {

	@Query(value = "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE rc.visaid =v.vid AND rc.addedby = u.userid")
	public List<RecConsultant> getAllRequirments();

//	@Query(value = "SELECT new com.narvee.Technology.entity.RecruitingConsultant(rc.id,rc.name,rc.mobile, rc.email,rc.visaid,rc.experience,rc.rate,rc.location,rc.technology,rc.skills,rc.summary,rc.resume) FROM   RecruitingConsultant rc WHERE rc.name=:name AND rc.id=:id")
//	public RecruitingConsultant getDetailsConsultantByName(@Param("name") String name, long id);
	
	@Query(value = "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE rc.visaid =v.vid AND rc.addedby = u.userid ")
	public Page<List<RecConsultant>> getAllRequrimentsConsultantByPageNo(Pageable pageable);
	
	
//	@Query(value = "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE CONCAT(rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) LIKE %?1% ")
//	public List<RecConsultant> getAllRecConsultantFilter(String keyword);
//	
	@Query(value = "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE rc.createddate LIKE CONCAT('%',:keyword, '%') OR rc.name LIKE CONCAT('%',:keyword, '%') OR rc.experience  LIKE CONCAT('%',:keyword, '%') OR rc.technology LIKE CONCAT('%',:keyword, '%') OR rc.email LIKE CONCAT('%',:keyword, '%') OR v.status LIKE CONCAT('%',:keyword, '%') OR u.firstname LIKE CONCAT('%',:keyword, '%') OR rc.updatedby LIKE CONCAT('%',:keyword, '%') OR rc.status LIKE CONCAT('%',:keyword, '%') AND rc.visaid =v.vid AND rc.addedby = u.userid")
	public List<RecConsultant> getAllRecConsultantFilter(String keyword);
	//rc.visaid =v.vid AND rc.addedby = u.userid AND 
	
	@Transactional
	@Modifying
	@Query("UPDATE RecConsultant c SET c.status = :status WHERE c.id = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") long id);
}
