

package com.example.appengine.helloworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.appengine.Entity.Parent;
import com.example.appengine.Entity.Student;

@SuppressWarnings("serial")
public class CreateServlet extends HttpServlet {
	Dao dao = new Dao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
	ServletException {

		String tenantId = req.getParameter("tenantId");
		ThreadContext.setTenantId(tenantId);
		
		List<Parent> parents = new ArrayList<Parent>();

		Parent parent = new Parent();
		parent.setFirstName("FirstParentFirstName"+tenantId);
		parent.setLastName("FirstParentLastName"+tenantId);
		parents.add(parent);

		Parent parent1 = new Parent();
		parent1.setFirstName("SecondParentFirstName"+tenantId);
		parent1.setLastName("SecondParentLastName"+tenantId);
		parents.add(parent1);
		
		storeStudent("StudentFirstName"+tenantId, "StudentLastName"+tenantId, parents);
	}
	
	public void storeStudent(String firstName, String lastName, List<Parent> parents) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setParents(parents);
		
		dao.persistStudent(student);
	}

}

