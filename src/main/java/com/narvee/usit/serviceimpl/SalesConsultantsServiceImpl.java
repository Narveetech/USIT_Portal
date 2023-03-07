package com.narvee.usit.serviceimpl;

//import java.awt.print.Pageable;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.helper.SalesConsHelper;
import com.narvee.usit.repository.ISalesConsultantsRepository;
import com.narvee.usit.service.ISalesConsultantsService;

@Service
@Transactional
public class SalesConsultantsServiceImpl implements ISalesConsultantsService {

	
	@Autowired
	private ISalesConsultantsRepository iConsultantsRepo;
	
	@Override
	public boolean saveConsultantss(SalesConsultants con) {		 
		SalesConsultants sc= iConsultantsRepo.save(con);
		if(sc!=null)
		return true;
		else
		return false; 
	}

//	@Override
//	public List<SalesConsHelper> findAllConsultants() {		 
//		  List<SalesConsHelper> findAllReq = iConsultantsRepo.getAllConsDeatals();
//		  System.out.println("Con: "+findAllReq);
//		return findAllReq;
//	}

	@Override
	public SalesConsultants getbyId(long id) {
		return iConsultantsRepo.findById(id).get();
	}

	@Override
	public boolean deleteById(long id) {
				iConsultantsRepo.deleteById(id);
				return true;
	}
	
//	@Override
//	public int changeStatus(String status, long id,String rem) {
//		return iConsultantsRepo.toggleStatus(status, id,rem);
//	}

//	@Override
//	public SalesConsultants getConsultantByid(long id) {
//		SalesConsultants findById = iConsultantsRepo.getConsultantById(id);
//		return findById;
//	}

	@Override
	public SalesConsultants getFile(long fileId) throws FileNotFoundException {
        return iConsultantsRepo.findById(fileId).orElseThrow(() -> new FileNotFoundException("File does not exist" + fileId));
    }
	

	@Override
	public List<SalesConsultants> findSalesConsultants() {		 
				return iConsultantsRepo.findAll();
			
	}


//	@Override
//	public List<Object[]> findskilset() {
//		return iConsultantsRepo.findconsultantexp();
//	}

//	@Override
//	public Page<List<SalesConsHelper>> findPaginated(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		Page<List<SalesConsHelper>> findAll = iConsultantsRepo.getAllSalesConsByPageNo(pageable);
//		return findAll;
//	}

	
}
