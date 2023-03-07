package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
public class PortalVMS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PortalVMS")
	@SequenceGenerator(name = "PortalVMS", sequenceName = "portalvmsseq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "companyname")
	private String companyname;
	
	@Column(name = "technology")
	private Long techid;
	
	@Column(name = "recruitername")
	private String recruitername;
	
	@Column(name = "contactnumber")
	private String contactnumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "otherdetails")
	private String otherdetails;
	
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby=1;
	
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
	
	public PortalVMS(Long id, String companyname, Long techid, String recruitername, String contactnumber, String email) {
		this.id = id;
		this.companyname = companyname;
		this.techid = techid;
		this.recruitername = recruitername;
		this.contactnumber = contactnumber;
		this.email = email;
	}
}
