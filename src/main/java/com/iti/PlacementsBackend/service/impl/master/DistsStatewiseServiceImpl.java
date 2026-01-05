package com.iti.PlacementsBackend.service.impl.master;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.master.DistsStatewise;
import com.iti.PlacementsBackend.repo.master.DistsStatewiseRepo;
import com.iti.PlacementsBackend.service.master.DistsStatewiseService;


@Service
public class DistsStatewiseServiceImpl implements DistsStatewiseService{
	
	@Autowired
	 private DistsStatewiseRepo distsStatewiseRepo;

	@Override
	public DistsStatewise saveDistsStatewise(DistsStatewise distsStatewise) {
		// TODO Auto-generated method stub
		return distsStatewiseRepo.save(distsStatewise);
	}

	@Override
	public Optional<DistsStatewise> getDistStatewise(Integer distcode) {
		// TODO Auto-generated method stub
		return distsStatewiseRepo.findById(distcode) ;
	}

	@Override
	public List<DistsStatewise> getAllDistStatewise() {
		// TODO Auto-generated method stub
		return distsStatewiseRepo.findAll();
	}

	 
	
	

}

