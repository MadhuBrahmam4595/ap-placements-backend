package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.DeptTest;
import com.iti.PlacementsBackend.entity.hrm.Employee;
import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;
import com.iti.PlacementsBackend.repo.hrm.DeptTestRepository;
import com.iti.PlacementsBackend.repo.hrm.EmployeePromotionDetailsRepository;
import com.iti.PlacementsBackend.repo.hrm.EmployeeRepository;
import com.iti.PlacementsBackend.service.hrm.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeePromotionDetailsRepository employeePromotionDetailsRepository;
	
	@Autowired
	public DeptTestRepository deptTestRepository;

	

	@Override
	public Employee saveEmployee(Employee employee, List<EmployeePromotionDetails> promotionDetails,
			List<DeptTest> deptTests) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
		if (!promotionDetails.isEmpty()) {
			employeePromotionDetailsRepository.saveAll(promotionDetails);
	    }
	    
	    if (!deptTests.isEmpty()) {
	    	deptTestRepository.saveAll(deptTests);
	    }

	    return employee;
	}



	@Override
	public List<Employee> findByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return employeeRepository.findByItiCode(itiCode);
	}



	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}









	



	



	

}
