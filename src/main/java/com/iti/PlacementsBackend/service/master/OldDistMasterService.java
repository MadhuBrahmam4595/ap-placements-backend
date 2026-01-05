package com.iti.PlacementsBackend.service.master;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.master.OldDistMasterEntity;


public interface OldDistMasterService {
	
	List<OldDistMasterEntity> getAllItiDist();
	Optional<OldDistMasterEntity> getByDistCode(String dist_code);
	List<OldDistMasterEntity> getAll();

}
