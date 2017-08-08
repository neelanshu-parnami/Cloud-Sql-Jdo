package com.example.appengine.helloworld;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

class MultiTenancyProviderService {
	public static PersistenceManager getPersistenceManager() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Metacampus");
		System.out.println("PMF Object ID:"+pmf);
		PersistenceManager pm = pmf.getPersistenceManager();
		//tenantId
		String tenantTd = ThreadContext.getTenantId();
		pm.setProperty("datanucleus.TenantID", tenantTd);
		return pm;
	}
}