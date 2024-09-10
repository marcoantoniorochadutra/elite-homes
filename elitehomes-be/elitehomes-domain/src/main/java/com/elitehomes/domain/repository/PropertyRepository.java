package com.elitehomes.domain.repository;

import com.elitehomes.domain.entity.Property;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository  extends JpaRepository<Property, Long>  {
}
