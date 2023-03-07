package com.narvee.usit.entity;	

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SalesConsultantDocuments {
	private  MultipartFile resume; 
	private  MultipartFile h1b;
	private  MultipartFile dl;
}