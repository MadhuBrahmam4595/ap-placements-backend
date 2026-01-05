package com.iti.PlacementsBackend.service.inplant;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.inplant.IndustryMaster;
import com.iti.PlacementsBackend.projection.MaxCountProj;

public interface IndustryMasterService {

	public IndustryMaster saveIndustryMaster(IndustryMaster industryMaster);
	public Optional<IndustryMaster> getIndustryMaster(Long industryId);
	public List<IndustryMaster> getAllIndustryMaster();
	public void deleteIndustryMaster(Long industryId);
	
	public MaxCountProj getMaxCountIndustryMaster();
	public IndustryMaster getByIndustryName(String industryName);
	
	public List<IndustryMaster> getAllByOrderByIndustryNameAsc();
}
