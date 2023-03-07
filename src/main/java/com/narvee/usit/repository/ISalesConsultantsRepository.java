package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.helper.SalesConsHelper;
public interface ISalesConsultantsRepository extends JpaRepository<SalesConsultants, Serializable> {

//	@Query("SELECT s FROM SalesConsultants s WHERE s.id=:id ")
//	public SalesConsultants getConsultantById(@Param("id") long id);
//
//	@Modifying
//	@Query("UPDATE SalesConsultants c SET c.status = :status,c.remarks = :rem  WHERE c.id = :id")
//	public int toggleStatus(@Param("status") String status, @Param("id") long id, @Param("rem") String rem);
//
//	@Query("select s.sid,s.fullName,s.technology,s.totalExperience  from SalesConsultants s where s.status='Active'")
//	List<Object[]> findconsultantexp();
//	
//	@Query(value = "SELECT new com.narvee.usit.helper.SalesConsHelper(sc.sid, sc.fullName,sc.email,sc.totalExperience,t.technology_area,v.status,sc.currentLocation,sc.hourlyRate,sc.priority,sc.relocate,sc.status) FROM SalesConsultants sc, Visa v,Technologies t WHERE sc.visaStatus =v.vid AND sc.technology= t.id")
//	public List<SalesConsHelper> getAllConsDeatals();
//	
//	@Query(value = "SELECT new com.narvee.usit.helper.SalesConsHelper(sc.sid, sc.fullName,sc.email,sc.totalExperience,t.technology_area,v.status,sc.currentLocation,sc.hourlyRate,sc.priority,sc.relocate,sc.status) FROM SalesConsultants sc, Visa v,Technologies t WHERE sc.visaStatus =v.vid AND sc.technology= t.id")
//	public Page<List<SalesConsHelper>> getAllSalesConsByPageNo(Pageable pageable);
	
//	private long id;
//	private String consultantname;
//	private String email;
//	private String experience;
//	private String technology;
//	private String visa;
//	private String currentlocation;
//	private String rate;
//	private String priority;
//	private String status;

}