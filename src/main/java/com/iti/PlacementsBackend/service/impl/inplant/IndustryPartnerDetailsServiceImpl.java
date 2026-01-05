package com.iti.PlacementsBackend.service.impl.inplant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.inplant.IndustryPartnerDetails;
import com.iti.PlacementsBackend.repo.inplant.IndustryPartnerDetailsRepo;
import com.iti.PlacementsBackend.service.inplant.IndustryPartnerDetailsService;

@Service
public class IndustryPartnerDetailsServiceImpl implements IndustryPartnerDetailsService{
	
	@Autowired
	private IndustryPartnerDetailsRepo industryPartnerDetailsRepo;

	@Override
	public Optional<IndustryPartnerDetails> getIndustryPartnerDetails(Long pid) {
		// TODO Auto-generated method stub
		return industryPartnerDetailsRepo.findById(pid);
	}

	@Override
	public List<IndustryPartnerDetails> getAllIndustryPartnerDetails() {
		// TODO Auto-generated method stub
		return industryPartnerDetailsRepo.findAll();
	}

	@Override
	public IndustryPartnerDetails saveIndustryPartnerDetails(IndustryPartnerDetails industryPartnerDetails) {
		// TODO Auto-generated method stub
		return industryPartnerDetailsRepo.save(industryPartnerDetails);
	}

	@Override
	public void deleteIndustryPartnerDetailsById(Long pid) {
		// TODO Auto-generated method stub
		industryPartnerDetailsRepo.deleteById(pid);
	}

}
