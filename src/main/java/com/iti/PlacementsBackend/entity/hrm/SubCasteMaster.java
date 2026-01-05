package com.iti.PlacementsBackend.entity.hrm;

import jakarta.persistence.*;

@Entity
@Table(name = "subcaste_master", schema = "hrms")
public class SubCasteMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcaste_id")
    private Long subCasteId;

    @ManyToOne
    @JoinColumn(name = "caste_id", nullable = false)
    private CasteMaster casteMaster;

    @Column(name = "sub_caste", nullable = false)
    private String subCaste;

    // Constructors
    public SubCasteMaster() {}

    public SubCasteMaster(CasteMaster casteMaster, String subCaste) {
        this.casteMaster = casteMaster;
        this.subCaste = subCaste;
    }

    // Getters and Setters
    public Long getSubCasteId() {
        return subCasteId;
    }

    public void setSubCasteId(Long subCasteId) {
        this.subCasteId = subCasteId;
    }

    public CasteMaster getCasteMaster() {
        return casteMaster;
    }

    public void setCasteMaster(CasteMaster casteMaster) {
        this.casteMaster = casteMaster;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    // toString() Method
    @Override
    public String toString() {
        return "SubCasteMaster{" +
                "subCasteId=" + subCasteId +
                ", casteMaster=" + casteMaster +
                ", subCaste='" + subCaste + '\'' +
                '}';
    }
}