package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TechSupport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TechSupport")
	@SequenceGenerator(name = "TechSupport", sequenceName = "techsupportseq")
	private Long id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "pseudoname")
	private String pseudoname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "primarynumber")
	private String primarynumber;
	
	@Column(name = "secondarynumber")
	private String secondarynumber;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "location")
	private String currentlocation;
	
	@Column(name = "technology")
	private Long techid;
	
	@Transient
	private String technology;
	
	@Column(name = "skills")
	private String skills;
	
	@Column(name="updateddate") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
	
	@Column(name="status") 
	private String status = "Active";
	
	
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby;
	
	@Column(name="updatedby") 
	private String updatedby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
}
