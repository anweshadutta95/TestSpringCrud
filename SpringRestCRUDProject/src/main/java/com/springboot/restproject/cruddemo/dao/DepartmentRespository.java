package com.springboot.restproject.cruddemo.dao;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.Department;

public interface DepartmentRespository {

	public List<Department> getDepartments(String collegeName);

	public Department saveDepartment(Department c, int collegeId);

	public Department getDepartment(int theId, String collegeName);

	public void deleteDepartment(int theId);
	
}
