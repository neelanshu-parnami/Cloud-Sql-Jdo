package com.example.appengine.helloworld;

import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.example.appengine.Entity.Parent;
import com.example.appengine.Entity.Student;



class Dao {
	public void persistStudent(Student student) {
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(student);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public List<Student> fetchStudents() {
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();
		try {
			Query query = pm.newQuery(Student.class); // Will query all from User class. Replace User with your class
			List<Student> students = (List<Student>) query.execute();
			return students;
		} finally {
			pm.close();

		}
	}
	
	public Parent fetchParent(int id) {
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();
		try {
			return pm.getObjectById(Parent.class, id);
		} finally {
			pm.close();
		}
	}
	
	public void persistParent(Parent parent) {
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(parent);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Student fetchStudent(int id) {
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();
		try {
			return pm.getObjectById(Student.class, id);
		} finally {
			pm.close();
		}
	}
}