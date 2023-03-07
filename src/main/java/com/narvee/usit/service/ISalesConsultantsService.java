package com.narvee.usit.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.helper.SalesConsHelper;

public interface ISalesConsultantsService {
	public boolean saveConsultantss(SalesConsultants salesConsultants);
//	public List<SalesConsHelper> findAllConsultants();
	public SalesConsultants getbyId(long id);
	public boolean deleteById(long id);
//	public SalesConsultants getConsultantByid(long id);
//	public List<Object[]> findskilset();
//	public int changeStatus(String status, long id,String remarks);
	public SalesConsultants getFile(long fileId) throws FileNotFoundException, FileNotFoundException;
	public List<SalesConsultants> findSalesConsultants();  
	
//	public Page<List<SalesConsHelper>> findPaginated(int pageNo, int pageSize);
}
