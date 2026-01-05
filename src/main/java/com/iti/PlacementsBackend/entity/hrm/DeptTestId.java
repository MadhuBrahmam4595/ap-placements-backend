package com.iti.PlacementsBackend.entity.hrm;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DeptTestId implements Serializable {

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "employee_code")
    private String employeeCode;

    public DeptTestId() {}

    public DeptTestId(Long testId, String employeeCode) {
        this.testId = testId;
        this.employeeCode = employeeCode;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptTestId that = (DeptTestId) o;
        return Objects.equals(testId, that.testId) &&
               Objects.equals(employeeCode, that.employeeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, employeeCode);
    }
}
