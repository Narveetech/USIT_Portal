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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requirment")
public class RecRequirements {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "Recruting")
	@SequenceGenerator(name="Requirments",sequenceName = "requirmentSeq")
	@Column(name="recruiterId")
	private Long recruiterId;
	
	@Column(name = "postedon")
	private String postedOn;
	
	@Column(name = "vendor")
	private String vendor;
	
	//@NotNull(message = "Job title should be not null")
	private String jobTitle;
	
	private String category;
	
	//@Email(message = "invalid email address")
	private String email;
	
	//@Size(min = 10, max = 10)
	private String phoneNo;
	
	private String jobDescription;
	
	//@NotNull(message = "recruiter name sholud be not null")
	private String recruitername;
	
	//@NotNull(message = "recruiter name sholud be not null")
	private String location;
	
	//@PositiveOrZero
	private String jobexperience;
	
	private String jobskills;
	
	private String employmenttype;
	
	//@NotNull(message = "salary should be decimal Number")
	//@DecimalMin("0.0")
	private double salary;
	
	private String source;
	
	@Column(name="updateddate") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
	
	@Column(name="status") 
	private String status = "Active";
	
	
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby=1;
	
	@Column(name="updatedby") 
	private String updatedby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
	
	public RecRequirements(long recruiterId,String postedOn, String jobTitle, String location, String vendor, String employmenttype) {
		this.recruiterId = recruiterId;
		this.postedOn = postedOn;
		this.jobTitle = jobTitle;
		this.location = location;
		this.vendor = vendor;
		this.employmenttype = employmenttype;
	}
	
}
