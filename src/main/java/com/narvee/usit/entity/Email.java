package com.narvee.usit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Email {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name="email", sequenceName = "email_seq", allocationSize=500)
	private Long id;
	
	@Column(name="mailsubject") 
	private String subject;
	
	@Column(name="body",columnDefinition="TEXT") 
	private String body;
	
	@Column(name="mailfrom") 
	private String from;
	
	
	@Column(name="mail_to")
	private String to;
	
	@Column(name="mail_cc")
	private String cc;
	
	@Column(name="attachment") 
	private String attachment;
	
	@Column(name = "sentdate")
	private String date;
	
	private String subjectcategory;
	
	private String company;
}



