package com.iti.PlacementsBackend.entity.hrm;

import jakarta.persistence.*;

@Entity
@Table(name = "qual_mast", schema = "hrms")
public class QualMast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qual_code")
    private Long qualCode;

    @Column(name = "qualification", nullable = false)
    private String qualification;

    // Constructors
    public QualMast() {}

    public QualMast(String qualification) {
        this.qualification = qualification;
    }

    // Getters and Setters
    public Long getQualCode() {
        return qualCode;
    }

    public void setQualCode(Long qualCode) {
        this.qualCode = qualCode;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    // toString() Method
    @Override
    public String toString() {
        return "QualMast{" +
                "qualCode=" + qualCode +
                ", qualification='" + qualification + '\'' +
                '}';
    }
}