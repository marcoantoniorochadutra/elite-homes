package com.elitehomes.view.client;

import com.elitehomes.view.entity.LoginDto;
import com.elitehomes.view.entity.PropertyResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
public class PropertyClient extends RestHelper {

    private static final String HOST = "http://localhost:8070/elitehomes/api/";
    private static final String GET_PROPERTIES_URL = HOST + "v1/property";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public PropertyClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate);
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<PropertyResultDto> searchProperties(LoginDto login) {
        ResponseEntity<String> response = get(GET_PROPERTIES_URL, login);
        var typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, PropertyResultDto.class);
        try {
            return objectMapper.readValue(response.getBody().getBytes(), typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
