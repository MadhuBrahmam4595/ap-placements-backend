package com.iti.PlacementsBackend.entity.hrm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dept_test_mast", schema = "hrms")
public class DeptTestMaster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;
	
	/**
	 * @param testId
	 * @param testName
	 */
	public DeptTestMaster(Long testId, String testName) {
		super();
		this.testId = testId;
		this.testName = testName;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * 
	 */
	public DeptTestMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "test_name", nullable = false)
    private String testName;

	@Override
	public String toString() {
		return "DeptTestMaster [testId=" + testId + ", testName=" + testName + "]";
	}

}
