

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

import com.example.appengine.Entity.Parent;
import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class AddParentServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Demo");
		PersistenceManager pm = pmf.getPersistenceManager();
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		Student student = null;
		Parent parent = new Parent();
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			student = pm.getObjectById(Student.class, idInt);
			out.println("before>>>"+student);
			parent.setFirstName(firstName);
			parent.setLastName(lastName);
			student.getParents().add(parent);
			pm.makePersistent(student);
			student = pm.getObjectById(Student.class,idInt);
			out.println("after>>>"+student);
		} finally {
			pm.close();
		}
	}
}

