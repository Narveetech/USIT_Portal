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
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.helper.SalesSubmissionHelper;
import com.narvee.usit.service.ISubmissionService;

@RestController
@RequestMapping("usit//sales")
public class SalesSubmissionController {
	
	@Autowired 
	private ISubmissionService service;
	
	@RequestMapping(value = "/addSubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveSlesSubmission(@RequestBody SalesSubmission submission) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Successfully Saveed Sales Submission", service.saveSubmission(submission)),HttpStatus.CREATED);
	}
	
//	@RequestMapping(value = "/getallsubmission", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<RestAPIResponse> getAllSalesSubmission() {
//		return new ResponseEntity<>(new RestAPIResponse("Success","Successfully Fetched Sales Submission", service.findAllSubmission()),HttpStatus.OK);			
//	}
//	
	@RequestMapping(value = "/submission/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesSubmissionByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "SuccesfullyFetched ", service.getSubmissionByID(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/submission/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteSalesSubmissionById(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Sales Submission deleted By ID", service.deleteSalesSubmission(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "editsubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editSaleSubmissionById(@RequestBody SalesSubmission submission) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully edit SalesSubmission By ID", service.saveSubmission(submission)),HttpStatus.ACCEPTED);
	}

//	@RequestMapping(value = "/submissions/{pageNo}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<RestAPIResponse> getSalesSubmissionByPageNo(@PathVariable int pageNo) {
//		int pageSize = 2;
//		Page<List<SalesSubmissionHelper>> findPaginated = service.findPaginated(pageNo, pageSize);
//		List<List<SalesSubmissionHelper>> findAlltech = findPaginated.getContent();
////		List<RecrutingConsutantHelper> findAllSalesCon = service.getAllRecruitingConsultant();
//		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch salesCons By pageNo Successfully", findAlltech),HttpStatus.OK);
//
//	}
	
//	@RequestMapping(value = "/cons", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<RestAPIResponse> getconsDetailsBySubmission() {
//		return new ResponseEntity<>(new RestAPIResponse("Success", "SuccessfullyFetched Consultant Details", service.getConDetailsBySubmission()),HttpStatus.OK);
//	}
}
