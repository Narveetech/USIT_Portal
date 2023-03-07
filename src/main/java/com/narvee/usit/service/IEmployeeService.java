package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.Employee;
import com.narvee.usit.helper.IEmployeeDTO;

public interface IEmployeeService {
	
	public boolean saveEmployee(Employee employee);
	
	public Employee getEmployeeByID(long id);
	
	public boolean deleteEmployee(long id);
	
	public List<IEmployeeDTO> getAllEmployee();
	
	public Page<List<IEmployeeDTO>> getAllEmployeesByPage(int pageNo, int pageSize);
}
