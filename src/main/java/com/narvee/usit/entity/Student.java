package com.narvee.usit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "message", columnDefinition = "LONGTEXT")
	private String message;
	
	@Column(name = "body", columnDefinition = "MEDIUMTEXT")
	private String body;
	
	@Column(name = "name", columnDefinition = "TEXT")
	private String name;
}
