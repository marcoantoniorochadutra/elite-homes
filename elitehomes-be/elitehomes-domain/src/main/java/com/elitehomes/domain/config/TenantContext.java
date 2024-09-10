package com.elitehomes.domain.config;

public class TenantContext {

	public static final String ROOT = "elite_homes_root";

    private static final ThreadLocal<String> currentTenant = ThreadLocal.withInitial(() -> ROOT);

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void setRootCurrentTenant() {
        currentTenant.set(ROOT);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }


}
