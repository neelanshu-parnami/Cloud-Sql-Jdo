

package com.example.appengine.helloworld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
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

		String studentId = req.getParameter("id");
		int studentIdInt = Integer.parseInt(studentId);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Demo");
		PersistenceManager pm = pmf.getPersistenceManager();
		Student student = null;
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			student = pm.getObjectById(Student.class, studentIdInt);
			out.println(student);
		} finally {
			pm.close();

		}
	}
}

