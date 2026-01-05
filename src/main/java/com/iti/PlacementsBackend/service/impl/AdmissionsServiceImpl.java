package com.iti.PlacementsBackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.AdmissionsEntity;
import com.iti.PlacementsBackend.projection.plcmts.GetAdmDetailsByName;
import com.iti.PlacementsBackend.repo.AdmissionsRepo;
import com.iti.PlacementsBackend.service.AdmissionsService;


@Service
public class AdmissionsServiceImpl implements AdmissionsService{
	
	@Autowired
	private AdmissionsRepo repo;

	@Override
	public AdmissionsEntity getByAdmNum(String admNum) {
		// TODO Auto-generated method stub
		return repo.getByAdmNum(admNum);
	}

	@Override
	public List<GetAdmDetailsByName> getByNameslikes(String name) {
		// TODO Auto-generated method stub
		return repo.getByNameslikes(name);
	}

}
