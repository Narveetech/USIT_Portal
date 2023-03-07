package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.Users;

public interface IUserService {
	
	public Users saveUser(Users users);
	
	public Users findUserByEmail(String email);
	
	public Users findUserByEmailandUid(String email, Long id);
	
	public List<Users> getAllUsers();
	

}
