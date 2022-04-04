package com.springboot.restproject.cruddemo.dao;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.Faculty;

public interface FacultyRespository {

	public List<Faculty> getFaculties(String collegeName);

	public Faculty saveFaculty(Faculty c, int collegeId);

	public Faculty assignFaculty(String facultyName, int departmentId);
	
	public Faculty assignHod(String facultyName, int departmentId);
	
	public Faculty getHod(int departmentId);
	
	public Faculty getFaculty(int theId);
	
	public List<Faculty> getFacultyByDepartment(String collegeName, String deptName);

	public void deleteFaculty(int theId);

}
