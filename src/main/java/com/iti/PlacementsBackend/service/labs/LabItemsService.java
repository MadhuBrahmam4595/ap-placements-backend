package com.iti.PlacementsBackend.service.labs;

import java.util.List;

import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;

public interface LabItemsService {
	public List<LabItemsEntity> getByItiCode(String itiCode);
	public List<LabItemsEntity> getAll();
	
}
