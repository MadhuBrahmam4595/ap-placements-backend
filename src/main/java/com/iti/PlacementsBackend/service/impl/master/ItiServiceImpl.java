package com.iti.PlacementsBackend.service.impl.master;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.projection.ItiCodeAndNameProj;
import com.iti.PlacementsBackend.repo.master.ItiRepo;
import com.iti.PlacementsBackend.service.master.ItiService;


@Service
public class ItiServiceImpl implements ItiService{
	
	@Autowired
	private ItiRepo itiRepo;
	
	@Override
	public List<ItiCodeAndNameProj> getItiCodeAndName() {
		// TODO Auto-generated method stub
		return itiRepo.getItiCodeAndName();
	}
	
	@Override
	public List<ItiEntity> getByDistCode(String distCode) {
		// TODO Auto-generated method stub
		return itiRepo.findByDistCode(distCode);
	}

	@Override
	public ItiEntity getByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return itiRepo.findByItiCode(itiCode);
	}

	@Override
	public Optional<ItiEntity> getByItiCodee(String itiCode) {
		// TODO Auto-generated method stub
		return itiRepo.findById(itiCode);
	}

	@Override
	public List<ItiEntity> getAllItis() {
		// TODO Auto-generated method stub
		return itiRepo.findAll();
	}
}

