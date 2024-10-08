package com.elitehomes.web.endpoint.filter;

import java.util.Objects;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.domain.config.TenantContext;
import com.elitehomes.web.authentication.LoginFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.elitehomes.core.auth.Authentication;
import com.elitehomes.core.entity.base.MessageDto;

import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
@Authentication
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Context
	private ResourceInfo resourceInfo;


	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		LoginDto login = (LoginDto) requestContext.getProperty(LoginFactory.LOGIN_PROPERTY);
		Authentication annotation = getAuthenticationAnnotation(resourceInfo);

		String ten = requestContext.getHeaders().getFirst("tenant");
		TenantContext.setCurrentTenant(ten);

	}

	private void throwAuthException(String message) {
		throw new WebApplicationException(
				Response.status(Status.FORBIDDEN)
						.entity(new MessageDto(message))
						.type(MediaType.APPLICATION_JSON)
						.build());
	}

	private boolean verifyToken(Authentication annotation, LoginDto login) {
		boolean allowed = true;
		if(Objects.nonNull(annotation)) {
			allowed = isPlanoAllowedForResource(login, annotation);
		}
		return allowed;
	}


	private boolean isLoginValid(LoginDto login) {
        return Objects.nonNull(login);
    }



	private Authentication getAuthenticationAnnotation(ResourceInfo resourceInfo) {
		Authentication annotation = resourceInfo.getResourceMethod().getAnnotation(Authentication.class);
		if (Objects.isNull(annotation)) {
			annotation = resourceInfo.getResourceClass().getAnnotation(Authentication.class);
		}
		return annotation;
	}

	private boolean isPlanoAllowedForResource(LoginDto login, Authentication annotation) {
		return true;
	}




}

