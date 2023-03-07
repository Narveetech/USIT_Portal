package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Employee;
import com.narvee.usit.helper.IEmployeeDTO;
import com.narvee.usit.repository.IEmployeeRepository;
import com.narvee.usit.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private IEmployeeRepository repository;
	
	@Override
	public boolean saveEmployee(Employee employee) {
		Employee emp = repository.save(employee);
		if(emp != null) {
			return true;
		}
		return false;
	}

	@Override
	public Employee getEmployeeByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean deleteEmployee(long id) {
		Employee employee = repository.findById(id).get();
		if(employee != null) {
			repository.delete(employee);
			return true;
		}
		return false;
	}

	@Override
	public List<IEmployeeDTO> getAllEmployee() {
		return repository.getAllEmployees();
	}

	@Override
	public Page<List<IEmployeeDTO>> getAllEmployeesByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<IEmployeeDTO>> findAll = repository.getAllEmployeesByPageNo(pageable);
		return findAll;
	}

}
