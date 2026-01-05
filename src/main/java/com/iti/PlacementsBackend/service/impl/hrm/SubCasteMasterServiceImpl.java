package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;
import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;
import com.iti.PlacementsBackend.repo.hrm.CasteMasterRepository;
import com.iti.PlacementsBackend.repo.hrm.SubCasteMasterRepository;
import com.iti.PlacementsBackend.service.hrm.SubCasteMasterService;

@Service
public class SubCasteMasterServiceImpl implements SubCasteMasterService {

	@Autowired
    private SubCasteMasterRepository subCasteMasterRepository;

    @Autowired
    private CasteMasterRepository casteMasterRepository;

    @Override
    public SubCasteMaster saveSubCaste(Long casteId, SubCasteMaster subCaste) {
        Optional<CasteMaster> casteMaster = casteMasterRepository.findById(casteId);
        if (casteMaster.isPresent()) {
            subCaste.setCasteMaster(casteMaster.get());
            return subCasteMasterRepository.save(subCaste);
        } else {
            throw new RuntimeException("Caste with ID " + casteId + " not found.");
        }
    }

    @Override
    public List<SubCasteMaster> getAllSubCastes() {
        return subCasteMasterRepository.findAll();
    }

    @Override
    public List<SubCasteMaster> getSubCastesByCasteId(Long casteId) {
        return subCasteMasterRepository.findByCasteMaster_CasteId(casteId);
    }

    @Override
    public Optional<SubCasteMaster> getSubCasteById(Long subCasteId) {
        return subCasteMasterRepository.findById(subCasteId);
    }

    @Override
    public SubCasteMaster updateSubCaste(Long subCasteId, SubCasteMaster subCasteDetails) {
        return subCasteMasterRepository.findById(subCasteId).map(existingSubCaste -> {
            existingSubCaste.setSubCaste(subCasteDetails.getSubCaste());
            return subCasteMasterRepository.save(existingSubCaste);
        }).orElse(null);
    }

    @Override
    public void deleteSubCaste(Long subCasteId) {
        subCasteMasterRepository.deleteById(subCasteId);
    }
}