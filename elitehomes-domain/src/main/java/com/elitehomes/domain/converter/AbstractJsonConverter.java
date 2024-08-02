package com.elitehomes.domain.converter;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

public abstract class AbstractJsonConverter<T> implements AttributeConverter<T, String> {

	private static final Logger log = LoggerFactory.getLogger(AbstractJsonConverter.class);
	
	private final ObjectMapper objectMapper;
	private final Class<? extends T> type;
	
    public AbstractJsonConverter(Class<? extends T> type, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.type = type;
	}

	@Override
    public String convertToDatabaseColumn(T entity) {
        String json = null;
        if (Objects.nonNull(entity)) {
	        try {
	            json = objectMapper.writeValueAsString(entity);
	        } catch (Exception e) {
	        	log.error("JSON writing error {}", e.getMessage(), e);
	        	throw new RuntimeException("JSON writing error");
	        }
        }
        return json;
    }

	@Override
    public T convertToEntityAttribute(String json) {
        T entity = null;
        if (StringUtils.isNotBlank(json)) {
	        try {
	        	entity = objectMapper.readValue(json, type);
	        } catch (final IOException e) {
	        	log.error("JSON reading error {}", e.getMessage(), e);
	        	throw new RuntimeException("JSON writing error");
	        }
        }
        return entity;
    }

}
