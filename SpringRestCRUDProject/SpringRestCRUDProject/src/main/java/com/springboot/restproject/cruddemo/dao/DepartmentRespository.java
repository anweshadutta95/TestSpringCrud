package com.springboot.restproject.cruddemo.dao;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.Department;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;

public interface DepartmentRespository {

	public List<Department> getDepartments(String collegeName) throws Exception;

	public Department saveDepartment(Department c, int collegeId) throws Exception;

	public Department getDepartment(int theId) throws DepartmentNotFoundException,Exception;

	public void deleteDepartment(int theId) throws Exception;
	
}
