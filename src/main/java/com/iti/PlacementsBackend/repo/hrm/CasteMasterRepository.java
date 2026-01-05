package com.iti.PlacementsBackend.repo.hrm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;

@Repository
public interface CasteMasterRepository extends JpaRepository<CasteMaster, Long> {
	
	 Optional<CasteMaster> findByCasteCategory(String casteCategory);
}
