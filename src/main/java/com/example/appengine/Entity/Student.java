package com.example.appengine.Entity;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.Extensions;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
//import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.listener.StoreCallback;
import javax.jdo.spi.StateManager;


@PersistenceCapable
/*@Extensions({
	@Extension(key = "multitenancy-column-name", value = "TENANT", vendorName = "datanucleus"),
	@Extension(key = "multitenancy-column-length", value = "24", vendorName = "datanucleus")	
})*/
public class Student {
      
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private int studentId;
	
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;

	
	@Persistent
	@Element(dependent = "true")
	private List<Parent> parents;
	
	
	/*@Persistent
	private int tenantId;
	
	public int getTenantId() {
		return tenantId;
	}*/
/*
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
*/
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public List<Parent> getParents() {
		return parents;
	}

	public void setParents(List<Parent> parents) {
		this.parents = parents;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + " , firstName=" + firstName
				+ ", lastName=" + lastName + ", parent=" + parents.toString() + "]";
	}


/*	@Override
	public void jdoPreStore() {
		 PersistenceManager pm = JDOHelper.getPersistenceManager (this);
		 this.tenantId = (Integer)pm.getUserObject();		
	}
*/	
}
