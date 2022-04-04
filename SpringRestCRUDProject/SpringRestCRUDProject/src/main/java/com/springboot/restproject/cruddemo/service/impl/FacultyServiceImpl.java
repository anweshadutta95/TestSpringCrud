package com.springboot.restproject.cruddemo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.restproject.cruddemo.dao.FacultyRespository;
import com.springboot.restproject.cruddemo.entity.Faculty;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.exception.FacultyNotFoundException;
import com.springboot.restproject.cruddemo.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService{

	private FacultyRespository facultyRespository;
	
	@Autowired
	public FacultyServiceImpl(FacultyRespository facultyRespository) {
		this.facultyRespository = facultyRespository;
	}

	@Override
	public List<Faculty> getFaculties(String collegeName) throws CollegeNotFoundException,Exception  {
		return facultyRespository.getFaculties(collegeName);
	}

	@Override
	public Faculty saveFaculty(Faculty f, int collegeId) throws CollegeNotFoundException,Exception  {
		return facultyRespository.saveFaculty(f, collegeId);
	}
	
	@Override
	public Faculty assignFaculty(String facultyName, int departmentId) throws DepartmentNotFoundException, FacultyNotFoundException, Exception  {
		return facultyRespository.assignFaculty(facultyName, departmentId);
	}
	
	@Override
	public Faculty assignHod(String facultyName, int departmentId) throws DepartmentNotFoundException, Exception  {
		return facultyRespository.assignHod(facultyName, departmentId);
	}
	
	@Override
	public Faculty getHod(int departmentId) throws DepartmentNotFoundException, FacultyNotFoundException, Exception  {
		return facultyRespository.getHod(departmentId);
	}

	@Override
	public Faculty getFaculty(int theId) throws FacultyNotFoundException,Exception  {
		Faculty f = facultyRespository.getFaculty(theId);
		if(f == null) {
			throw new FacultyNotFoundException("Faculty Id "+theId+" not found");
		}
		return f;
	}
	
	@Override
	public void deleteFaculty(int theId) throws Exception {
		facultyRespository.deleteFaculty(theId);
	}

	@Override
	public List<Faculty> getFacultyByDepartment(String collegeName, String departmentName) throws DepartmentNotFoundException, Exception {
		return facultyRespository.getFacultyByDepartment(collegeName, departmentName);
	}

}
