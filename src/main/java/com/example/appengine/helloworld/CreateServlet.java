

package com.example.appengine.helloworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;

import com.example.appengine.Entity.Parent;
import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class CreateServlet extends HttpServlet {

	public static final String PROPERTY_TENANT_ID = "datanucleus.TenantID";
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		List<Parent> parents = new ArrayList<Parent>();

		Parent parent = new Parent();
		parent.setFirstName("DummyParentFirstName");
		parent.setLastName("DummyParentLastName");
		parents.add(parent);

		Parent parent1 = new Parent();
		parent1.setFirstName("DummyParentFirstName1");
		parent1.setLastName("DummyParentLastName1");
		parents.add(parent1);
		
		storeStudent("DummyStudentFirstName", "DummyStudentLastName", parents, "123");

		parents = new ArrayList<Parent>();

		Parent parent2 = new Parent();
		parent2.setFirstName("DummyParentFirstName2");
		parent2.setLastName("DummyParentLastName2");
		parents.add(parent2);

		Parent parent3 = new Parent();
		parent3.setFirstName("DummyParentFirstName3");
		parent3.setLastName("DummyParentLastName3");
		parents.add(parent3);
		
		storeStudent("DummyStudentFirstName1", "DummyStudentLastName1", parents, "456");
		
		parents = new ArrayList<Parent>();

		Parent parent4 = new Parent();
		parent4.setFirstName("DummyParentFirstName4");
		parent4.setLastName("DummyParentLastName4");
		parents.add(parent4);

		Parent parent5 = new Parent();
		parent5.setFirstName("DummyParentFirstName5");
		parent5.setLastName("DummyParentLastName5");
		parents.add(parent5);
		
		storeStudent("DummyStudentFirstName2", "DummyStudentLastName2", parents, "123");

		parents = new ArrayList<Parent>();

		Parent parent6 = new Parent();
		parent6.setFirstName("DummyParentFirstName6");
		parent6.setLastName("DummyParentLastName6");
		parents.add(parent6);

		Parent parent7 = new Parent();
		parent7.setFirstName("DummyParentFirstName7");
		parent7.setLastName("DummyParentLastName7");
		parents.add(parent7);
		storeStudent("DummyStudentFirstName3", "DummyStudentLastName3", parents, "789");
		
		
	}
	
	public void storeStudent(String firstName, String lastName, List<Parent> parents, String tenantId){

/*		PersistenceUnitMetaData pumd = new PersistenceUnitMetaData("dynamic-unit", "RESOURCE_LOCAL", null);
		pumd.addProperty("datanucleus.PersistenceUnitName", "Demo"+tenantId);
		pumd.addProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
		pumd.addProperty("javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.GoogleDriver");
		pumd.addProperty("javax.jdo.option.ConnectionURL", "jdbc:google:mysql://metacampus-in:us-central1:test/guestbook");
		pumd.addProperty("javax.jdo.option.ConnectionUserName", "root");
		pumd.addProperty("datanucleus.autoCreateSchema", "true");
		pumd.addProperty("javax.jdo.option.ConnectionPassword", "ciitdc#123");
		pumd.addProperty("javax.jdo.option.NontransactionalRead", "true");
		pumd.addProperty("javax.jdo.option.NontransactionalWrite", "true");
		pumd.addProperty("javax.jdo.option.Multithreaded", "true");
		pumd.addProperty("javax.jdo.option.RetainValues", "true");
		pumd.addProperty("datanucleus.singletonPMFForName", "true");
		pumd.addProperty("datanucleus.cache.level2.type", "none");
		pumd.addProperty("datanucleus.TenantID", tenantId);
		
		PersistenceManagerFactory pmf = new JDOPersistenceManagerFactory(pumd, null);
*/		
		Properties properties = new Properties();	
/*		properties.put("datanucleus.PersistenceUnitName", "Demo"+tanentId);
		properties.put("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
		properties.put("javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.GoogleDriver");
		properties.put("javax.jdo.option.ConnectionURL", "jdbc:google:mysql://metacampus-in:us-central1:test/guestbook");
		properties.put("javax.jdo.option.ConnectionUserName", "root");
		properties.put("datanucleus.autoCreateSchema", "true");
		properties.put("javax.jdo.option.ConnectionPassword", "ciitdc#123");
		properties.put("javax.jdo.option.NontransactionalRead", "true");
		properties.put("javax.jdo.option.NontransactionalWrite", "true");
		properties.put("javax.jdo.option.Multithreaded", "true");
		properties.put("javax.jdo.option.RetainValues", "true");
		properties.put("datanucleus.singletonPMFForName", "true");
		properties.put("datanucleus.cache.level2.type", "none");*/
		properties.put("datanucleus.TenantID", tenantId);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties, "Metacampus"+tenantId);
		System.out.println("PMF Object ID:"+pmf);
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		//pm.setUserObject(tanentId);

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);

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

