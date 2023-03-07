package com.narvee.usit.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Roles;
import com.narvee.usit.repository.IRolesRepository;
import com.narvee.usit.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRolesRepository iRoleRepo;

	public Roles saveRole(Roles role) {
		Roles saveRole = iRoleRepo.save(role);
		return saveRole;
	}

	@Override
	public List<String> finaAllRolByRolName(String rolename) {
		 
		return iRoleRepo.findRolByRolName(rolename);
	}

	@Override
	public Roles findbyrolenameandid(String name, Long id) {
		// TODO Auto-generated method stub
		return iRoleRepo.findByRolenameAndRoleidNot(name, id);
	}

	//findByRolenameAndRoleidNotIn
	 

}
