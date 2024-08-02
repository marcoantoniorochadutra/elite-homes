package com.elitehomes.support;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.auth.LoginOrigin;
import com.elitehomes.core.utils.PasswordUtils;
import com.elitehomes.domain.entity.User;
import com.elitehomes.domain.entity.UserDetails;
import com.elitehomes.domain.repository.RealEstateRepository;
import com.elitehomes.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.elitehomes.domain.test.support.AbstractTestDbSetup;
import com.elitehomes.service.config.ServiceConfig;
import org.springframework.test.context.jdbc.Sql;

import java.util.Locale;


@ContextConfiguration(classes = { ServiceConfig.class })
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(scripts = {"classpath:sql/test-data.sql"})
public class AbstractTestSupport extends AbstractTestDbSetup {

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected ModelMapper modelMapper;

	@Autowired
	protected UserRepository userRepository;
	@Autowired
	protected RealEstateRepository realEstateRepository;

	public LoginOrigin generateOrigin() {
		return new LoginOrigin("192.168.0.0", "PostmanRuntime/7.36.3", "0:0:0:0:0:0:0:1");
	}

	public LoginDto loginDefault() {
		return generateLoginDto("Mock User", "mockuser@bank.com", "en", "account_vdmhMSHCZhOOj8uR2TMb");
	}

	public LoginDto generateLoginDto(String name, String email, String locale, String account) {
		LoginDto login = new LoginDto();
		login.setEmail(email);
		login.setName(name);
		login.setLocale(locale);
		return login;
	}

	public User userDefault() {
		return userRepository.findById(1L).orElse(null);
	}

	protected User createUser(String name, String email, String password) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setHashPass(PasswordUtils.encodeMd5(password));

		UserDetails details = new UserDetails(true, new Locale("en"));
		user.setUserDetails(details);

		return userRepository.saveAndFlush(user);
	}




}
