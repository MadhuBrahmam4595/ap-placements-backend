package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.DeptTest;
import com.iti.PlacementsBackend.repo.hrm.DeptTestRepository;
import com.iti.PlacementsBackend.service.hrm.DeptTestService;

@Service
public class DeptTestServiceImpl implements DeptTestService{
	
	@Autowired
	public DeptTestRepository deptTestRepository;

	@Override
	public List<DeptTest> getByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return deptTestRepository.findByItiCode(itiCode);
	}

	@Override
	public List<DeptTest> getAll() {
		// TODO Auto-generated method stub
		return deptTestRepository.findAll();
	}

	@Override
	public List<DeptTest> findByEmployeeCode(String employeeCode) {
		// TODO Auto-generated method stub
		return deptTestRepository.findById_EmployeeCode(employeeCode);
	}

	

}
