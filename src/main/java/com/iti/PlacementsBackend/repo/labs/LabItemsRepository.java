package com.iti.PlacementsBackend.repo.labs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;

public interface LabItemsRepository extends JpaRepository<LabItemsEntity, Long> {
	
	public List<LabItemsEntity> findByItiCode(String itiCode);
}
