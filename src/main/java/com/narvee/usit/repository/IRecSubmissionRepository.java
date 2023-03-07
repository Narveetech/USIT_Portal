package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.narvee.usit.entity.ReqSubmission;

public interface IRecSubmissionRepository extends JpaRepository<ReqSubmission, Serializable>{
	
//	@Query(value = "SELECT new com.narvee.usit.helper.RecSubmissionHelper(rc.id,s.submissionId,s.createddate,rc.name,s.rate,"
//			+ "s.recruitername,s.status) FROM RecConsultant rc, "
//			+ " Reqsubmission s WHERE rc.id =s.consultantId")
//	public List<RecSubmissionHelper> getsubmissions();
//	
	@Query(value = "SELECT new com.narvee.usit.entity.ReqSubmission(rs.submissionId,rs.consultantId,rs.requirementId, rs.createddate, rc.name, rs.rate,u.fullname,rs.substatus,rs.status, u.userid) FROM ReqSubmission rs,RecConsultant rc, Users u WHERE rs.consultantId= rc.id AND rs.addedby = u.userid")
	public Page<List<ReqSubmission>> AllsubmissionsByPageNo(Pageable pageable);
	
	@Query(value = "SELECT new com.narvee.usit.entity.ReqSubmission(rs.submissionId,rs.consultantId,rs.requirementId, rs.createddate, rc.name, rs.rate,u.fullname,rs.substatus,rs.status, u.userid) FROM ReqSubmission rs,RecConsultant rc, Users u WHERE rs.consultantId= rc.id AND rs.addedby = u.userid")
	public List<ReqSubmission> getAllSubmission();
	
//	@Query(value = "SELECT new com.narvee.usit.entity.ReqSubmission(rs.submissionId,rs.consultantId,rs.requirementId, rs.createddate, rc.name, rs.rate,u.fullname,rs.substatus,rs.status, u.userid) FROM ReqSubmission rs,RecConsultant rc, Users u WHERE rs.consultantId= rc.id AND rs.addedby = u.userid AND  rs.createddate LIKE CONCAT('%',:keyword, '%') OR rc.name LIKE CONCAT('%',:keyword, '%') OR rs.rate LIKE CONCAT('%',:keyword, '%') OR u.fullname LIKE CONCAT('%',:keyword,'%') OR rs.substatus LIKE CONCAT('%',:keyword, '%') OR rs.status LIKE CONCAT('%',:keyword,'%')")
//	public List<ReqSubmission> getAllSubmissionByFilter(String keyword);
	
	@Query(value = "SELECT r.recruiter_id, r.job_title, r.location FROM requirment r", nativeQuery = true)
	public List<Object[]> getAllRequirments();
	
	
	@Query(value = "SELECT rc.id, rc.experience, rc.name, rc.technology FROM recruiting_consultant rc ", nativeQuery = true)
	public List<Object[]> getAllRecConsultants();
	
	@Query(value = "SELECT u.userid, u.fullname FROM users u", nativeQuery = true)
	public List<Object[]> getAllUsers();
	
}
