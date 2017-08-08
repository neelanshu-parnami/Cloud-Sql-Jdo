

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
	Dao dao = new Dao();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {
		String tenantId = req.getParameter("tenantId");
		ThreadContext.setTenantId(tenantId);
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		Student student = null;
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		
		student = dao.fetchStudent(idInt);
		
		out.println("before>>>"+student);
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		
		dao.persistStudent(student);
		
		student = dao.fetchStudent(idInt);
		
		out.println("after>>>"+student);
	}
}

