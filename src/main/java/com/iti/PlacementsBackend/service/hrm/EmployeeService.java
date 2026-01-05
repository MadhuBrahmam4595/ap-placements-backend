package com.iti.PlacementsBackend.service.hrm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.DeptTest;
import com.iti.PlacementsBackend.entity.hrm.Employee;
import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;

@Service
public interface EmployeeService {
	public  Employee saveEmployee(Employee employee, List<EmployeePromotionDetails> promotionDetails, List<DeptTest> deptTests);
	
	public List<Employee> findByItiCode(String itiCode);
	public List<Employee> findAll();
}