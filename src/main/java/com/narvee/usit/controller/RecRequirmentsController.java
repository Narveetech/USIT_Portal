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
import com.narvee.usit.entity.RecRequirements;
import com.narvee.usit.service.IRecRequirmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/recruiting")
public class RecRequirmentsController {
	
	@Autowired
	private IRecRequirmentService service;
	
	@ApiOperation("To Save Requirment")
	@ApiResponses({ @ApiResponse(code = 200, message = "Requirment Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/requirments/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRequirement(@RequestBody RecRequirements requirements) {
		Object obj = service.saveRequirments(requirements);
		return new ResponseEntity<>(new RestAPIResponse("Success","Save Requirments Successfully",obj),HttpStatus.CREATED);
		
	}
	
	@ApiOperation("To Fetch Requirment")
	@ApiResponses({ @ApiResponse(code = 200, message = "Requirment Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/requirments/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRequirmentByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Requirment Fetch Success Fully", service.getRequrimentByID(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/requirments/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRequirments() {
		return new ResponseEntity<>(new RestAPIResponse("Success","fetch All Requirments", service.getAllRequirments()),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/requirments/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editRequirmentByID(@RequestBody RecRequirements requirements) {
		return new ResponseEntity<>(new RestAPIResponse("Success","updated Requirments Successfully", service.updateRequirments(requirements)),HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/requirments/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteRequirmentByID(@PathVariable long id){
		return new ResponseEntity<>(new RestAPIResponse("Success", "Deleted Requirment Successfully",service.deleteRequirmentsByID(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/requirmen/page/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRequirmentsByPageNo(@PathVariable("pageNo") int pageNo) {
		int pageSize = 2;
		Page<RecRequirements> findPaginated = service.findPaginated(pageNo, pageSize);
		List<RecRequirements> findAlltech = findPaginated.getContent();
		return  new ResponseEntity<>(new RestAPIResponse("success","fetching Requirments By Page No",findAlltech),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/requiments/search{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRequirmentsByFilter(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success","fetch Requirments By search", service.getRequirmentByFiletr(keyword)),HttpStatus.OK);
	}
}
