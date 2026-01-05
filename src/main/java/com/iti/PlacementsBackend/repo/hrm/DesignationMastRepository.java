package com.iti.PlacementsBackend.repo.hrm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.DesignationMaster;

@Repository
public interface DesignationMastRepository extends JpaRepository<DesignationMaster, Long> {
	
	Optional<DesignationMaster> findByDesignation(String designation);

}
