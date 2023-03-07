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
import com.narvee.usit.entity.Employee;
import com.narvee.usit.helper.IEmployeeDTO;
import com.narvee.usit.service.IEmployeeService;

@RestController
@RequestMapping("/usit")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService service;
	
	@RequestMapping(value = "/employee/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "saved Employee entity", service.saveEmployee(employee)),HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/employee/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getEmployeeByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched Employee entity", service.getEmployeeByID(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteEmployee(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Employee Entity deleted", service.deleteEmployee(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editEmployeeByID(@RequestBody Employee employee) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "edit employee by ID", service.saveEmployee(employee)),HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/employee/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllEmployees() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "getAll Employees", service.getAllEmployee()),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/page/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllEmployeesByPageNo(@PathVariable int pageNo) {
		int pageSize = 2;
		Page<List<IEmployeeDTO>> findPaginated = service.getAllEmployeesByPage(pageNo, pageSize);
		List<List<IEmployeeDTO>> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch Employees By pageNo Successfully", findAlltech),HttpStatus.OK);
	}
}
