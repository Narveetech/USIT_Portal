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
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.service.IRecInterviewService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/recruiting")
public class RecInterviewController {
	
	@Autowired
	private IRecInterviewService service;
	
	/* to save RecInterview Entity 		*/
	
	@ApiOperation("To Save RecInterview entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Saved"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interviews/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRecInterview(@RequestBody RecInterviews interviews) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Save RecInterview Successfully", service.saveInterviews(interviews)),HttpStatus.CREATED);
	}
	
	/* to get All RecInterview Entity 		*/
	
	@ApiOperation("To getAll RecInterview entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interviews", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecInterviewDetails() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched RecInterviews Details Successfully", service.getAllRecInterviewDetails()),HttpStatus.OK);
	}
	
	/* to get by ID RecInterview Entity 		*/
	
	@ApiOperation("To get RecInterview entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interviews/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRecInterviewByID(@PathVariable long id) {
		System.out.println("sdfdh");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched RecInterview ById Successfully", service.getRecInterviewById(id)),HttpStatus.OK);
	}
	
	/* to edit/update RecInterview Entity by ID 		*/
	
	@ApiOperation("To Edit/Update entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Updated"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interview", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateRecInterviewByID(@RequestBody RecInterviews interviews) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "updated Recruiting Interview Succesfully ", service.saveInterviews(interviews)),HttpStatus.ACCEPTED);
	}
	
	/* to Delete RecInterview Entity By ID		*/
	
	@ApiOperation("To Delete RecInterview entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Deleted"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interviews/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteRecInterviewByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success","RecInterview Deleted Successfully", service.deleteRecInterviewByID(id)),HttpStatus.OK);
	}
	
	/* to get All RecInterview Entity By PageNo		*/
	
	@ApiOperation("To get Recinterview entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/intervie/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRecInterviewByPageNo(@PathVariable int pageNo) {
		int pageSize = 2;
		Page<List<RecInterviews>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<RecInterviews>> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch RecInterview By pageNo Successfully", findAlltech),HttpStatus.OK);
	}
	
	/* get Submission Details On consultant for dropdown */
	
/*	@ApiOperation("To get RecSubmission entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/submission", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRecSubmissionDetails() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched recsub details", service.getRecSubDetails()),HttpStatus.OK);
	}		*/
	
	/* get Submission RecConsultant Details On consultant for dropdown */
	
	@ApiOperation("To get RecSubmission entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/subrecconsultants/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSubRecConsultantDetails() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched RecConsultant", service.getRecConsultantDetails()),HttpStatus.OK);
	}
	
	/* get All Users Details		*/
	
	@RequestMapping(value = "users/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> togetUserDetails() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched users Details", service.getUsersDetails()),HttpStatus.OK);
	}
	
	/* get All RecRequirments Details		*/
	
	@RequestMapping(value = "recrequirments/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> toGetAllRecRequirments() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "RecRequirments Details", service.getRecRequirmentsDetails()),HttpStatus.OK);
	}
	
	/* get Recruiting consultants details by ID for anchor tag*/
	
	@ApiOperation("To get RecConsultant entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/recconsultant/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> togetRecConsultantDetails(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Rec Consultant details", service.getConsultantById(id)),HttpStatus.OK);
	}
	
	/* get Recruiting Submission details by ID for anchor tag*/
	
	@ApiOperation("To get RecSubmission entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
	@ApiResponse(code = 404, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "recsubmission/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> togetRecSubmissionDetails(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Rec Submission details", service.getSubmissionByID(id)),HttpStatus.OK);
	}
	
}
