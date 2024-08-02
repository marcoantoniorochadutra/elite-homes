package com.elitehomes.domain.repository;

import com.elitehomes.domain.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
}
