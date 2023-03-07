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
import com.narvee.usit.entity.PortalVMS;
import com.narvee.usit.service.IPortalVMSService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit")
public class PortalVMSController {
	
	@Autowired
	private IPortalVMSService service;
	
	/* to save PortalVMS 		*/
	
	@ApiOperation("To Save Portal VMS Entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "PortalVMS Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/portalvms/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> savePortalVMS(@RequestBody PortalVMS vms) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Saved PortalVMS", service.savePortalVMS(vms)),HttpStatus.CREATED);
	}
	
	/* to get PortalVMS BY ID 		*/
	
	@ApiOperation("To get PortalVMS entity By ID ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched PortalVMS By ID"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/portalvms/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getPortalVMSByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Portal Vms Feted", service.getPortalVMSByID(id)),HttpStatus.OK);
	}
	
	/* to edit/update PortalVMS By ID		*/
	
	@ApiOperation("To Edit/Update PortalVMS entity By ID ")
	@ApiResponses({ @ApiResponse(code = 200, message = "updates PortalVMS By ID"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/portalvms/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updatePortalVMSByID(@RequestBody PortalVMS portalVMS) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Updated PortalVMS Successfully", service.savePortalVMS(portalVMS)),HttpStatus.ACCEPTED);
	}
	
	/* to Delete PortalVMS By ID 		*/
	
	@ApiOperation("To Delete PortalVMS entity By ID ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Deleted PortalVMS By ID"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/portalvms/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deletePortalVMSByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Portal VMS Deleted", service.deletePortalVMSByID(id)),HttpStatus.OK);
	}
	
	/* to getAll PortalVMS 		*/
	
	@ApiOperation("To getAll PortalVMS entity  ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched PortalVMS "),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/portalvms/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllPortalVMS() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "get All PortalVMS", service.getAllPortalVMS()),HttpStatus.OK);
	}
	
	/* to getAll PortalVms By PageNo	*/
	
	@ApiOperation("To get PortalVMS entity By page ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched PortalVMS By Page"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "portalvms/page/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllPortalVMSByPageNO(@PathVariable int pageNo) {
		int pageSize = 2;
		Page<List<Object[]>> findPaginated = service.getAllPortalVMSBYpageNo(pageNo, pageSize);
		List<List<Object[]>> findAll = findPaginated.getContent();
		return new ResponseEntity<>(new RestAPIResponse("Success", "get All PortalVMS By pageNo", findAll),HttpStatus.OK);
	}
}
