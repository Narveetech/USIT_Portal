package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.PortalVMS;

@Repository
public interface IPortalVMSRepository extends JpaRepository<PortalVMS, Serializable>{
	
//	@Query("SELECT new com.narvee.usit.entity.PortalVMS(pv.id, pv.companyname, pv.techid, pv.recruitername, pv.contactnumber, pv.email) FROM PortalVMS pv ")
//	public List<PortalVMS> getAllPortalVMS();
//	
//	@Query(value = "SELECT new com.narvee.usit.entity.PortalVMS(pv.id, pv.companyname, pv.techid, pv.recruitername, pv.contactnumber, pv.email) FROM PortalVMS pv ")
//	public Page<PortalVMS> getAllPortalVMSBypage(Pageable pageable);
	
	@Query(value = "SELECT pv.id, pv.companyname, pv.contactnumber,pv.technology, t.technology_area, pv.email, pv.recruitername FROM portalvms pv, tbl_technologies_tags t WHERE pv.technology = t.id", nativeQuery = true)
	public List<Object[]> getAllPortalVMS();
	
	@Query(value = "SELECT pv.id, pv.companyname, pv.contactnumber,pv.technology, t.technology_area, pv.email, pv.recruitername FROM portalvms pv, tbl_technologies_tags t WHERE pv.technology = t.id", countQuery = "SELECT count(*) FROM portalvms pv, tbl_technologies_tags t WHERE pv.technology = t.id", nativeQuery = true)	
	public Page<List<Object[]>> getAllPortalVMSBypage(Pageable pageable);
	
	
}
//countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",