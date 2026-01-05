package com.iti.PlacementsBackend.repo.hrm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.DeptTestMaster;

@Repository
public interface DeptTestMasterRepository extends JpaRepository<DeptTestMaster, Long> {
    Optional<DeptTestMaster> findByTestName(String testName);
}