package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.helper.SalesSubmissionHelper;
public interface ISalesSubmissionrepo  extends JpaRepository<SalesSubmission, Serializable> {
 /*@Query("SELECT new com.narvee.Technology.entity."
				+ "SalesSubmission(c.sid,u.id,s.created_date,s.subid,c.fullName, s.positionTitle,s.projectLocation,s.endClient,s.vendor,s.subrate,u.firstName) from SalesSubmission s, User u,SalesConsultants c where u.id=s.addedBy and s.consultantid=c.sid order by s.created_date DESC, c.fullName asc")
				Page<SalesSubmission> findAlld(Pageable pageable);
 
 @Query("SELECT new com.narvee.Technology.entity."
			+ "SalesSubmission(c.sid,u.id,s.created_date,s.subid,c.fullName, s.positionTitle,s.projectLocation,s.endClient,s.vendor,s.subrate,u.firstName) from SalesSubmission s, User u,SalesConsultants c where u.id=s.addedBy and "
			+ "s.consultantid=c.sid AND CONCAT(c.fullName, s.positionTitle,s.projectLocation,s.endClient,"
			+ "s.vendor,s.subrate,u.firstName) Like %?1% order by s.created_date DESC,c.fullName asc "
		)
			Page<SalesSubmission> findAlldbykey(Pageable pageable,String keyword);
 
 @Query("SELECT new com.narvee.Technology.entity."
			+ "SalesSubmission(c.sid,u.id,s.created_date,s.subid,c.fullName, s.positionTitle,s.projectLocation,s.endClient,s.vendor,s.subrate,u.firstName) from SalesSubmission s, User u,SalesConsultants c where u.id=s.addedBy and "
			+ "s.consultantid=c.sid and s.created_date =:keyword order by s.created_date DESC,c.fullName asc"
		)
			Page<SalesSubmission> findAlldbydate(Pageable pageable,LocalDate keyword);
 
 public List<SalesSubmission>  findByConsultantidAndProjectLocationAndEndClient(String id,String loc,String client);	*/
	
//	@Query("SELECT new com.narvee.usit.helper.SalesSubmissionHelper(ss.subid, ss.created_date,sc.fullName,ss.positionTitle,ss.endClient,ss.vendor,ss.subrate,ss.addedBy,ss.status ) FROM SalesSubmission ss,SalesConsultants sc WHERE ss.consultantid = sc.sid")
//	public List<SalesSubmissionHelper> getAllSubmission();
//	
//	@Query("SELECT new com.narvee.usit.helper.SalesSubmissionHelper(ss.subid, ss.created_date,sc.fullName,ss.positionTitle,ss.endClient,ss.vendor,ss.subrate,ss.addedBy,ss.status ) FROM SalesSubmission ss,SalesConsultants sc WHERE ss.consultantid = sc.sid")
//	public Page<List<SalesSubmissionHelper>> getSalesSubmissionByPageNo(Pageable pageable);
//	
//	@Query("SELECT sc.sid,sc.fullName, sc.totalExperience,t.technology_area FROM SalesConsultants sc, Technologies t WHERE sc.technology = t.id")
//	public List<Object[]> getconsultantDetailsBySub();
	
//	@Query(value = "select s.subid,c.full_name,s.vendor,s.position_title,s.project_location,s.end_client from sales_submission  s,\r\n"
//			+ "tbl_sales_consultant c where c.id=s.consultantid ", nativeQuery = true)
//	public List<Object[]> submissiondetails();

	
//	private Long   subid;
//	private LocalDate created_date;
//	private String consultantname;
//	private String positionTitle;
//	private String endClient;
//	private String vendor;
//	private String rate;
//	private String addedBy;
//	private String status;
}

