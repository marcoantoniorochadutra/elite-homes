package com.elitehomes.core.config;

import java.util.List;
import java.util.Locale;

public class LocaleContext {

    public static final Locale ENGLISH = Locale.of("EN", "US");
    public static final Locale PORTUGUESE = Locale.of("PT", "BR");
    public static final List<Locale> AVAILABLE_LOCALE = List.of(ENGLISH, PORTUGUESE);

    private static final ThreadLocal<Locale> CURRENT_LOCALE = ThreadLocal.withInitial(() -> ENGLISH);

    public static void setCurrentLocale(Locale tenant) {
        CURRENT_LOCALE.set(tenant);
    }

    public static Locale getCurrentLocale() {
        return CURRENT_LOCALE.get();
    }

}
