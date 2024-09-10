package com.elitehomes.web.endpoint;

import com.elitehomes.core.auth.Authentication;
import com.elitehomes.domain.entity.Address;
import com.elitehomes.domain.entity.Owner;
import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyType;
import com.elitehomes.domain.repository.OwnerRepository;
import com.elitehomes.domain.repository.PropertyRepository;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.random.RandomGenerator;

@Path("/v1/dev")
@Authentication
public class DevController {

    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public DevController(PropertyRepository propertyRepository,
                         OwnerRepository ownerRepository) {
        this.propertyRepository = propertyRepository;
        this.ownerRepository = ownerRepository;
    }

    @POST
    @Path("/create")
    public String create() {
        Owner ow = ownerRepository.saveAndFlush(Owner.builder()
                        .withName("test")
                        .withEmail("email@hotmail.com")
                        .withPhone("Phone")
                        .withNationalRegistry("126057169651")
                        .withCountry("br")
                        .withState("SP")
                        .withCity("City")
                        .withCreatedAt(Instant.now())
                .build());

        Property prop = Property.builder()
                .withTitle("test " + RandomStringUtils.randomAlphabetic(10))
                .withNumBedroom(RandomGenerator.getDefault().nextInt(0, 5))
                .withNumBathroom(RandomGenerator.getDefault().nextInt(0, 5))
                .withNumSuite(RandomGenerator.getDefault().nextInt(0, 5))
                .withParkingSpaces(RandomGenerator.getDefault().nextInt(0, 5))
                .withGoal(PropertyGoal.RENT)
                .withType(PropertyType.APARTMENT)
                .withCreatedAt(Instant.now())
                .withValue(RandomGenerator.getDefault().nextDouble(0, 3000.0))
                .withDescription(RandomStringUtils.randomAlphabetic(100))
                .withValueDescription("DEF")
                .withAddress(Address.builder()
                        .withCountry("br")
                        .withState("SP")
                        .withCity("City")
                        .withStreet("Street")
                        .withNumber("10")
                        .withComplement("Complement")
                        .withZipCode("123456")
                        .withNeighborhood("Neighborhood")
                        .withDescription("Address")
                        .build())
                .withOwner(ow)
                .build();

        Property p = propertyRepository.saveAndFlush(prop);
        System.err.println("Created: " + p);
        return "create";
    }
}
