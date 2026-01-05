package com.iti.PlacementsBackend.service.hrm;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;

public interface SubCasteMasterService {
	
	SubCasteMaster saveSubCaste(Long casteId, SubCasteMaster subCaste);
    List<SubCasteMaster> getAllSubCastes();
    List<SubCasteMaster> getSubCastesByCasteId(Long casteId);
    Optional<SubCasteMaster> getSubCasteById(Long subCasteId);
    SubCasteMaster updateSubCaste(Long subCasteId, SubCasteMaster subCasteDetails);
    void deleteSubCaste(Long subCasteId);

}
