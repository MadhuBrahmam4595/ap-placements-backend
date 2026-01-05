package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;
import com.iti.PlacementsBackend.repo.hrm.CasteMasterRepository;
import com.iti.PlacementsBackend.service.hrm.CasteMasterService;

@Service
public class CasteMasterServiceImpl implements CasteMasterService{

	@Autowired
	private CasteMasterRepository casteMasterRepository;
	
	@Override
	public CasteMaster saveCaste(CasteMaster casteMaster) {
		// TODO Auto-generated method stub
		return casteMasterRepository.save(casteMaster);
	}

	@Override
	public List<CasteMaster> getAllCastes() {
		// TODO Auto-generated method stub
		return casteMasterRepository.findAll();
	}

	@Override
	public Optional<CasteMaster> getCasteById(Long id) {
		// TODO Auto-generated method stub
		return casteMasterRepository.findById(id);
	}

	@Override
	public Optional<CasteMaster> getCasteByCategory(String category) {
		// TODO Auto-generated method stub
		return casteMasterRepository.findByCasteCategory(category);
	}

	@Override
	public CasteMaster updateCaste(Long id, CasteMaster casteDetails) {
		// TODO Auto-generated method stub
		
		 Optional<CasteMaster> existingCaste = casteMasterRepository.findById(id);
	        if (existingCaste.isPresent()) {
	            CasteMaster casteMaster = existingCaste.get();
	            casteMaster.setCasteCategory(casteDetails.getCasteCategory());
	            return casteMasterRepository.save(casteMaster);
	        }
	        return null;
	    }

	@Override
	public void deleteCaste(Long id) {
		// TODO Auto-generated method stub
		casteMasterRepository.deleteById(id);
	}

}
