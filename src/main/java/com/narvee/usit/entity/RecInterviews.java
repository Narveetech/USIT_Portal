package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="recruiting_interiews")
@Entity
public class RecInterviews {	 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;   
        
    @Column(name="submission")
    private Long submissionid;
    
    @Transient
    private Long cid;
    @Transient
    private String consultantname;
    
    private Long requirmentid;
    @Transient
    private String requirment;
    
    @Transient
    private LocalDate dateofsubmission;
    
    @Transient
    private String username;
    
    @Transient
    private long userid;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "interviewdate")
    private String interviewdate;

    @Column(name="timezone")
    private String timezone;

    @Column(name="Round")
    private String round;
    
    @Column(name="Mode")
    private String mode;
    
    @Column(name="FeedBack")
    private String feedBack;
    
    @Column(name="InterviewStatus")
    private String interviewstatus; 
    
    @Column(name= "addedby", nullable = false, updatable = false)
	private long addedby;
    
    @Column(name="updatedby") 
	private String updatedby;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
    
    @Column(name="updateddate") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
     
    @Column(name="status") 
	private String status = "Active";
	
	public RecInterviews(Long id, Long submissionid, Long cid, long userid,String consultantname, String interviewdate, String timezone, String round, String mode, String requirment, LocalDate dateofsubmission, String username, String interviewstatus, String status) {
		this.id = id;
		this.submissionid = submissionid;
		this.cid = cid;
		this.userid = userid;
		this.consultantname = consultantname;
		this.interviewdate = interviewdate;
		this.timezone = timezone;
		this.round = round;
		this.mode = mode;
		this.requirment = requirment;
		this.dateofsubmission = dateofsubmission;
		this.username = username;
		this.interviewstatus = interviewstatus;
		this.status = status;
	}
	
}
