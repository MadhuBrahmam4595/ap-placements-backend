package com.iti.PlacementsBackend.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iti.PlacementsBackend.entity.master.States_mastEntity;


public interface States_mastRepo extends JpaRepository<States_mastEntity, String> {
	
	States_mastEntity findByStatecode(String statecode);
}
