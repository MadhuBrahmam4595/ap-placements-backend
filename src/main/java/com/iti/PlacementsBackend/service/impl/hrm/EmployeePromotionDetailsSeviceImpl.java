package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;
import com.iti.PlacementsBackend.repo.hrm.EmployeePromotionDetailsRepository;
import com.iti.PlacementsBackend.service.hrm.EmployeePromotionDetailsSevice;

@Service
public class EmployeePromotionDetailsSeviceImpl implements EmployeePromotionDetailsSevice {
	
	@Autowired
	public EmployeePromotionDetailsRepository employeePromotionDetailsRepository; 

	@Override
	public List<EmployeePromotionDetails> getByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return employeePromotionDetailsRepository.findByItiCode(itiCode);
	}

	@Override
	public List<EmployeePromotionDetails> getAll() {
		// TODO Auto-generated method stub
		return employeePromotionDetailsRepository.findAll();
	}

	@Override
	public List<EmployeePromotionDetails> findById_EmployeeCode(String employeeCode) {
		// TODO Auto-generated method stub
		return employeePromotionDetailsRepository.findById_EmployeeCode(employeeCode);
	}

}
