package com.iti.PlacementsBackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.projection.UniversalProjection;
import com.iti.PlacementsBackend.repo.master.ItiTradeMasterRepo;
import com.iti.PlacementsBackend.service.ItitradeService;

@Service
public class ItitradeServiceImpl implements ItitradeService{
	
	@Autowired
	private ItiTradeMasterRepo itiTradeMasterRepo;

	@Override
	public List<UniversalProjection> getTradesInIti(String iticode) {
		// TODO Auto-generated method stub
		return itiTradeMasterRepo.getTradesInIti(iticode);
	}

}
