package com.narvee.usit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.RecConsultantNew;
import com.narvee.usit.service.IRecConsService;

@RestController
public class RecConsController {
	
	@Autowired
	private IRecConsService service;
	
	@RequestMapping(value = "/recCons", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRecCons(@RequestBody RecConsultantNew new1) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "save RecCons entity", service.saveRecCons(new1)),HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "rec/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "get ById", service.getByID(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rec", method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> getAll() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "getAll", service.getAll()),HttpStatus.OK);
	}
}
