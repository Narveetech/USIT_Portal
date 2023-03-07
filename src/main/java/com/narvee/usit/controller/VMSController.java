package com.narvee.usit.controller;                                                    /*created By Swamy */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Users;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.HelperVmsUpload;
import com.narvee.usit.service.IVmsService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/vms")
@CrossOrigin(origins = "http://localhost:4200")
public class VMSController {
	
	@Autowired 
	public IVmsService service;
	
	@ApiOperation("To save VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveVMS(@RequestBody Vms vms) {
		//System.out.println("save vms "+vms);
		List<Vms> vm = service.dupvendor(vms.getCpemail());
		if (vm == null || vm.isEmpty()) {
			return new ResponseEntity<>(new RestAPIResponse("Success", "Vendor Saved", service.saveVms(vms)), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Vendor Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}
	
	@ApiOperation("To Fetch List Of VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllVMSList() {
		//updateVendor
		System.out.println(service.findAllVms());
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Fetched Successfully", service.findAllVms()),HttpStatus.OK);
	}
	
	@ApiOperation("To Edit/Update VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Edited"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateVMSByID(@RequestBody Vms vms) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Successfully Update", service.saveVms(vms)),HttpStatus.OK);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@ApiOperation("To Delete VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Deleted"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vmsdel/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteVMSByID(@PathVariable Long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Record Deleted Successfully", service.deleteById(id)), HttpStatus.OK);
	}
	
	@ApiOperation("To Fetch VMS By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vm/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getVMSByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Fetched By ID Successfully ", service.getbyId(id)),HttpStatus.OK);
	}
	
	@ApiOperation("To Fetch VMS By filter")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched Records"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/v/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> filterVMS(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Fetched Records", service.filterVms(keyword)), HttpStatus.OK);
	}
		
	@ApiOperation("To fetch VMS By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched VMS"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms1/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> findPaginated(@PathVariable("pageNo") int pageNo) {
		int pageSize = 2;
		Page<Vms> findPaginated = service.findPaginated(pageNo, pageSize);
		List<Vms> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(new RestAPIResponse("success","fetching VMS By Page No",findAlltech),HttpStatus.OK);
	}
	
	
	@ApiOperation("Vms Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change VMS Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value="/status",method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody Vms vms){
		Long id = vms.getId();
		 String status =vms.getStatus();
		 String remarks = vms.getRemarks();
		 
			Long changestat= null;
			String result;
			if(status.equals("Active")) 
				result = "InActive";
			else 
				result = "Active";
			changestat = service.changeStatus(result, id,remarks);
			if(changestat!=0) {
			System.out.println("status Successfully");
			}
			else
			{
				System.out.println("Not Chnaged");
			}
			  service.changeStatus(result, id, remarks);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully","Done" ),HttpStatus.OK);
	}
	
	@RequestMapping(value = "excelupload", method = RequestMethod.POST)
	public ResponseEntity<RestAPIResponse> saveVmsFileUpload(@RequestParam MultipartFile file){
		service.saveVmsFileUpload(file);
		return new ResponseEntity<>(new RestAPIResponse("Success", "excel file uploaded" ),HttpStatus.CREATED);
	}
	
}
