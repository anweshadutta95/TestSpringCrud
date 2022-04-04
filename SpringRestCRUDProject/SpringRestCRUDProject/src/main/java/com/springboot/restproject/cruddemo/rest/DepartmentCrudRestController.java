package com.springboot.restproject.cruddemo.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.restproject.cruddemo.entity.Department;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.service.DepartmentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bootapi")
public class DepartmentCrudRestController {
	
	@Autowired
	private DepartmentService departmentService;

	@ApiOperation(value = "This method is used to get all departments by college name.")
	@GetMapping("/departments/{collegeName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<Department> getAllDepartments(@PathVariable String collegeName) throws Exception {
		return departmentService.getDepartments(collegeName);
	}
	
	@ApiOperation(value = "This method is used to get department by id.")
	@GetMapping("/departments/id/{departmentId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Department getDepartmentById(@PathVariable int departmentId) throws DepartmentNotFoundException,Exception {
		Department d = departmentService.getDepartment(departmentId);
		return d;
	}
	
	@ApiOperation(value = "This method is used to add a department to a college.")
	@PostMapping("/departments/{collegeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Department saveDepartment(@RequestBody Department department, @PathVariable int collegeId) throws Exception {
		department.setId(0);
		return departmentService.saveDepartment(department, collegeId);
	}
	
	/*@PutMapping("/departments")
	public Department updateDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}
	
	@DeleteMapping("/departments/{departmentId}")
	public String deleteDepartmentById(@PathVariable int departmentId) {
		Department c = departmentService.getDepartment(departmentId);
		if(c == null ) {
			throw new DepartmentNotFoundException("Department ID "+departmentId+" not found");
		}
		departmentService.deleteDepartment(departmentId);
		return "Department ID "+departmentId+" successfully deleted";
	}*/
}
