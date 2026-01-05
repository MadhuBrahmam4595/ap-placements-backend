package com.iti.PlacementsBackend.service.impl.master;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.master.OldDistMasterEntity;
import com.iti.PlacementsBackend.repo.master.OldDistMasterRepo;
import com.iti.PlacementsBackend.service.master.OldDistMasterService;

@Service
public class OldDistMasterServiceImpl implements OldDistMasterService{
	
	  @Autowired
	   OldDistMasterRepo oldDistMasterRepo;

	   public List<OldDistMasterEntity> getAllItiDist() {
	      return this.oldDistMasterRepo.findAll();
	   }
	   
	   @Override
		public Optional<OldDistMasterEntity> getByDistCode(String dist_code) {
			// TODO Auto-generated method stub
			return oldDistMasterRepo.findById(dist_code);
		}

	@Override
	public List<OldDistMasterEntity> getAll() {
		// TODO Auto-generated method stub
		return oldDistMasterRepo.findAll();
	}

}
