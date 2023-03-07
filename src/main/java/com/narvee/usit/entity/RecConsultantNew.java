package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "RecConsult")
public class RecConsultantNew {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "RecruitingConsultantNew")
	@SequenceGenerator(name="RecruitingConsultantNew",sequenceName = "RecruitingConsultantSeq")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="experience")
	private String experience;
	
	@Column(name="rate_type")
	private String ratetype;
	
	@Column(name="rate")
	private String rate;
	
	@Column(name="location")
	private String location;
	
	@Column(name="technology")
	private String technology;
	
	@Column(name="skills")
	private String skills;
	
	@Column(name="summary")
	private String summary;
	
	@Column(name="resume")
	private String resume;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Visa visa;
	
	private String companyname;
	
	private String salespersonname;
	
	private String companymobile;
	
	private String companyemail;
	
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby;
	
	@Column(name="updatedby") 
	private String updatedby;
	
	@Column(name="createdby")
	private String createdby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
	
	@Column(name="updateddate") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
	
	@Column(name="status") 
	private String status = "Active";
	
	
}
