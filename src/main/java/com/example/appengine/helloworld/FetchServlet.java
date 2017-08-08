

package com.example.appengine.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class FetchServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

/*		String tenantId = req.getParameter("tenantId");
		Properties properties = new Properties();
		properties.put("datanucleus.TenantID", tenantId);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties, "Demo");*/
		PersistenceManager pm = MultiTenancyProviderService.getPersistenceManager();;
		//Student student = null;
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			//out.println("pm.getSupportedProperties():"+pm.getSupportedProperties());
			Query query = pm.newQuery(Student.class); // Will query all from User class. Replace User with your class
			List<Student> students = (List<Student>) query.execute();
			for(Student student : students) {
				out.println(student);
			}
		} finally {
			pm.close();

		}
	}
}

