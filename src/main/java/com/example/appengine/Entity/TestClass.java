package com.example.appengine.Entity;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable
public class TestClass implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private int entryID;

    @Persistent
    private String firstName;

    @Persistent
    private String lastName;

    public TestClass(String firstName,String lastName){
    	this.firstName = firstName;
    	this.lastName = lastName;
    }
    
	public int getEntryID() {
		return entryID;
	}






	public void setEntryID(int entryID) {
		this.entryID = entryID;
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
    
}