package com.iti.PlacementsBackend.entity.hrm;


import jakarta.persistence.*;

@Entity
@Table(name = "caste_master", schema = "hrms")
public class CasteMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caste_id")
    private Long casteId;

    @Column(name = "caste_category", nullable = false, unique = true)
    private String casteCategory;

    // Constructors
    public CasteMaster() {}

    public CasteMaster(String casteCategory) {
        this.casteCategory = casteCategory;
    }

    // Getters and Setters
    public Long getCasteId() {
        return casteId;
    }

    public void setCasteId(Long casteId) {
        this.casteId = casteId;
    }

    public String getCasteCategory() {
        return casteCategory;
    }

    public void setCasteCategory(String casteCategory) {
        this.casteCategory = casteCategory;
    }

    // toString() Method
    @Override
    public String toString() {
        return "CasteMaster{" +
                "casteId=" + casteId +
                ", casteCategory='" + casteCategory + '\'' +
                '}';
    }
}
