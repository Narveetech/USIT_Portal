package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.RecInterviews;

@Repository
public interface IRecInterviewRepository extends JpaRepository<RecInterviews, Serializable>{
	
	
/*	@Query(value = "SELECT new com.narvee.usit.helper.RecInterviewHelper(ri.id,rc.name, ri.interviewdate,ri.timezone,ri.round, ri.mode,rc.technology,rs.createddate,rs.addedby,ri.interviewstatus,ri.status) FROM RecInterviews ri,ReqSubmission rs,RecConsultant rc WHERE ri.submissionid= rs.submissionId AND rs.consultantId = rc.id ")
	public List<RecInterviewHelper> getAllRecInterviewDetails();

	@Query(value = "SELECT new com.narvee.usit.helper.RecInterviewHelper(ri.id,rc.name, ri.interviewdate,ri.timezone,ri.round, ri.mode,rc.technology,rs.createddate,rs.addedby,ri.interviewstatus,ri.status) FROM RecInterviews ri,ReqSubmission rs,RecConsultant rc WHERE ri.submissionid= rs.submissionId AND rs.consultantId = rc.id")
	public Page<List<RecInterviewHelper>> getAllRecruitingInterviewByPageNo(Pageable pageable);
	
	@Query( "SELECT rs.submissionId, rc.name FROM ReqSubmission rs, RecConsultant rc WHERE rs.consultantId= rc.id ")
	public List<Object[]> recSubmissiondetails(); 	*/
	
	@Query("SELECT rc.id,rc.name,rc.technology,rc.experience FROM RecConsultant rc, ReqSubmission rs WHERE rs.consultantId= rc.id")
	public List<Object[]> getConsultantdetails();
	
	@Query("SELECT u.userid, u. fullname FROM Users u")
	public List<Object[]> getUsersDetails();
	
	@Query("SELECT rr.recruiterId, rr.jobTitle, rr.location FROM RecRequirements rr")
	public List<Object[]> getRequirmentDetails();
	
//	@Query(value = "SELECT new com.narvee.usit.entity.RecInterviews(ri.id, ri.submissionid, rc.id,u.userid, rc.name, ri.interviewdate,ri.timezone,ri.round, ri.mode,rr.jobTitle,rs.createddate ,u.fullname,ri.interviewstatus,ri.status) FROM RecInterviews ri,ReqSubmission rs,RecConsultant rc,RecRequirements rr, Users u WHERE ri.submissionid= rs.submissionId AND rs.addedby = u.userid AND rs.consultantId = rc.id AND ri.requirmentid = rr.id")
//	public List<RecInterviews> getAllRecInterviewDetails();
	
	@Query(value = "SELECT new com.narvee.usit.entity.RecInterviews(ri.id, ri.submissionid, rc.id,u.userid, rc.name, ri.interviewdate,ri.timezone,ri.round, ri.mode,rr.jobTitle,rs.createddate ,u.fullname,ri.interviewstatus,ri.status) FROM RecInterviews ri,ReqSubmission rs,RecConsultant rc,RecRequirements rr, Users u WHERE ri.submissionid= rs.submissionId AND rs.addedby = u.userid AND rs.consultantId = rc.id AND ri.requirmentid = rr.id")
	public Page<List<RecInterviews>> getAllRecruitingInterviewByPageNo(Pageable pageable);
	
	@Query(value = "SELECT ri.id, rs.sub_id,rs.consult_id,u.userid, rc.name,ri.interviewdate,ri.timezone,ri.round,ri.mode,rr.job_title,rs.createddate,u.fullname, ri.interview_status, ri.status FROM recruiting_interiews ri, reqsubmission rs, recruiting_consultant rc,requirment rr,users u WHERE ri.submission = rs.sub_id AND rs.addedby = u.userid AND rs.consult_id = rc.id AND ri.requirmentid = recruiter_id", nativeQuery =  true)
	public List<Object[]> getAllRecInterviewDetails();
}
	
