

package com.example.appengine.helloworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.Entity.Parent;
import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class CreateServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		storeStudent("DummyStudentFirstName", "DummyStudentLastName", "DummyParentFirstName", "DummyParentLastName", 333);
		storeStudent("DummyStudentFirstName1", "DummyStudentLastName1", "DummyParentFirstName1", "DummyParentLastName1", 555);
		
	}
	
	public void storeStudent(String firstName, String lastName, String parentFirstName, String parentLastName, int tanentId){

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Demo");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.setUserObject(tanentId);

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);

		Parent parent = new Parent();
		parent.setFirstName(parentFirstName);
		parent.setLastName(parentLastName);
		List<Parent> parents = new ArrayList<Parent>();
		parents.add(parent);
		student.setParents(parents);

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
}

