package com.narvee.usit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.service.IRecSubmissionService;
import com.narvee.usit.service.IRecruitingConsultingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/recruiting")
public class RecSubmissionController {
	
	@Autowired
	private IRecSubmissionService service;
	
	@Autowired
	private IRecruitingConsultingService consultantservice;
	
	
	//saving entity
	@ApiOperation("To Save entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Saved"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/saveSubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRequirement(@RequestBody ReqSubmission submission) {
		Object obj = service.saveSubmission(submission);
		return new ResponseEntity<>(new RestAPIResponse("Success","Object saved Successfully",obj),HttpStatus.CREATED);
		
	}
	
	//get all entities
	@ApiOperation("Get all Entities")
	@RequestMapping(value = "/allsubmission", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllSubmission() {
		return new ResponseEntity<>(new RestAPIResponse("Success","fetch All Submissions", service.getAllsubmission()),HttpStatus.OK);
	}
	
	//fetch single entity by id
	@ApiOperation("Get Single Entity By Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Submission Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/submissionbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRequirmentByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Submission Fetch Success Fully", service.getSubmissionByID(id)),HttpStatus.OK);
	}
	
/* to Get Recruiting Consultant Entity By ID */
	
	@ApiOperation("To get Recruiting Consultant entity ByID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultantByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch Successfullt by Consutlant ID", consultantservice.getConsultantByID(id)),HttpStatus.OK);
	}
	
	
	//update  entity
	@ApiOperation("Updating Entity")
	@RequestMapping(value = "/updateSubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editRequirmentByID(@RequestBody ReqSubmission submission) {
		return new ResponseEntity<>(new RestAPIResponse("Success","updated Requirments Successfully", service.saveSubmission(submission)),HttpStatus.ACCEPTED);
	}

	//delete  entity
	@ApiOperation("delete Entity")
	@RequestMapping(value = "submission/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteRequirmentByID(@PathVariable long id){
		return new ResponseEntity<>(new RestAPIResponse("Success", "Deleted Requirment Successfully",service.deleteSubmissionByID(id)),HttpStatus.OK);
	}
	
	//pagination  entity
	@ApiOperation("all entities with pagination")
	@RequestMapping(value = "submission/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultalntByPage(@PathVariable int pageNo) {
		int pageSize = 2;
		Page<List<ReqSubmission>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<ReqSubmission>> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch consultant By pageNo Successfully", findAlltech),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllrequirments", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRequirments() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "getAll Requirments", service.getAllRecRequirments()),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllrecconsultants", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecConsultants() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "getAllRecConsultants", service.getAllRecConsultants()),HttpStatus.OK);
	}
	
	@RequestMapping(value = "getallUsers", method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> getAllUsers() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "message", service.getAllUsers()),HttpStatus.OK);
	}
	
	/*
	@RequestMapping(value = "/requ/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRequirmentsByFilter(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success","fetch Requirments By search", service.getRequirmentByFiletr(keyword)),HttpStatus.OK);
	}
	*/
}
