package com.iti.PlacementsBackend.service.hrm;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;

public interface CasteMasterService {

	CasteMaster saveCaste(CasteMaster casteMaster);
    List<CasteMaster> getAllCastes();
    Optional<CasteMaster> getCasteById(Long id);
    Optional<CasteMaster> getCasteByCategory(String category);
    CasteMaster updateCaste(Long id, CasteMaster casteDetails);
    void deleteCaste(Long id);
}
