package com.iti.PlacementsBackend.repo.labs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iti.PlacementsBackend.entity.labs.LabEntity;

public interface LabRepository extends JpaRepository<LabEntity, Long>{
	
	public List<LabEntity> findByItiCode(String itiCode);

}
