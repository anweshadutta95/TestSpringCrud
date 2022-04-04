package com.springboot.restproject.cruddemo.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.restproject.cruddemo.entity.Faculty;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.exception.FacultyNotFoundException;
import com.springboot.restproject.cruddemo.service.FacultyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bootapi")
public class FacultyCrudRestController {

	@Autowired
	private FacultyService facultyService;

	@ApiOperation(value = "This method is used to get all faculties by college name.")
	@GetMapping("/faculties/name/{collegeName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<Faculty> getAllFaculties(@PathVariable String collegeName) throws CollegeNotFoundException, Exception {
		return facultyService.getFaculties(collegeName);
	}

	@ApiOperation(value = "This method is used to get a faculty by id.")
	@GetMapping("/faculties/id/{facultyId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty getFacultyById(@PathVariable int facultyId) throws FacultyNotFoundException,Exception {
		Faculty f = facultyService.getFaculty(facultyId);
		return f;
	}

	@ApiOperation(value = "This method is used to get faculties by department name.")
	//GET FACULTY LIST BY DEPT NAME
	@GetMapping("/faculties/dept/{collegeName}/{departmentName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<Faculty> getFacultyByDepartment(@PathVariable String collegeName, @PathVariable String departmentName) throws DepartmentNotFoundException, Exception {
		List<Faculty> f = facultyService.getFacultyByDepartment(collegeName, departmentName);
		return f;
	}

	@ApiOperation(value = "This method is add a faculty to a college.")
	@PostMapping("/faculties/{collegeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty saveFaculty(@RequestBody Faculty faculty, @PathVariable int collegeId) throws CollegeNotFoundException, Exception {
		faculty.setId(0);
		return facultyService.saveFaculty(faculty, collegeId);
	}

	//ASSIGN FACULTY TO DEPT
	@ApiOperation(value = "This method is used to assign a faculty to a department.")
	@PutMapping("/faculties/{departmentId}/{facultyName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty updateFaculty(@PathVariable String facultyName, @PathVariable int departmentId) throws DepartmentNotFoundException, FacultyNotFoundException, Exception {
		return facultyService.assignFaculty(facultyName, departmentId);
	}

	//ASSIGN HOD TO DEPT
	@ApiOperation(value = "This method is used to assign HOD to a department.")
	@PutMapping("/faculties/hod/{departmentId}/{facultyName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty assignHod(@PathVariable String facultyName, @PathVariable int departmentId) throws DepartmentNotFoundException, Exception {
		return facultyService.assignHod(facultyName, departmentId);
	}

	//GET HOD FROM DEPT
	@ApiOperation(value = "This method is used to get HOD of a department.")
	@GetMapping("/faculties/gethod/{departmentId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty getHod(@PathVariable int departmentId) throws DepartmentNotFoundException, FacultyNotFoundException, Exception {
		return facultyService.getHod(departmentId);
	}

}
