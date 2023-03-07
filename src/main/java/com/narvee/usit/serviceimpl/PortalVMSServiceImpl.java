package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Interview;
import com.narvee.usit.entity.PortalVMS;
import com.narvee.usit.repository.IPortalVMSRepository;
import com.narvee.usit.service.IPortalVMSService;

@Service
public class PortalVMSServiceImpl implements IPortalVMSService{
	
	@Autowired
	private IPortalVMSRepository repository;
	
	@Override
	public boolean savePortalVMS(PortalVMS vms) {
		PortalVMS portalVMS = repository.save(vms);
		if(portalVMS != null) {
			return true;
		}
		return false;
	}

	@Override
	public PortalVMS getPortalVMSByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean deletePortalVMSByID(long id) {
		PortalVMS vms = repository.findById(id).get();
		if(vms != null) {
			repository.delete(vms);
			return true;
		}return false;
	}

	@Override
	public List<Object[]> getAllPortalVMS() {
		return repository.getAllPortalVMS();
	}

	@Override
	public Page<List<Object[]>> getAllPortalVMSBYpageNo(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<Object[]>> findAll = repository.getAllPortalVMSBypage(pageable);
		return findAll;
	}

}
