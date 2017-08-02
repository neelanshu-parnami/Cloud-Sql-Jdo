

package com.example.appengine.helloworld;

import java.io.IOException;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class DeleteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		String tenantId = req.getParameter("tenantId");
		Properties properties = new Properties();
		properties.put("datanucleus.TenantID", tenantId);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties, "Demo");
		
		// Insert a few rows.
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		Student student = null;
		

		try {
			tx.begin();
			student = pm.getObjectById(Student.class, idInt);
			pm.deletePersistent(student);
			tx.commit();
		} finally {
			pm.close();

		}
	}
}

