package com.elitehomes.domain.repository;

import com.elitehomes.domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elitehomes.domain.entity.Property;

public interface OwnerRepository extends JpaRepository<Owner, Long>  {
}
