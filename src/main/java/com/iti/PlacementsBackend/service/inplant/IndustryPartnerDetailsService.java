package com.iti.PlacementsBackend.service.inplant;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.inplant.IndustryPartnerDetails;

public interface IndustryPartnerDetailsService {
	
	public Optional<IndustryPartnerDetails> getIndustryPartnerDetails(Long pid);
	public List<IndustryPartnerDetails> getAllIndustryPartnerDetails();
	
	public IndustryPartnerDetails saveIndustryPartnerDetails(IndustryPartnerDetails industryPartnerDetails);
	
	public void deleteIndustryPartnerDetailsById(Long pid);

}
