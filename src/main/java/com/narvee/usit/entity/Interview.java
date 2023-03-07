package com.narvee.usit.entity;																			/*Created By Swamy 	*/

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
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name="interview")
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO,generator = "Interview")
		@SequenceGenerator(name="Interview",sequenceName = "intrSeq")
		@Column(name="intrid")
		private Long intrid;
		private String  submissionid;
		
		@Transient
		private String  consultantname;
		
		@Transient
		private String vendor;
		
		@Transient
		 private LocalDate dateofsubmission; 
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@Column(name= "interviewdate")
	    private String interviewDate;
		
		private String timezone;
		private String round;
		private String modeofintr;
		private String feedback;
		private String interviewstatus;
		@Column(name= "addedBy", nullable = false, updatable = false)
		private long addedby; 
		
		@Transient
		private String uid; 
		
		@Transient
		private long consultantid;
		
		@Column(name="updatedby")
		private String updatedby;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@Column(name= "createddate", nullable = false, updatable = false)
		@CreationTimestamp()
	    private LocalDateTime createddate;
		
		private String status="Active";
		
		
		public Interview(Long intrid,long consultantid,String consultantname, String interviewDate,String timezone, String round, String modeofintr,
				String vendor, LocalDate dateofsubmission, String uid, String interviewstatus, String status) {
			super();
			this.intrid = intrid;
			this.consultantid = consultantid;
			this.consultantname = consultantname;
			this.interviewDate = interviewDate;
			this.timezone = timezone;
			this.round = round;
			this.modeofintr = modeofintr;
			this.vendor = vendor;
			this.dateofsubmission = dateofsubmission;
			this.uid = uid;
			this.interviewstatus = interviewstatus;
			this.status = status;
		}

}
