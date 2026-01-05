package com.iti.PlacementsBackend.repo.inplant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iti.PlacementsBackend.entity.inplant.IndustryMaster;
import com.iti.PlacementsBackend.projection.MaxCountProj;

public interface IndustryMasterRepo extends JpaRepository<IndustryMaster, Long> {

	@Query(value = "select max(industry_id) from implant.industry_master", nativeQuery = true)
	public MaxCountProj getMaxCountIndustryMaster();

	public IndustryMaster findByIndustryName(String industryName);

	public List<IndustryMaster> findAllByOrderByIndustryNameAsc();

}
