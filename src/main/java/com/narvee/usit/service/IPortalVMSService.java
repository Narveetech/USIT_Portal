package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.PortalVMS;

public interface IPortalVMSService {
	
	public boolean savePortalVMS(PortalVMS vms);
	
	public PortalVMS getPortalVMSByID(long id);
	
	public boolean deletePortalVMSByID(long id);
	
	public List<Object[]> getAllPortalVMS();
	
	public Page<List<Object[]>> getAllPortalVMSBYpageNo(int pageNo, int pageSize);
	
}
