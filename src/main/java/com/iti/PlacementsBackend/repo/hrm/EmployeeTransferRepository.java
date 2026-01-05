package com.iti.PlacementsBackend.repo.hrm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.EmployeeTransfer;

@Repository
public interface EmployeeTransferRepository extends JpaRepository<EmployeeTransfer, String> {

}
