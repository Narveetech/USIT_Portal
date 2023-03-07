package com.narvee.usit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.service.IRecruitingConsultingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/Recruiting/")
public class RecConsultantController {
	
	@Autowired
	private IRecruitingConsultingService service;
	
	/* to save Recruiting Consultant Entity */
	
	@ApiOperation("To save Recruiting Consultant entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant saved"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/save",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRecrutingConsultant(@RequestBody RecConsultant consultant) {
		Object object =service.saveRecruitingConsutant(consultant);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Consultant Created Successfully", object),HttpStatus.CREATED);
	}
	
	/* to Get All Recruiting Consultant Entity */
	
	@ApiOperation("To get All Recruiting Consultant entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecruitingConsultant() {
		System.out.println(service.getAllRecruitingConsultant()+"1234");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Consultant fetched Successfully", service.getAllRecruitingConsultant()),HttpStatus.OK);
	}
	
	/* to Get Recruiting Consultant Entity By ID */
	
	@ApiOperation("To get Recruiting Consultant entity ByID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultantByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch Successfullt by Consutlant ID", service.getConsultantByID(id)),HttpStatus.OK);
	}
	
	/* to Delete Recruiting Consultant Entity  By ID */
	
	@ApiOperation("To Delete Recruiting Consultant entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Deleted"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteConsultantByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Deleted Consultant By ID Successfully", service.deleteRecruitingConsultantByID(id)),HttpStatus.OK);
	}
	
	/* to Get Recruiting Consultant Entity By Name 
	
	@ApiOperation("To get Recruiting Consultant entity By Name")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultan/{name}/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getDetailsConsultantByName(@PathVariable String name,@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched Consultant by Name Successfully", service.getDetailsByConsultantName(name,id)),HttpStatus.OK);
	}	*/
	
	/* to Edit Recruiting Consultant Entity By ID */
	
	@ApiOperation("To edit/update Recruiting Consultant entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/edit",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateRecruitingConsultantByID(@RequestBody RecConsultant consultant) {
		return new ResponseEntity<>(new RestAPIResponse("Sucess", "Updated Consultant By ID Successfully", service.saveRecruitingConsutant(consultant)),HttpStatus.ACCEPTED);
	}
	
	/* to Get Recruiting Consultant Entity By pageNo */
	
	@ApiOperation("To get Recruiting Consultant entity By pageNo")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultant/page/{pageNo}",method = RequestMethod.GET, produces  = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultalntByPage(@PathVariable int pageNo) {
		int pageSize = 2;
		Page<List<RecConsultant>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<RecConsultant>> findAlltech = findPaginated.getContent();
//		List<RecrutingConsutantHelper> findAllSalesCon = service.getAllRecruitingConsultant();
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch consultant By pageNo Successfully", findAlltech),HttpStatus.OK);
	}
	
	
/* to Get Recruiting Consultant Entity By pageNo */
	
	@ApiOperation("To get Recruiting Consultant entity By filter")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/search/{keyword}",method = RequestMethod.GET, produces  = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecConsultantByFilter(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched RecConsultant by filter", service.getAllRecConsultantByFilter(keyword)),HttpStatus.OK);
	}
	
	/* to change the Recruiting consultant status 	*/
	
	@ApiOperation("To chage Recruiting Consultant entity Status")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Status Changed"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value="/editstatus",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(HttpServletRequest req){
		long id = Long.parseLong(req.getParameter("id"));
		 String status = req.getParameter("status");
		 
			long changestat= 0;
			String result;
			if(status.equals("Active")) 
				result = "InActive";
			else 
				result = "Active";
			changestat = service.changeStatus(result, id);
			if(changestat!=0) {
			System.out.println("status Successfully");
			}
			else
			{
				System.out.println("Not Chnaged");
			}
			  service.changeStatus(result, id);
	
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully","Done" ),HttpStatus.OK);

	}
}
