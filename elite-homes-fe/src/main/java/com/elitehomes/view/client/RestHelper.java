package com.elitehomes.view.client;

import com.elitehomes.view.entity.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestHelper {

    private final RestTemplate restTemplate;

    @Autowired
    public RestHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected ResponseEntity<String> get(String URL, LoginDto login) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, getHeader(login), String.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("TODO remove this exception");
        }
    }

    private HttpEntity<?> getHeader(LoginDto login) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("tenant", login.getRealEstateKey());
        return new HttpEntity<>(requestHeaders);
    }
}
