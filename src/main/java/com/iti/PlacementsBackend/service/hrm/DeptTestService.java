package com.iti.PlacementsBackend.service.hrm;

import java.util.List;

import com.iti.PlacementsBackend.entity.hrm.DeptTest;

public interface DeptTestService {
	
	public List<DeptTest> getByItiCode(String itiCode);
	public List<DeptTest> findByEmployeeCode(String employeeCode);
	public List<DeptTest> getAll();

}
