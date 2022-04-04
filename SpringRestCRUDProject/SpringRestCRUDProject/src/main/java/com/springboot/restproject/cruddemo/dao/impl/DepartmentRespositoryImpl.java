package com.springboot.restproject.cruddemo.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.restproject.cruddemo.dao.DepartmentRespository;
import com.springboot.restproject.cruddemo.entity.College;
import com.springboot.restproject.cruddemo.entity.Department;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;

@Repository
public class DepartmentRespositoryImpl implements DepartmentRespository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CollegeRespositoryImpl collegeDAO;
	
	@Override
	public List<Department> getDepartments(String collegeName) throws Exception {
		
		//SessionFactory sessionFactory = getSessionFactory();
		
		
		//IMPLEMENT
		College c = collegeDAO.getCollegeByName(collegeName);
		if(c == null) {
			throw new RuntimeException("College Name "+collegeName+" not found");
		}
		System.out.println("Colleges retrieved = "+c.toString());
		//create the query
		
		//Query<Department> query = session.createNamedQuery("getDepartmentsByCollegeName", Department.class);
		//query.setParameter("collegeName", collegeName);
		//execute the query and retrieve the list of departments
		Set<Department> departments = c.getDepartments();
		
		//return the list of departments;
		return departments.stream().collect(Collectors.toList());
	}

	@Override
	public Department saveDepartment(Department d, int collegeId) throws Exception {
		
		Session s = sessionFactory.openSession();
		//begin transaction
		s.beginTransaction();
		//get the college
		College c = (College) s.get(College.class, collegeId);
		if(c == null) {
			throw new RuntimeException("College Id "+collegeId+" not found");
		}
		System.out.println("College retrieved = "+c.toString());
		//System.out.println("College retrieved existing depts = "+c.getDepartments().get(0).toString());
		//add the i/p dept to retrieved college
		//d.setId(0);
		for (Department dp : c.getDepartments()) {
			System.out.println("Department = "+dp.toString());
		}
		System.out.println("Dept is = "+d.toString());
		c.add(d);
		//System.out.println("College retrieved existing depts after add = "+c.getDepartments().get(0).toString()+" :: "
		//+c.getDepartments().get(1).toString());
		//get session factory
		//SessionFactory sessionFactory = getSessionFactory();
		//IMPLEMENT
		
		//save the department
		s.saveOrUpdate(c);
		s.getTransaction().commit();
		s.close();
		return d;
	}

	@Override
	public Department getDepartment(int theId) throws DepartmentNotFoundException, Exception {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.openSession();
		//IMPLEMENT
		Department d = (Department) s.get(Department.class, theId);
		if(d == null) {
			throw new RuntimeException("Department Id "+theId+" not found");
		}
		s.close();
		return d;
	}

	@Override
	public void deleteDepartment(int theId) throws Exception {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.openSession();
		//OPTION 1: get the department and delete - 2 db operations
		Department c = (Department) s.get(Department.class, theId);
		s.delete(c);
		//OPTION 2: HQL - 1 db operation
		
		//IMPLEMENT
		//@SuppressWarnings("unchecked")
		//Query<Department> q = s.createQuery("delete from Department where id:=departmentId");
		//q.setParameter("departmentId", theId);
		//q.executeUpdate();
		s.close();
	}

}
