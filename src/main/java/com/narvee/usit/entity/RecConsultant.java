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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RecruitingConsultant")
public class RecConsultant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "RecruitingConsultant")
	@SequenceGenerator(name="RecruitingConsultant",sequenceName = "RecruitingConsultantSeq")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
//	@NotNull(message = "Visa Status Does Not Null")
	private long visaid;
	
	@Transient
	private String visatype;

	
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
	
	private String companyname;
	
	private String salespersonname;
	
	private String companymobile;
	
	private String companyemail;
	
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby;
	
	@Transient
	private String addedbyname;
	
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
	
	
	public RecConsultant(Long id, LocalDate createddate, String name, String experience, String technology, String email,String visatype, String addedbyname, String updatedby, String status) {
		this.id = id;
		this.createddate = createddate;
		this.name = name;
		this.experience = experience;
		this.technology = technology;
		this.email = email;
		this.visatype = visatype;
		this.addedbyname = addedbyname;
		this.updatedby = updatedby;
		this.status = status;
	}
	
	
/*	public RecConsultant(long id, String name, String mobile, String email, String visaid, String experience, String rate,
			String location, String technology, String skills, String summary, String resume) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;	
		this.visaid = visaid;
		this.experience = experience;
		this.rate = rate;
		this.location = location;
		this.technology = technology;
		this.skills = skills;
		this.summary = summary;
		this.resume = resume;
	}		*/
	
	
}
