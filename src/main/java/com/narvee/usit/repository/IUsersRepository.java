package com.narvee.usit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narvee.usit.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Serializable> {
	
	public Users findByEmail(String email);
	
	public Users findByEmailAndUseridNot(String email, Long id);

}
