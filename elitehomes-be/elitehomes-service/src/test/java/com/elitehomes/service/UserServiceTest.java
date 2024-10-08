package com.elitehomes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.elitehomes.core.constants.SystemConstants;
import com.elitehomes.domain.entity.User;
import com.elitehomes.domain.entity.UserDetails;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.elitehomes.core.exception.BusinessException;
import com.elitehomes.support.AbstractTestSupport;

import java.util.Locale;

public class UserServiceTest extends AbstractTestSupport {

	@Autowired
	private UserService accountService;

	@Test
	public void shouldCreateUser() {

		User user = accountService.registerUser("Marco Dutra", "email@hotmail.com", "123456&!#Claudio");

		assertEquals("Marco Dutra", user.getName());
		assertEquals("email@hotmail.com", user.getEmail());

		UserDetails details = user.getUserDetails();
		assertTrue(details.getActive());
		assertEquals(SystemConstants.EN_LOCALE, details.getLocale());
	}

	@Test
	public void shouldNotRegisterUserInvalidEmail() {

		BusinessException ex = assertThrows(BusinessException.class,
				() -> accountService.registerUser("Marco Dutra", "email", "123456&!#Claudio"));

		assertEquals("Invalid information [ email ]", ex.getMessage());
	}

	@Test
	public void shouldNotRegisterUserInvalidNome() {

		BusinessException ex = assertThrows(BusinessException.class,
				() -> accountService.registerUser(null, "email@hotmail.com", "123456&!#Claudio"));

		assertEquals("Field must be filled [ name ]", ex.getMessage());
	}

	@Test
	public void shouldNotRegisterUserInvalidPass() {

		BusinessException ex = assertThrows(BusinessException.class,
				() -> accountService.registerUser("Marco Dutra", "email@hotmail.com", "123456"));

		assertEquals("The password must contain letters and numbers, between 6 and 20 characters, and may include special characters.", ex.getMessage());
	}

}
