package com.springboot.restproject.cruddemo.service;

import java.util.List;
import com.springboot.restproject.cruddemo.entity.College;

public interface CollegeService {
	
	public List<College> getColleges();

	public College saveCollege(College c);

	public College getCollegeById(int theId);
	
	public College getCollegeByName(String name);
	
	public College updateCollege(String oldName, String newName);

	public void deleteCollege(int theId);

}
