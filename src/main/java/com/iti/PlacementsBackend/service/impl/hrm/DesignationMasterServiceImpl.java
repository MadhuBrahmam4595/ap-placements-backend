package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.DesignationMaster;
import com.iti.PlacementsBackend.repo.hrm.DesignationMastRepository;
import com.iti.PlacementsBackend.service.hrm.DesignationMasterService;

@Service
public class DesignationMasterServiceImpl implements DesignationMasterService {

	@Autowired
	public DesignationMastRepository designationMastRepository;
	@Override
	public DesignationMaster saveDesignation(DesignationMaster designation) {
		// TODO Auto-generated method stub
		return designationMastRepository.save(designation);
	}

	@Override
	public List<DesignationMaster> getAllDesignations() {
		// TODO Auto-generated method stub
		return designationMastRepository.findAll();
	}

	@Override
	public Optional<DesignationMaster> getDesignationById(Long id) {
		// TODO Auto-generated method stub
		return designationMastRepository.findById(id);
	}

	@Override
    public Optional<DesignationMaster> getDesignationByName(String designation) {
        return designationMastRepository.findByDesignation(designation);
    }

    
    @Override
    public void deleteDesignation(Long id) {
    	designationMastRepository.deleteById(id);
    }

	@Override
	public DesignationMaster updateDesignation(Long id, DesignationMaster designationDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
