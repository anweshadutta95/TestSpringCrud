package com.springboot.restproject.cruddemo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.restproject.cruddemo.dao.DepartmentRespository;
import com.springboot.restproject.cruddemo.entity.Department;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRespository departmentRepository;
	
	@Autowired
	public DepartmentServiceImpl(DepartmentRespository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> getDepartments(String collegeName) throws Exception {
		return departmentRepository.getDepartments(collegeName);
	}

	@Override
	public Department saveDepartment(Department d, int collegeId) throws Exception {
		return departmentRepository.saveDepartment(d, collegeId);
	}

	@Override
	public Department getDepartment(int theId) throws DepartmentNotFoundException,Exception {
		Department d = departmentRepository.getDepartment(theId);
		if(d == null) {
			throw new DepartmentNotFoundException("Department Id "+theId+" not found");
		}
		return d;
	}

	@Override
	public void deleteDepartment(int theId) throws Exception {
		departmentRepository.deleteDepartment(theId);
	}

}
