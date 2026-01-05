package com.iti.PlacementsBackend.service.impl.labs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;
import com.iti.PlacementsBackend.repo.labs.LabItemsRepository;
import com.iti.PlacementsBackend.service.labs.LabItemsService;

@Service
public class LabItemsServiceImpl implements LabItemsService{
	
	@Autowired
	private LabItemsRepository labItemsRepository;
	
	@Override
	public List<LabItemsEntity> getByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return labItemsRepository.findByItiCode(itiCode);
	}

	@Override
	public List<LabItemsEntity> getAll() {
		// TODO Auto-generated method stub
		return labItemsRepository.findAll();
	}

}
