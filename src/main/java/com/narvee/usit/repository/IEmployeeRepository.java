package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.Employee;
import com.narvee.usit.helper.IEmployeeDTO;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Serializable>{
	
//	@Query(value = "SELECT e.id, e.name, e.pseudoname, e.email,e.personalnumber,e.designation,e.status FROM employee e, roles r WHERE e.roles = r.roleid", nativeQuery = true)
//	public List<Object[]> getAllEmployees();
//	
	
	@Query(value = "SELECT e.id, e.name, e.pseudoname, e.email,e.personalnumber,e.designation,e.status, r.rolename,r.roleid FROM employee e, roles r WHERE e.roles = r.roleid", countQuery = "SELECT count(*) FROM employee e, roles r WHERE e.roles = r.roleid", nativeQuery = true)
	public Page<List<IEmployeeDTO>> getAllEmployeesByPageNo(Pageable pageable);
	
	@Query(value = "SELECT e.id, e.name, e.pseudoname, e.email,e.personalnumber,e.designation,e.status, r.rolename,r.roleid FROM employee e, roles r WHERE e.roles = r.roleid", nativeQuery = true)
	public List<IEmployeeDTO> getAllEmployees();
}
