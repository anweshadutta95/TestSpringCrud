package com.springboot.restproject.cruddemo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.restproject.cruddemo.dao.CollegeRespository;
import com.springboot.restproject.cruddemo.entity.College;

@Repository
public class CollegeRespositoryImpl implements CollegeRespository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<College> getColleges() {
		
		//SessionFactory sessionFactory = getSessionFactory();
		//get the current session
		Session session = sessionFactory.openSession();
		
		Transaction t = session.beginTransaction();
		//create the query
		Query<College> query = session.createQuery("from College", College.class);
		
		//execute the query and retrieve the list of colleges
		List<College> colleges = query.getResultList();
		
		t.commit();
		//return the list of colleges;
		return colleges;
	}

	@Override
	public College saveCollege(College c) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		//get current HB session
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		//save the college
		s.saveOrUpdate(c);
		t.commit();
		s.close();
		return c;
	}

	@Override
	public College getCollegeById(int theId) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		College c = (College) s.get(College.class, theId);
		t.commit();
		s.close();
		//sessionFactory.close();
		return c;
	}
	
	@Override
	public College getCollegeByName(String name) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.openSession();
		//Transaction t = s.beginTransaction();
		//College c = (College) s.get(College.class, theId);
		TypedQuery<College> q = s.createNamedQuery("findCollegeByName", College.class);
		q.setParameter("name", name);
		College cl = q.getSingleResult();
		//t.commit();
		return cl;
	}

	@Override
	public void deleteCollege(int theId) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		//OPTION 1: get the college and delete - 2 db operations
		//College c = (College) s.get(College.class, theId);
		//s.delete(c);
		//OPTION 2: HQL - 1 db operation
		@SuppressWarnings("unchecked")
		Query<College> q = s.createQuery("delete from College c where c.id=:collegeId");
		q.setParameter("collegeId", theId);
		q.executeUpdate();
		t.commit();
	}

	public College updateCollege(String oldName, String newName) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		//College c = (College) s.get(College.class, theId);
		Query<College> q = s.createQuery("update College c set c.name = :newName where c.name = :oldName");
		q.setParameter("oldName", oldName);
		q.setParameter("newName", newName);
		q.executeUpdate();
		t.commit();
		College cl = getCollegeByName(newName);
		return cl;
	}

}
