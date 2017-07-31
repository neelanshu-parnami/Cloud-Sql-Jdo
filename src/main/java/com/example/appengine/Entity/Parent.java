package com.example.appengine.Entity;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.listener.StoreCallback;


@PersistenceCapable
public class Parent implements StoreCallback {

	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private int parentId;
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;

	@Persistent
	private int tenantId;
	
	
	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	@Override
	public String toString() {
		return "Parent [parentId=" + parentId + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	@Override
	public void jdoPreStore() {
		 PersistenceManager pm = JDOHelper.getPersistenceManager (this);
		 this.tenantId = (Integer)pm.getUserObject();
	}
	
}
