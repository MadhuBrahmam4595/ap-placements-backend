package com.iti.PlacementsBackend.service.master;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.projection.ItiCodeAndNameProj;


public interface ItiService {
	List<ItiCodeAndNameProj> getItiCodeAndName();
	List<ItiEntity> getByDistCode(String distCode);
	
	ItiEntity getByItiCode(String itiCode);
	
	Optional<ItiEntity> getByItiCodee(String itiCode);
	List<ItiEntity> getAllItis();
	
	
}
