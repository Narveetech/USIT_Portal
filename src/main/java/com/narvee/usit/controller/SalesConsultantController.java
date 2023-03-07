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
import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.helper.SalesConsHelper;
import com.narvee.usit.service.ISalesConsultantsService;

@RestController
@RequestMapping("/usit/sales")
public class SalesConsultantController {
	
	@Autowired
	private ISalesConsultantsService service;
	
	/* save sales Consultant Entity */
	
	@RequestMapping(value = "AddNewConsultants",method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveCons(@RequestBody SalesConsultants salesConsultants){
		service.saveConsultantss(salesConsultants);
		return new ResponseEntity<>(new RestAPIResponse("success", "Save Sales Con",service.saveConsultantss(salesConsultants)),HttpStatus.CREATED);
	} 
	
	/* get All sales Consultant Entity */
	
//	@RequestMapping(value = "AllConsultant",method = RequestMethod.GET,produces = "application/json")
//	public ResponseEntity<RestAPIResponse> getAllConsultant() {
//		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfuuly fetched Sales consultant details", service.findAllConsultants()),HttpStatus.OK);
//	}
	
	/* get sales Consultant Entity By ID */
	
	@RequestMapping(value = "/consultant/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesConsByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched By Cons By Id", service.getbyId(id)),HttpStatus.OK);
	}
	
	/* Delete sales Consultant Entity By ID */
	
	@RequestMapping(value = "/consultant/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteConsByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully deleted", service.deleteById(id)),HttpStatus.OK);
	}
	
	/* get sales Consultant Entity  By Page No*/
	
//	@RequestMapping(value = "/consutlant/{pageNo}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<RestAPIResponse> getSalesConsByPageNo(@PathVariable int pageNo) {
//		int pageSize = 2;
//		Page<List<SalesConsHelper>> findPaginated = service.findPaginated(pageNo, pageSize);
//		List<List<SalesConsHelper>> findAlltech = findPaginated.getContent();
////		List<RecrutingConsutantHelper> findAllSalesCon = service.getAllRecruitingConsultant();
//		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch salesCons By pageNo Successfully", findAlltech),HttpStatus.OK);
//	}
//	
	/* Edit sales Consultant Entity By ID */
	
	@RequestMapping(value = "editConsultants",method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<RestAPIResponse> editSalesConsByID(@RequestBody SalesConsultants consultants) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Succesfully sales consultant edited", service.saveConsultantss(consultants)),HttpStatus.ACCEPTED);
	}
	
	//save the sales consultants
/*		@RequestMapping(value = "NewConsultants",method = RequestMethod.POST,produces = "application/json")	
		public ResponseEntity<RestAPIResponse> saveConss(
				@RequestBody SalesConsultants salesConsultants,
		   SalesConsultantDocuments sCD,
		   @RequestParam("resume") MultipartFile resume,
		   @RequestParam("h1b") MultipartFile h1b,
		   @RequestParam("dl") MultipartFile dl) throws IOException {
			
			// location to store files
			String storeLocation = "C:\\Users\\polak\\OneDrive\\Desktop\\swamy";
			File file = new File(storeLocation);
			if (!file.exists()) {
				file.mkdir();
			}
			
			String resumeFile = "";
			String h1bFile = "";
			String dlFile = "";
			
			sCD.setResume(resume);
			sCD.setH1b(h1b);
			sCD.setDl(dl);
			
			
			// get getmethod--- return type file
			MultipartFile rFile = sCD.getResume();
			MultipartFile hFile = sCD.getH1b();
			MultipartFile dDile = sCD.getDl();
			
			System.out.println("rFile: "+rFile);
			System.out.println("hFile: "+hFile);
			System.out.println("dDileFile: "+hFile);

			if (!rFile.isEmpty()) {
				System.out.println("resuime");
				InputStream inR = rFile.getInputStream();
				resumeFile = StringUtils.cleanPath(rFile.getOriginalFilename());
				OutputStream osResume = new FileOutputStream(file.getAbsolutePath() + "\\" + resumeFile);
				IOUtils.copy(inR, osResume);
				salesConsultants.setResumePath(file.getAbsolutePath() + "\\" + resumeFile);
			} 
			// get the input stream of them

			if (!hFile.isEmpty()) {
				System.out.println("h1b");

				InputStream inH = hFile.getInputStream();
				h1bFile = StringUtils.cleanPath(hFile.getOriginalFilename());
				OutputStream osH1b = new FileOutputStream(file.getAbsolutePath() + "\\" + h1bFile);
				IOUtils.copy(inH, osH1b);
				salesConsultants.setH1bCopyPath(file.getAbsolutePath() + "\\" + h1bFile);

			}
			if (!dDile.isEmpty()) {
				System.out.println("dl");

				InputStream inD = dDile.getInputStream();
				dlFile = StringUtils.cleanPath(dDile.getOriginalFilename());
				OutputStream osDL = new FileOutputStream(file.getAbsolutePath() + "\\" + dlFile);
				IOUtils.copy(inD, osDL);
				salesConsultants.setDrivingLicenceCopyPath(file.getAbsolutePath() + "\\" + dlFile);
			}
			boolean saveConsultantss = service.saveConsultantss(salesConsultants);

			return new ResponseEntity<>(new RestAPIResponse("success", "Saved Sales Con",saveConsultantss),HttpStatus.OK);
			
		}		*/
}
