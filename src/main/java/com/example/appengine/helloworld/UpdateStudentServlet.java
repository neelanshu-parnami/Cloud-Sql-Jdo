

package com.example.appengine.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class UpdateStudentServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		/*String tenantId = req.getParameter("tenantId");
		Properties properties = new Properties();
		properties.put("datanucleus.TenantID", tenantId);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties, "Demo");
		
		PersistenceManager pm = pmf.getPersistenceManager();*/
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		Student student = null;
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			student = pm.getObjectById(Student.class, idInt);
			out.println("before>>>"+student);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			pm.makePersistent(student);
			student = pm.getObjectById(Student.class,idInt);
			out.println("after>>>"+student);
		} finally {
			pm.close();
		}
	}
}

