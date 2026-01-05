package com.iti.PlacementsBackend.entity.hrm;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeePromotionId implements Serializable {

    @Column(name = "promotion_id")
    private Long promotionId;

    @Column(name = "employee_code")
    private String employeeCode;

    public EmployeePromotionId() {}

    public EmployeePromotionId(Long promotionId, String employeeCode) {
        this.promotionId = promotionId;
        this.employeeCode = employeeCode;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
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
        EmployeePromotionId that = (EmployeePromotionId) o;
        return Objects.equals(promotionId, that.promotionId) &&
               Objects.equals(employeeCode, that.employeeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, employeeCode);
    }
}

