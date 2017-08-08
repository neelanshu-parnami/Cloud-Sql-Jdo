

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
	Dao dao = new Dao();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String tenantId = req.getParameter("tenantId");
		ThreadContext.setTenantId(tenantId);
		
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			List<Student> students = dao.fetchStudents();
			for(Student student : students) {
				out.println(student);
			}
	}
}

