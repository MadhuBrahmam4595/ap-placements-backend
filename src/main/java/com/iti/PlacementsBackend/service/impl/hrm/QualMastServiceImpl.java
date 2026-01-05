package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.QualMast;
import com.iti.PlacementsBackend.repo.hrm.QualMastRepository;
import com.iti.PlacementsBackend.service.hrm.QualMastService;

@Service
public class QualMastServiceImpl implements QualMastService{

	@Autowired
	public QualMastRepository qualMastRepository;
	
	@Override
	public QualMast saveQualification(QualMast qualMast) {
		// TODO Auto-generated method stub
		return qualMastRepository.save(qualMast);
	}

	@Override
	public List<QualMast> getAllQualifications() {
		// TODO Auto-generated method stub
		return qualMastRepository.findAll();
	}

	@Override
	public Optional<QualMast> getQualificationById(Long id) {
		// TODO Auto-generated method stub
		return qualMastRepository.findById(id) ;
	}

	@Override
	public void deleteQualification(Long id) {
		// TODO Auto-generated method stub
		
		qualMastRepository.deleteById(id);
		
	}

}
