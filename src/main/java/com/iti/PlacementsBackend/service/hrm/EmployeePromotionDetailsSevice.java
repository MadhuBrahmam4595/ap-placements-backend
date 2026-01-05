package com.iti.PlacementsBackend.service.hrm;

import java.util.List;

import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;

public interface EmployeePromotionDetailsSevice {
	
	public List<EmployeePromotionDetails> getByItiCode(String itiCode);
	public List<EmployeePromotionDetails> getAll();
	public List <EmployeePromotionDetails> findById_EmployeeCode(String employeeCode);

}
