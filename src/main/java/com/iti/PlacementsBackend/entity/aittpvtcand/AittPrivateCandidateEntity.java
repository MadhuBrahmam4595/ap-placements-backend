package com.iti.PlacementsBackend.entity.aittpvtcand;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aitt_private_candidate")
public class AittPrivateCandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;

    private String category;
    private String applicantName;
    private String photoPath;

    private String fatherName;
    private String fatherOccupation;
    private String motherName;

    private LocalDate dob;
    private Integer age;
    private String gender;

    private String caste;
    private String subcaste;
    private Boolean pwdFlag;
    private String pwdCategory;
    private Boolean ewsFlag;


 }
