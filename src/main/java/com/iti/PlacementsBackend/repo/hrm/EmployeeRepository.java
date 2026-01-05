package com.iti.PlacementsBackend.repo.hrm;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	public List<Employee> findByItiCode(String itiCode);
	public List<Employee> findAll();
	
	
	
	


}
