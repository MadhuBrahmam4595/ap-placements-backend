package com.iti.PlacementsBackend.entity.hrm;

import jakarta.persistence.*;

@Entity
@Table(name = "designation_mast", schema = "hrms")
public class DesignationMaster {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desig_code",nullable = false)
    private Long desigCode;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "category", nullable = false)
    private String category;

    // Constructors
    public DesignationMaster() {}

    public DesignationMaster(String designation, String category) {
        this.designation = designation;
        this.category = category;
    }

    // Getters and Setters
    public Long getDesigCode() {
        return desigCode;
    }

    public void setDesigCode(Long desigCode) {
        this.desigCode = desigCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString() Method
    @Override
    public String toString() {
        return "DesignationMast{" +
                "desigCode=" + desigCode +
                ", designation='" + designation + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}


