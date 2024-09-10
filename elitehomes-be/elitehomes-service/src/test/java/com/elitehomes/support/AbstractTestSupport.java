package com.elitehomes.support;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.auth.LoginOrigin;
import com.elitehomes.core.utils.PasswordUtils;
import com.elitehomes.domain.entity.Address;
import com.elitehomes.domain.entity.Contact;
import com.elitehomes.domain.entity.Owner;
import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.entity.RealEstate;
import com.elitehomes.domain.entity.User;
import com.elitehomes.domain.entity.UserDetails;
import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyType;
import com.elitehomes.domain.repository.OwnerRepository;
import com.elitehomes.domain.repository.PropertyRepository;
import com.elitehomes.domain.repository.RealEstateRepository;
import com.elitehomes.domain.repository.UserRepository;
import com.elitehomes.model.base.SelectableDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.elitehomes.domain.test.support.AbstractTestDbSetup;
import com.elitehomes.service.config.ServiceConfig;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = { ServiceConfig.class })
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(scripts = {"classpath:sql/test-data.sql"})
public class AbstractTestSupport extends AbstractTestDbSetup {

	private static final Long DEFAULT_ENTITY_ID = 1L;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected Mapper modelMapper;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected RealEstateRepository realEstateRepository;

	@Autowired
	protected OwnerRepository ownerRepository;

	@Autowired
	protected PropertyRepository propertyRepository;

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
		return userRepository.getReferenceById(DEFAULT_ENTITY_ID);
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

	protected Owner defaultOwner() {
		return ownerRepository.getReferenceById(DEFAULT_ENTITY_ID);
	}

	protected Address defaultAddress() {
		return Address.builder()
				.withCountry("Brasil")
				.withState("Santa Catarina")
				.withCity("Imbituba")
				.withNeighborhood("Centro")
				.withStreet("Rua Santos")
				.withNumber("123")
				.withZipCode("88780-000")
				.withComplement("Apartamento 101")
				.withDescription("Perto da praia")
				.build();

	}

	protected Contact defaultContato() {
		return Contact.builder()
				.withEmail("mockEmail")
				.withPhone("mockPhone")
				.withCellphone("mockCellphone")
				.build();
	}

	protected RealEstate buildRealEstate(String regNumber, String creci, String tenantKey) {
		RealEstate rs =  RealEstate.builder()
				.withCreatedAt(Instant.now())
				.withName("Real Estate " + regNumber)
				.withTenantKey(tenantKey)
				.withCompanyRegNumber(regNumber)
				.withCreci(creci)
				.withAddress(defaultAddress())
				.withContact(defaultContato())
				.build();

		return realEstateRepository.saveAndFlush(rs);
	}

	protected Property buildProperty(String description, PropertyGoal goal, PropertyType type) {
		Property property = Property.builder()
				.withCreatedAt(Instant.now())
				.withNumBedroom(2)
				.withNumBathroom(2)
				.withNumSuite(1)
				.withParkingSpaces(2)
				.withGoal(goal)
				.withType(type)
				.withOwner(defaultOwner())
				.withValue(2500.0)
				.withDescription(description)
				.withValueDescription("value description")
				.withAddress(defaultAddress())
				.build();

		return propertyRepository.saveAndFlush(property);
	}

	protected void assertSelectable(String key, SelectableDto selectable) {
		assertEquals(key, selectable.getKey());
	}

}
