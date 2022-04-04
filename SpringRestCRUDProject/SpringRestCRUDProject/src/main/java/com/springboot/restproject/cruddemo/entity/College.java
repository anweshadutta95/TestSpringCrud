package com.springboot.restproject.cruddemo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries(  
		{  
			@NamedQuery(  
					name = "findCollegeByName",  
					query = "from College c where c.name = :name"  
					),
			@NamedQuery(  
					name = "updateCollegeByName",  
					query = "update College c set c.name = :oldName where c.name = :newName"  
					)  
		}  
		)  
@Entity
@Table(name="college")
public class College {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="city")
	private String city;

	//@JsonIgnore
	//unidirectional one to many
	@OneToMany(fetch=FetchType.EAGER, 
			cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name="college_id")
	private Set<Department> departments;

	//unidirectional one to many
	//@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, 
	cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name="college_id")
	private Set<Faculty> faculties;

	public College() {
		super();
	}

	public College(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public void add(Department d) {
		if(this.departments==null) {
			this.departments = new HashSet<Department>();
		}
		this.departments.add(d);
		//d.setCollege(this);
	}

	public void remove(Department d) {
		this.departments.remove(d);
	}
	
	public void addFaculties(Faculty f) {
		if(this.faculties==null) {
			this.faculties = new HashSet<Faculty>();
		}
		this.faculties.add(f);
	}

	public void removeFaculty(Faculty f) {
		this.faculties.remove(f);
	}

	@Override
	public String toString() {
		return "College [id=" + id + ", name=" + name + "]";
	}

}
