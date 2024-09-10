package com.elitehomes.domain.repository;

import com.elitehomes.domain.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {

    @Query("select r.tenantKey from RealEstate r")
    List<String> listSchemas();
}
