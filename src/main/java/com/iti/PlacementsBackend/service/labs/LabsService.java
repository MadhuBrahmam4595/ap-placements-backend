package com.iti.PlacementsBackend.service.labs;

import java.util.List;

import com.iti.PlacementsBackend.entity.labs.LabEntity;
import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;

public interface LabsService {
	
	public LabEntity saveLabWithItems(LabEntity labEntity, List<LabItemsEntity> labItemsEntities);
	public LabEntity saveLab(LabEntity labEntity);
	public LabItemsEntity saveLabItem(LabItemsEntity labItem);
	
	public List<LabEntity> getByItiCode(String itiCode);
	public List<LabEntity> getAll();
	

}
