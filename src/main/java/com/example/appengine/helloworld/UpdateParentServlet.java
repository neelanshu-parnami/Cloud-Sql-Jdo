

package com.example.appengine.helloworld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.Entity.Parent;

@SuppressWarnings("serial")
public class UpdateParentServlet extends HttpServlet {
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
		Parent parent = null;

			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			
			parent = dao.fetchParent(idInt);
			
			out.println("before>>>"+parent);
			
			parent.setFirstName(firstName);
			parent.setLastName(lastName);
			
			dao.persistParent(parent);
			
			parent = dao.fetchParent(idInt);
			
			out.println("after>>>"+parent);

	}
}

