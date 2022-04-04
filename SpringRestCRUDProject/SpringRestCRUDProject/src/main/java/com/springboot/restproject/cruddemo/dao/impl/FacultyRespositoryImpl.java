package com.springboot.restproject.cruddemo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.restproject.cruddemo.dao.FacultyRespository;
import com.springboot.restproject.cruddemo.entity.College;
import com.springboot.restproject.cruddemo.entity.Department;
import com.springboot.restproject.cruddemo.entity.Faculty;
import com.springboot.restproject.cruddemo.exception.CollegeNotFoundException;
import com.springboot.restproject.cruddemo.exception.DepartmentNotFoundException;
import com.springboot.restproject.cruddemo.exception.FacultyNotFoundException;

@Repository
public class FacultyRespositoryImpl implements FacultyRespository{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CollegeRespositoryImpl collegeDAO;
	
	@Override
	public List<Faculty> getFaculties(String collegeName) throws CollegeNotFoundException,Exception {
		
		College c = collegeDAO.getCollegeByName(collegeName);
		if(c == null) {
			throw new CollegeNotFoundException("College Name "+collegeName+" not found");
		}

		Set<Faculty> faculties = c.getFaculties();
		//return the list of departments;
		return faculties.stream().collect(Collectors.toList());
	}

	@Override
	public Faculty saveFaculty(Faculty f, int collegeId) throws CollegeNotFoundException,Exception {
		
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		College c = (College) s.get(College.class, collegeId);
		if(c == null) {
			throw new CollegeNotFoundException("College Id "+collegeId+" not found");
		}
		c.addFaculties(f);
		//save the department
		s.saveOrUpdate(c);
		s.getTransaction().commit();
		s.close();
		return f;
	}
	
	@Override
	public Faculty getFaculty(int theId) throws FacultyNotFoundException,Exception {
		Session s = sessionFactory.openSession();
		
		Faculty f = (Faculty) s.get(Faculty.class, theId);
		if(f == null) {
			throw new FacultyNotFoundException("Faculty Id "+theId+" not found");
		}
		return f;
	}

	@Override
	public Faculty assignFaculty(String facultyName, int deptId) throws DepartmentNotFoundException, FacultyNotFoundException, Exception {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Department d = (Department) s.get(Department.class, deptId);
		if(d == null) {
			throw new DepartmentNotFoundException("Department Id "+deptId+" not found");
		}
		//get faculty by name
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Faculty> q = cb.createQuery(Faculty.class);
		Root<Faculty> faculty = q.from(Faculty.class);
		
		List<Predicate> pr = new ArrayList<Predicate>();
		pr.add(cb.equal(faculty.get("firstName"), facultyName));
		
		q.where(pr.toArray(new Predicate[0]));
		
		Faculty f = s.createQuery(q).getSingleResult();
		
		if(f == null) {
			throw new FacultyNotFoundException("Faculty Name "+facultyName+" not found");
		}
		d.addFaculties(f);
		//save the department
		s.saveOrUpdate(d);
		s.getTransaction().commit();
		s.close();
		return f;
	}
	
	@Override
	public Faculty assignHod(String facultyName, int deptId) throws DepartmentNotFoundException,Exception {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Department d = (Department) s.get(Department.class, deptId);
		if(d == null) {
			throw new DepartmentNotFoundException("Department Id "+deptId+" not found");
		}
		//get faculty by name from dept obj
		Faculty f = new Faculty();
		for(Faculty fac : d.getFaculties()) {
			if(fac.getFirstName().equalsIgnoreCase(facultyName)) {
				f = fac;
				break;
			}
		}
		
		f.setRole("HOD");
		//save the department
		s.saveOrUpdate(f);
		s.getTransaction().commit();
		s.close();
		return f;
	}
	
	@Override
	public Faculty getHod(int deptId) throws DepartmentNotFoundException,FacultyNotFoundException,Exception {
		System.out.println("Inside getHod method");
		Session s = sessionFactory.openSession();
		Department d = (Department) s.get(Department.class, deptId);
		if(d == null) {
			throw new DepartmentNotFoundException("Department Id "+deptId+" not found");
		}
		System.out.println("Department = "+d.toString());
		//get faculty by name from dept obj
		Faculty f = null;
		for(Faculty fac : d.getFaculties()) {
			System.out.println("Faculty = "+fac.toString());
			if(fac.getRole()!=null && fac.getRole().equalsIgnoreCase("HOD")) {
				System.out.println("Inside hod if block = "+fac.getFirstName());
				f = fac;
				break;
			}
		}
		if(f == null) {
			throw new FacultyNotFoundException("No HOD found for Dept Id "+deptId+"");
		}
		s.close();
		return f;
	}

	@Override
	public List<Faculty> getFacultyByDepartment(String collegeName, String deptName) throws DepartmentNotFoundException, Exception {
		
		//get dept by name
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		College c = collegeDAO.getCollegeByName(collegeName);
		//s.update(c);//not required here - to bind this object to this session
		Department d = null;
		for(Department dep : c.getDepartments()) {
			if(dep.getName().equalsIgnoreCase(deptName)) {
				d = dep;
				break;
			}
		}
		//OPTION 2 : get directly from dept name - will cause ambiguity
		//create criteria query
		/*CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Department> cq = cb.createQuery(Department.class);
		Root<Department> dept = cq.from(Department.class);
		
		List<Predicate> pr = new ArrayList<Predicate>();
		pr.add(cb.equal(dept.get("name"), deptName));
		pr.add(cb.equal(dept.get("college"), deptName));
		
		cq.where(pr.toArray(new Predicate[0]));
		
		Department d = s.createQuery(cq).getSingleResult();
		if(d == null) {
			throw new RuntimeException("Department "+deptName+" not found");
		}
		*/
		if(d == null) {
			throw new DepartmentNotFoundException("Department "+deptName+" not found");
		}
		Set<Faculty> facultyList = d.getFaculties();
		if(facultyList == null || facultyList.isEmpty()) {
			throw new Exception("No faculty assigned to Department "+deptName+"");
		}
		s.getTransaction().commit();
		s.close();
		return facultyList.stream().collect(Collectors.toList());
	}

	@Override
	public void deleteFaculty(int theId) throws Exception {
		Session s = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query<Faculty> q = s.createQuery("delete from Faculty where id:=facultyId");
		q.setParameter("facultyId", theId);
		q.executeUpdate();
		s.close();
	}
	
	

}
