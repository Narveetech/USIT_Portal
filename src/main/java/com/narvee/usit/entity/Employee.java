package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Employee")
	@SequenceGenerator(name = "Employee", sequenceName = "employeeSeq")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "pseudoname")
	private String pseudoname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "personalnumber")
	private String personalnumber;
	
	@Column(name = "companynumber")
	private String companynumber;
	
	@Column(name = "designation")
	private String designation;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "roles")
	private Roles roles;
	

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
