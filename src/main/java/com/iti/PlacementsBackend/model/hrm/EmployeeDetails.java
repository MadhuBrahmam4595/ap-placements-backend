package com.iti.PlacementsBackend.model.hrm;

import java.util.List;

import com.iti.PlacementsBackend.entity.hrm.DeptTest;
import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;

public class EmployeeDetails {
	private List<DeptTest> deptTests;
	private List<EmployeePromotionDetails> employeePromotionDetails;
	public List<DeptTest> getDeptTests() {
		return deptTests;
	}
	public void setDeptTests(List<DeptTest> deptTests) {
		this.deptTests = deptTests;
	}
	public List<EmployeePromotionDetails> getEmployeePromotionDetails() {
		return employeePromotionDetails;
	}
	public void setEmployeePromotionDetails(List<EmployeePromotionDetails> employeePromotionDetails) {
		this.employeePromotionDetails = employeePromotionDetails;
	}
	
	

}
