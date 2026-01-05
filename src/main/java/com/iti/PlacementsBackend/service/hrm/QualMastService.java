package com.iti.PlacementsBackend.service.hrm;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.hrm.QualMast;

public interface QualMastService {
	
	QualMast saveQualification(QualMast qualMast);
    List<QualMast> getAllQualifications();
    Optional<QualMast> getQualificationById(Long id);
    void deleteQualification(Long id);

}
