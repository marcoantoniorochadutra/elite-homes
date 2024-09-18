package com.elitehomes.view.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ComponentScan({"com.elitehomes.view.context"})
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateNoHeaders(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplateNoHeaders = new RestTemplate(clientHttpRequestFactory);
        List<ClientHttpRequestInterceptor> noHeadersInterceptors = new ArrayList<>();
        restTemplateNoHeaders.setInterceptors(noHeadersInterceptors);
        return restTemplateNoHeaders;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        return new BufferingClientHttpRequestFactory(requestFactory);
    }

}
