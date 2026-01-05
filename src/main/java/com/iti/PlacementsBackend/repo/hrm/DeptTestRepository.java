package com.iti.PlacementsBackend.repo.hrm;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.DeptTest;
import com.iti.PlacementsBackend.entity.hrm.DeptTestId;

@Repository
public interface DeptTestRepository extends JpaRepository<DeptTest, DeptTestId> {
	
	public List<DeptTest> findByItiCode(String itiCode);
	public List<DeptTest> findById_EmployeeCode(String employeeCode);

	

}
