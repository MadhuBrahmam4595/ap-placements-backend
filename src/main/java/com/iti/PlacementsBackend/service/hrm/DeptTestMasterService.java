package com.iti.PlacementsBackend.service.hrm;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.hrm.DeptTestMaster;

public interface DeptTestMasterService {
	
	DeptTestMaster saveTest(DeptTestMaster test);
    List<DeptTestMaster> getAllTests();
    Optional<DeptTestMaster> getTestById(Long id);
    Optional<DeptTestMaster> getTestByName(String testName);
    DeptTestMaster updateTest(Long id, DeptTestMaster testDetails);
    void deleteTest(Long id);

}
