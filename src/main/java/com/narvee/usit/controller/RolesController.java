package com.narvee.usit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.service.IRoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolesController {

	@Autowired
	private IRoleService Service;
	
	public static final Logger logger = LoggerFactory.getLogger(RolesController.class);

	@ApiOperation("To save Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
	@ApiResponse(code = 404, message = "entity not found"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/saveRoles", method = RequestMethod.POST, produces = "application/json")
	
	public ResponseEntity<RestAPIResponse> AddRoles(@RequestBody Roles roles) {
		
		List<String> finaAllRolByRolName = Service.finaAllRolByRolName(roles.getRolename().toLowerCase());
		
		// System.out.println("Role Data :"+finaAllRolByRolName);
		if (finaAllRolByRolName == null || finaAllRolByRolName.isEmpty()) {
			logger.info("inside class !!! Roles Controller, Method !!!: AddRoles");
			logger.debug("dsfghfhgfh");

			logger.debug("loading index page..");
			logger.info("INFO level message!!");
			logger.warn("WARN level message!!");
			logger.error("ERROR level message !!");
			//logger.fatal("FATAL level message");
			logger.trace("TRACE level message..");
			
			logger.debug("Exit of index().");

			Roles saveroles = Service.saveRole(roles);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Role Saved", saveroles), HttpStatus.CREATED);
		}
		else 
		{
			logger.info("inside class !!! Roles Controller, Method !!!: AddRoles");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Role Already Exist", "Data not Saved"),
					HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("To Update Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
	@ApiResponse(code = 404, message = "entity not found"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/updaterole", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> UpdateRoles(@RequestBody Roles roles) {
		///String rn = roles.getRolename().toLowerCase();
		Roles finaAllRolByRolName = Service.findbyrolenameandid(roles.getRolename(), roles.getRoleid());
		if (finaAllRolByRolName == null) {
			Roles saveroles = Service.saveRole(roles);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Role Updated", saveroles), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Role Already Exist", "Data not Saved"),
					HttpStatus.FORBIDDEN);
		}
	}

}
