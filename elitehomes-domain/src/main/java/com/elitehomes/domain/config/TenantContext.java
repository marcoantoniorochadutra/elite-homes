package com.elitehomes.domain.config;

public class TenantContext {

	private static final String SELSYN_ROOT = "elite_homes_root";

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void setRootCurrentTenant() {
        currentTenant.set(SELSYN_ROOT);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }


}
