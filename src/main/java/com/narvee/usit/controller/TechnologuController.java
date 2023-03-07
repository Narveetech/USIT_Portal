package com.narvee.usit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Technologies;
import com.narvee.usit.service.TechnologyService;

@RestController
public class TechnologuController {
	
	@Autowired
	private TechnologyService service;
	
	@RequestMapping(value = "/tech", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> savetech(@RequestBody Technologies technologies) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Saves Tech", service.saveTechnology(technologies)),HttpStatus.CREATED);
	}
}
