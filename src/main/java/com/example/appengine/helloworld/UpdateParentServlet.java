

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

import com.example.appengine.Entity.Parent;

@SuppressWarnings("serial")
public class UpdateParentServlet extends HttpServlet {

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
		PersistenceManager pm = pmf.getPersistenceManager();
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		Parent parent = null;
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			parent = pm.getObjectById(Parent.class, idInt);
			out.println("before>>>"+parent);
			parent.setFirstName(firstName);
			parent.setLastName(lastName);
			pm.makePersistent(parent);
			parent = pm.getObjectById(Parent.class,idInt);
			out.println("after>>>"+parent);
		} finally {
			pm.close();
		}
	}
}

