package com.narvee.usit.controller;

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
import com.narvee.usit.entity.Users;
import com.narvee.usit.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/usit/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
	
	public static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private IUserService iUserService;

	@ApiOperation("To save Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> addUsers(@RequestBody Users users) {
		Users email = iUserService.findUserByEmail(users.getEmail());
		if (email == null) {
			Users saveUser = iUserService.saveUser(users);
			logger.trace("inside class: !! UsersController !!! method: ! addUsers");
			return new ResponseEntity<>(new RestAPIResponse("Success", "User Saved", saveUser), HttpStatus.CREATED);
		} else {
			logger.info("inside class: !! UsersController !!! method: ! addUsers");
			logger.trace("inside class: !! UsersController !!! method: ! addUsers");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}

	}

	@ApiOperation("To Update Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateUsers(@RequestBody Users users) {
		Users email = iUserService.findUserByEmailandUid(users.getEmail(), users.getUserid());
		if (email == null) {
			Users saveUser = iUserService.saveUser(users);
			return new ResponseEntity<>(new RestAPIResponse("Success", "User Updated", saveUser), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}

	// to check authorization
	@ApiOperation("To Edit Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllUsers() {
		logger.debug("inside class: !! UsersController !!! method :!getAllUsers");
		return new ResponseEntity<>(new RestAPIResponse("Success", "getAll Users", iUserService.getAllUsers()), HttpStatus.OK);
	}

	@ApiOperation("To Delete Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteUser() {
		System.out.println("Inside Delete User");
		return null;
	}

}
