package com.iti.PlacementsBackend.repo.inplant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iti.PlacementsBackend.entity.inplant.IndustryPartnerDetails;

public interface IndustryPartnerDetailsRepo extends JpaRepository<IndustryPartnerDetails, Long>{

	public List<IndustryPartnerDetails> findByItiCode(String itiCode);
	public List<IndustryPartnerDetails> findByDistCode(String distCode);
}
