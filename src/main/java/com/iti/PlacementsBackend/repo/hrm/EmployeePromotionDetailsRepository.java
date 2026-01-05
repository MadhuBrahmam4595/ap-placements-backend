package com.iti.PlacementsBackend.repo.hrm;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;
import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionId;

@Repository
public interface EmployeePromotionDetailsRepository extends JpaRepository<EmployeePromotionDetails, EmployeePromotionId> {
	public List<EmployeePromotionDetails> findByItiCode(String itiCode);
	
	public List <EmployeePromotionDetails> findById_EmployeeCode(String employeeCode);
	

	

}
