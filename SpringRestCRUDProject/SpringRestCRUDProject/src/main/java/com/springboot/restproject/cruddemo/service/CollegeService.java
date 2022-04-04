package com.springboot.restproject.cruddemo.service;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.College;

public interface CollegeService {
	
	public List<College> getColleges() throws Exception;

	public College saveCollege(College c) throws Exception;

	public College getCollegeById(int theId) throws Exception;
	
	public College getCollegeByName(String name) throws Exception;
	
	public College updateCollege(String oldName, String newName) throws Exception;

	public void deleteCollege(int theId) throws Exception;

}
