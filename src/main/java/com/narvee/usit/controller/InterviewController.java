package com.narvee.usit.controller;																			/* created By Swamy   			*/

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
import com.narvee.usit.entity.Interview;
import com.narvee.usit.service.IinterviewService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/sales")
public class InterviewController {
		
	@Autowired
	public IinterviewService service;
	
	/* to save sales interview 	*/
	
	@ApiOperation("To Save Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interviews/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveSalesInterview(@RequestBody Interview interview) {
//		List<Object[]> skilset = service.getsubdet();
		return new ResponseEntity<>(new RestAPIResponse("Success", "Interview Save Succesfully", service.saveSubmission(interview)),HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/subd", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSubDetails(){
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched Sub details", service.getsubdet()),HttpStatus.OK);
	}

	@ApiOperation("To Delete Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Deleted"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "interviews/{id}/delete", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteSalesInterview(@PathVariable int id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Interview Deleted Successfully", service.deleteInterviewById(id)),HttpStatus.OK);
	}
	
	@ApiOperation("To Fetch Interview By InterviewID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Fetched"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interview/{id}/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesInterviewByID(@PathVariable int id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Interview Fetched Successfully", service.getInterviewByID(id)),HttpStatus.OK);
	}
	
	@ApiOperation("To fetch Interview By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched Interviews"),
			@ApiResponse(code = 404, message = "Interviews entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interview/{pageNo}/getpage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> findPaginated(@PathVariable("pageNo") int pageNo) {
		int pageSize = 2;
		Page<List<Interview>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<Interview>> findAlltech = findPaginated.getContent();
	/*	int totalpages = findPaginated.getTotalPages();
		List<Technologies> findAllTechnologies = service.findAllTechnologies();
		int size= findAllTechnologies.size(); 	*/
		return new ResponseEntity<>(new RestAPIResponse("success","fetching Interview By Page No Successfully",findAlltech),HttpStatus.OK);
	}
	
	@ApiOperation("To fetch Interview By Filter/Search ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched Interviews"),
			@ApiResponse(code = 404, message = "Interviews entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interview/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllInterviewBasedOnFilter(@PathVariable String keyword) {
		System.out.println(keyword+"dsghfgdsgfhdgjdhghjfd");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetching Interview Successfully", service.getAllInterviewBasedOnFilter(keyword)),HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/interviews/getall", method = RequestMethod.GET, produces = "application/json")
	public  ResponseEntity<RestAPIResponse> getAllInterviewDetais() {
		return new ResponseEntity<>(new RestAPIResponse("Success","Successfully Fetched Interviews",service.getAllDetailsInterview()),HttpStatus.OK);
	}
	
	@ApiOperation("To Edit/Update Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview updated"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interview/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editSalesInterview(@RequestBody Interview interview) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Interview Edited Succesdfully", service.saveSubmission(interview)),HttpStatus.ACCEPTED);
	} 
	
	@ApiOperation("To fetch salesconsultant By id  ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched salesconsultant"),
			@ApiResponse(code = 404, message = "salesconsultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultant/{id}/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> togetSalesConsByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "salesCons By id success", service.getSalesConsById(id)),HttpStatus.OK);
	}
	
/*	@ApiOperation("To Fetch All Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Fetched"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "interviews", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllInterviews() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Interview Fetched Successfully", service.getAllInterviews()),HttpStatus.OK);
	}  */
}
