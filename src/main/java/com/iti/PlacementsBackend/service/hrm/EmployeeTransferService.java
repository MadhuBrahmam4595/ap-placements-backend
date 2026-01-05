package com.iti.PlacementsBackend.service.hrm;

import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.EmployeeTransfer;

@Service
public interface EmployeeTransferService {
	
	EmployeeTransfer saveEmployeeTransfer(EmployeeTransfer employeeTransfer);

}
