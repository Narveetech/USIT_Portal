package com.narvee.usit.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name="sales_submission")
@NoArgsConstructor
@AllArgsConstructor
public class SalesSubmission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "SalesSubmission")
	@SequenceGenerator(name="SalesSubmission",sequenceName = "salesConuSeq")
	@Column(name="subid")
	private Long subid;
	private String  consultantid;
	@Transient
	private long cid;
	
	private String positionTitle;
	private String projectLocation;
	private String subrate;
	private String endClient;
	private String partner;
	private String vendor;
	private String rateType;
	private String contactPerson;
	private String contactNumber;
	private String contactemail;
	@Column(name= "addedBy", nullable = false, updatable = false)
	private String addedBy;
	@Transient
	private long uid;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "created_date", nullable = false, updatable = false)
	@CreationTimestamp()
    private LocalDate created_date;
	@Column(name="status")
	private String status = "Active";
	
//	@OneToMany(mappedBy = "sales", cascade = CascadeType.REMOVE)
//	private List<Interview> inter;

	
	public SalesSubmission(long cid,long uid,LocalDate created_date,Long subid,String  consultantid,String positionTitle,String  projectLocation,String endClient,String vendor, String subrate,String addedBy) {
		this.created_date = created_date;
		this.cid=cid;
		this.uid=uid;
		this.subid=subid;
		this.projectLocation = projectLocation;
		this.consultantid = consultantid;
		this.positionTitle=positionTitle;
		this.endClient = endClient;
		this.vendor = vendor;
		this.subrate = subrate;
		this.addedBy = addedBy;
	}
	
}


