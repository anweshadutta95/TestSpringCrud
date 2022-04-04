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
import com.springboot.restproject.cruddemo.exception.FacultyNotFoundException;
import com.springboot.restproject.cruddemo.service.FacultyService;

@RestController
@RequestMapping("/bootapi")
public class FacultyCrudRestController {

	@Autowired
	private FacultyService facultyService;

	@GetMapping("/faculties/name/{collegeName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<Faculty> getAllFaculties(@PathVariable String collegeName) {
		return facultyService.getFaculties(collegeName);
	}

	@GetMapping("/faculties/id/{facultyId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty getFacultyById(@PathVariable int facultyId) {
		Faculty f = facultyService.getFaculty(facultyId);
		if(f == null ) {
			throw new FacultyNotFoundException("Faculty ID "+facultyId+" not found");
		}
		return f;
	}

	//GET FACULTY LIST BY DEPT NAME
	@GetMapping("/faculties/dept/{collegeName}/{departmentName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<Faculty> getFacultyByDepartment(@PathVariable String collegeName, @PathVariable String departmentName) {
		List<Faculty> f = facultyService.getFacultyByDepartment(collegeName, departmentName);
		return f;
	}

	@PostMapping("/faculties/{collegeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty saveFaculty(@RequestBody Faculty faculty, @PathVariable int collegeId) {
		faculty.setId(0);
		return facultyService.saveFaculty(faculty, collegeId);
	}

	//ASSIGN FACULTY TO DEPT
	@PutMapping("/faculties/{departmentId}/{facultyName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty updateFaculty(@PathVariable String facultyName, @PathVariable int departmentId) {
		return facultyService.assignFaculty(facultyName, departmentId);
	}

	//ASSIGN HOD TO DEPT
	@PutMapping("/faculties/hod/{departmentId}/{facultyName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty assignHod(@PathVariable String facultyName, @PathVariable int departmentId) {
		return facultyService.assignHod(facultyName, departmentId);
	}

	//GET HOD FROM DEPT
	@GetMapping("/faculties/gethod/{departmentId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Faculty getHod(@PathVariable int departmentId) {
		return facultyService.getHod(departmentId);
	}

	@DeleteMapping("/faculties/{facultyId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public String deleteFacultyById(@PathVariable int facultyId) {
		Faculty c = facultyService.getFaculty(facultyId);
		if(c == null ) {
			throw new FacultyNotFoundException("Faculty ID "+facultyId+" not found");
		}
		facultyService.deleteFaculty(facultyId);
		return "Faculty ID "+facultyId+" successfully deleted";
	}
}
