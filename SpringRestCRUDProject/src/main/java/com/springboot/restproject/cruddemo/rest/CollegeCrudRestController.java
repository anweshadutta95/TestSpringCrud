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
import com.springboot.restproject.cruddemo.entity.College;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.service.CollegeService;

@RestController
@RequestMapping("/bootapi")
public class CollegeCrudRestController {
	
	@Autowired
	private CollegeService collegeService;

	@GetMapping("/colleges")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<College> getAllColleges() {
		return collegeService.getColleges();
	}
	
	@GetMapping("/colleges/id/{collegeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public College getCollegeById(@PathVariable int collegeId) {
		College c = collegeService.getCollegeById(collegeId);
		if(c == null ) {
			throw new CollegeNotFoundException("College ID "+collegeId+" not found");
		}
		return c;
	}
	
	@GetMapping("/colleges/name/{collegeName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public College getCollegeById(@PathVariable String collegeName) {
		College c = collegeService.getCollegeByName(collegeName);
		if(c == null ) {
			throw new CollegeNotFoundException("Colleges with Name "+collegeName+" not found");
		}
		return c;
	}
	
	@PostMapping("/colleges")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public College saveCollege(@RequestBody College college) {
		college.setId(0);
		return collegeService.saveCollege(college);
	}
	
	@PutMapping("/colleges/{oldName}/{newName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public College updateCollege(@PathVariable String oldName, @PathVariable String newName) {
		College c = collegeService.getCollegeByName(oldName);
		if(c == null) {
			throw new CollegeNotFoundException("Colleges with Name "+oldName+" not found");
		}
		return collegeService.updateCollege(oldName, newName);
	}
	
	@DeleteMapping("/colleges/{collegeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public String deleteCollegeById(@PathVariable int collegeId) {
		College c = collegeService.getCollegeById(collegeId);
		if(c == null ) {
			throw new CollegeNotFoundException("College ID "+collegeId+" not found");
		}
		collegeService.deleteCollege(collegeId);
		return "College ID "+collegeId+" successfully deleted";
	}
}
