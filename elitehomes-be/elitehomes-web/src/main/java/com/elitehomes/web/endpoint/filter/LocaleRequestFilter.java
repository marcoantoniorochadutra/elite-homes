package com.elitehomes.web.endpoint.filter;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.config.LocaleContext;
import com.elitehomes.domain.config.TenantContext;
import com.elitehomes.web.authentication.LoginFactory;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Objects;

// TODO FILTRO DEPOIS DE ADICIONAR NO CONTEXTO AS INFOS DE LOGIN E IF LOGIN É TRUE POPULAR COM LOCALE DO LOGIN ELSE PEGAR DO HEADER
@Provider
@Priority(Priorities.USER)
public class LocaleRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String ten = requestContext.getHeaders().getFirst("tenant");
        System.err.println(ten);
        TenantContext.setCurrentTenant(ten);
        System.err.println("setting ten: " + ten);

        LoginDto login = (LoginDto) requestContext.getProperty(LoginFactory.LOGIN_PROPERTY);
		if (Objects.nonNull(login)) {
            Locale locale = new Locale(login.getLocale());
            LocaleContext.setCurrentLocale(locale);
		} else {
            String localeStr = requestContext.getHeaders().getFirst("Content-Language");
            if (StringUtils.isNotEmpty(localeStr)) {
                Locale locale = new Locale(localeStr);
                LocaleContext.setCurrentLocale(locale);
            }
        }
    }

}
