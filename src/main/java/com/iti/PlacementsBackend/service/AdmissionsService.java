package com.iti.PlacementsBackend.service;

import java.util.List;

import com.iti.PlacementsBackend.entity.AdmissionsEntity;
import com.iti.PlacementsBackend.projection.plcmts.GetAdmDetailsByName;

public interface AdmissionsService {
	
	AdmissionsEntity getByAdmNum(String admNum);
	List<GetAdmDetailsByName> getByNameslikes(String name);

}
