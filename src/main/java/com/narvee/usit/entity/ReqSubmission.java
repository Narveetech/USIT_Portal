package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reqsubmission")
public class ReqSubmission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "submission")
	@SequenceGenerator(name="ReqSubmission",sequenceName = "reqsub")
	@Column(name="subId")
	private Long submissionId;
	
	@Column(name="reqId")
	private Long requirementId;
	
	@Column(name="consultId")
	private Long consultantId;
	
	@Transient
	private String consultantname;
	
	private String ratetype;
	
	private double rate;
	
//	@Column(name="relocation")
//	private String[] relocation = {"relocate","expensive","benefits"};
	
	private String relocate = "no";
	private String expenses = "without";
	private String benefits = "without";
	
	@Column(name="relocationAssistance")
	private String relocationAssistance;
	
//  private Long recruiterId;
	@Transient
	private String recruitername;
	
	private String substatus;
	
	@Column(name="status") 
	private String status = "Active";
	
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby;
	
	private long userid;
	
//	@Transient
//	private String username;
	
	@Column(name="updatedby") 
	private String updatedby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
	
	@Column(name="updateddate") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
	
	public ReqSubmission(Long submissionId, Long consultantId,Long  requirementId ,LocalDate createddate, String consultantname, double rate, String recruitername, String substatus,String status, long addedby) {
		this.submissionId = submissionId;
		this.consultantId = consultantId;
		this.requirementId = requirementId;
		this.createddate = createddate;
		this.consultantname = consultantname;
		this.rate = rate;
		this.recruitername = recruitername;
		this.substatus = substatus;
		this.status = status;
		this.addedby = addedby;
	}
	
	
//	public ReqSubmission(Long submissionId, Long consultantId, LocalDate createddate, String consultantname, double rate, String recruitername, String status,String username,long addedby) {
//		
//	}
	
	/*
	 private Long id;
	 private Long subId;
	 private LocalDate created_date;
	 private String name;
	 private double rate;
	 private String recruiternName;
	 private String status
	 */
	
}
