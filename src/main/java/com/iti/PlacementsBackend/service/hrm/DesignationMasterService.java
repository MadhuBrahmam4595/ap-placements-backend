package com.iti.PlacementsBackend.service.hrm;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.hrm.DesignationMaster;

public interface DesignationMasterService {
	DesignationMaster saveDesignation(DesignationMaster designation);
    List<DesignationMaster> getAllDesignations();
    Optional<DesignationMaster> getDesignationById(Long id);
    Optional<DesignationMaster> getDesignationByName(String designation);
    DesignationMaster updateDesignation(Long id, DesignationMaster designationDetails);
    void deleteDesignation(Long id);

}
