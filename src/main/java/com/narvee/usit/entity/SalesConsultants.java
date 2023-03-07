package com.narvee.usit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="tbl_sales_consultant")
public class SalesConsultants {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "SalesConsultants")
	@SequenceGenerator(name="SalesConsultants",sequenceName = "salesConuSeq")
	@Column(name="id")
	private long sid; 
	
	@Column(name="FullName")
	private String fullName;			
	
	@Column(name="email",length = 20)
	private String email;
	
	@Column(name="USContactNumber")
	private String usContactNumber;
	
	@Column(name="VisaStatus")
	private String visaStatus;
	
	@Column(name="TotalExperience")
	private String totalExperience;
	
	@Column(name="CurrentLocation")
	private String currentLocation;
	
	@Column(name="relocate")
	private String relocate;
	
	@Column(name="Technology")
	private String technology;
	
	@Column(name="AdditionalSkills", length=3500)
	private String additionalSkills;		
	
	@Column(name="RateType")
	private String rateType;
	
	@Column(name="HourlyRate")
	private String hourlyRate;		
	 		
	@Column(name="summary",length=5000)
	private String summary;
	
	@Column(name="resume")
	private String resumePath;
	 				
	@Column(name="H1bCopy")
	private String h1bCopyPath;
					
	@Column(name="DrivingLicenceCopy")
	private String drivingLicenceCopyPath;	
	 
	@Column(name="Priority")
	private String priority;
	
	@Column(name="relocateval")
	private String relocateVal;
	
	@Column(name="AnyRestriction")
	private String anyRestriction;
	
	@Column(name="status")
	private String status="Active";
	 

	@Column(name="added_by")
	private String added_by;
	
	@Column(name="remarks")
	private String remarks;
	
		 
 
}
