package com.springboot.restproject.cruddemo.dao;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.Faculty;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.exception.FacultyNotFoundException;

public interface FacultyRespository {

	public List<Faculty> getFaculties(String collegeName) throws CollegeNotFoundException,Exception;

	public Faculty saveFaculty(Faculty c, int collegeId) throws CollegeNotFoundException,Exception;

	public Faculty assignFaculty(String facultyName, int departmentId) throws DepartmentNotFoundException,FacultyNotFoundException,Exception;
	
	public Faculty assignHod(String facultyName, int departmentId) throws DepartmentNotFoundException,Exception;
	
	public Faculty getHod(int departmentId) throws DepartmentNotFoundException,FacultyNotFoundException,Exception;
	
	public Faculty getFaculty(int theId) throws FacultyNotFoundException,Exception;
	
	public List<Faculty> getFacultyByDepartment(String collegeName, String deptName) throws DepartmentNotFoundException, Exception;

	public void deleteFaculty(int theId) throws Exception;

}
