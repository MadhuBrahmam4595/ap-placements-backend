package com.iti.PlacementsBackend.service.impl.hrm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.hrm.DeptTestMaster;
import com.iti.PlacementsBackend.repo.hrm.DeptTestMasterRepository;
import com.iti.PlacementsBackend.service.hrm.DeptTestMasterService;

@Service
public class DeptTestMasterServiceImpl implements DeptTestMasterService{

	@Autowired
	private DeptTestMasterRepository deptTestMasterRepository;
	
	@Override
	public DeptTestMaster saveTest(DeptTestMaster test) {
		// TODO Auto-generated method stub
		return deptTestMasterRepository.save(test);
	}

	@Override
	public List<DeptTestMaster> getAllTests() {
		// TODO Auto-generated method stub
		return deptTestMasterRepository.findAll();
	}

	@Override
	public Optional<DeptTestMaster> getTestById(Long id) {
		// TODO Auto-generated method stub
		return deptTestMasterRepository.findById(id);
	}

	@Override
	public Optional<DeptTestMaster> getTestByName(String testName) {
		// TODO Auto-generated method stub
		return deptTestMasterRepository.findByTestName(testName);
	}

	@Override
	public DeptTestMaster updateTest(Long id, DeptTestMaster testDetails) {
        return deptTestMasterRepository.findById(id).map(existingTest -> {
            existingTest.setTestName(testDetails.getTestName());
            return deptTestMasterRepository.save(existingTest);
        }).orElse(null);
    }

	@Override
	public void deleteTest(Long id) {
		
		// TODO Auto-generated method stub
		deptTestMasterRepository.deleteById(id);
		
	}

}
