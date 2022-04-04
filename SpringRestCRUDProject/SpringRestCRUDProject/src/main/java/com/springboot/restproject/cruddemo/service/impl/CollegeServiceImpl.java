package com.springboot.restproject.cruddemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.restproject.cruddemo.dao.impl.CollegeRespositoryImpl;
import com.springboot.restproject.cruddemo.entity.College;
import com.springboot.restproject.cruddemo.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService{

	private CollegeRespositoryImpl collegeDAO;
	
	@Autowired
	public CollegeServiceImpl(CollegeRespositoryImpl collegeDAO) {
		this.collegeDAO = collegeDAO;
	}

	@Override
	public List<College> getColleges() throws Exception {
		return collegeDAO.getColleges();
	}

	@Override
	public College saveCollege(College c) throws Exception {
		return collegeDAO.saveCollege(c);
	}

	@Override
	public College getCollegeById(int theId) throws Exception {
		College c = collegeDAO.getCollegeById(theId);
		if(c == null) {
			throw new RuntimeException("College Id "+theId+" not found");
		}
		return c;
	}
	
	@Override
	public College getCollegeByName(String name) throws Exception{
		College c = collegeDAO.getCollegeByName(name);
		return c;
	}
	
	@Override
	public College updateCollege(String oldName, String newName) throws Exception {
		College c = collegeDAO.updateCollege(oldName, newName);
		return c;
	}

	@Override
	public void deleteCollege(int theId) throws Exception {
		collegeDAO.deleteCollege(theId);
	}

}
