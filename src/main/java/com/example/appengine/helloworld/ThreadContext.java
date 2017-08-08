package com.example.appengine.helloworld;


public class ThreadContext {
	
	private static ThreadLocal<String> threadLocalUser = new ThreadLocal<String>();

	public static String getTenantId() {
		return (String) threadLocalUser.get();
	}
	
	public static void setTenantId(String tenantId) {
		if (tenantId == null || tenantId.equals("")) {
			threadLocalUser.remove();
		}
		threadLocalUser.set(tenantId);
	}
}