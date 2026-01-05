package com.iti.PlacementsBackend.service.impl.inplant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.inplant.IndustryMaster;
import com.iti.PlacementsBackend.projection.MaxCountProj;
import com.iti.PlacementsBackend.repo.inplant.IndustryMasterRepo;
import com.iti.PlacementsBackend.service.inplant.IndustryMasterService;

@Service
public class IndustryMasterServiceImpl implements IndustryMasterService{
	
	@Autowired
	private IndustryMasterRepo industryMasterRepo;

	@Override
	public IndustryMaster saveIndustryMaster(IndustryMaster industryMaster) {
		// TODO Auto-generated method stub
		return industryMasterRepo.save(industryMaster);
	}

	@Override
	public Optional<IndustryMaster> getIndustryMaster(Long industryId) {
		// TODO Auto-generated method stub
		return industryMasterRepo.findById(industryId);
	}

	@Override
	public List<IndustryMaster> getAllIndustryMaster() {
		// TODO Auto-generated method stub
		return industryMasterRepo.findAll();
	}

	@Override
	public void deleteIndustryMaster(Long industryId) {
		// TODO Auto-generated method stub
		industryMasterRepo.deleteById(industryId);
	}

	@Override
	public MaxCountProj getMaxCountIndustryMaster() {
		// TODO Auto-generated method stub
		return industryMasterRepo.getMaxCountIndustryMaster();
	}

	@Override
	public IndustryMaster getByIndustryName(String industryName) {
		// TODO Auto-generated method stub
		return industryMasterRepo.findByIndustryName(industryName);
	}

	@Override
	public List<IndustryMaster> getAllByOrderByIndustryNameAsc() {
		// TODO Auto-generated method stub
		return industryMasterRepo.findAllByOrderByIndustryNameAsc();
	}


}
