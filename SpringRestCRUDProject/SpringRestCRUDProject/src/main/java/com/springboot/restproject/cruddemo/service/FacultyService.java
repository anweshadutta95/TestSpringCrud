package com.springboot.restproject.cruddemo.service;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.Faculty;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.exception.FacultyNotFoundException;

public interface FacultyService {
	
	public List<Faculty> getFaculties(String collegeName) throws CollegeNotFoundException,Exception;

	public Faculty saveFaculty(Faculty c, int collegeId) throws CollegeNotFoundException,Exception;
	
	public Faculty assignFaculty(String facultyName, int departmentId) throws DepartmentNotFoundException,FacultyNotFoundException,Exception;
	
	public Faculty assignHod(String facultyName, int departmentId) throws DepartmentNotFoundException,Exception;
	
	public Faculty getHod(int departmentId) throws DepartmentNotFoundException,FacultyNotFoundException,Exception;

	public Faculty getFaculty(int theId) throws FacultyNotFoundException,Exception;

	public void deleteFaculty(int theId) throws Exception;

	public List<Faculty> getFacultyByDepartment(String collegeName, String departmentName) throws DepartmentNotFoundException, Exception;

}
