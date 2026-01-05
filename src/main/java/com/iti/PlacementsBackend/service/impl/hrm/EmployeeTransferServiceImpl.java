package com.iti.PlacementsBackend.service.impl.hrm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.EmployeeTransfer;
import com.iti.PlacementsBackend.repo.hrm.EmployeeTransferRepository;
import com.iti.PlacementsBackend.service.hrm.EmployeeTransferService;

@Service
public class EmployeeTransferServiceImpl implements EmployeeTransferService {
	
	@Autowired
    private EmployeeTransferRepository employeeTransferRepository;

    @Override
    public EmployeeTransfer saveEmployeeTransfer(EmployeeTransfer employeeTransfer) {
        return employeeTransferRepository.save(employeeTransfer);
    }

}
