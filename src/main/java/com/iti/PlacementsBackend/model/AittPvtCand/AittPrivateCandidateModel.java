package com.iti.PlacementsBackend.model.AittPvtCand;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AittPrivateCandidateModel {

    // ========= PERSONAL DETAILS ====
    private String category;
    private String applicantName;
    private String fatherName;
    private String fatherOccupation;
    private String motherName;

    private LocalDate dob;
    private Integer age;
    private String gender;

    private Long casteId;
    private Long subCasteId;

    private String pwdFlag;
    private String pwdCategory;
    private String ewsFlag;

    private byte[] photo;
    private String photoContentType;

    // ========= ADDRESS & CONTACT DETAILS =========
    private String permanentAddress;
    private String correspondenceAddress;
    private String mobile;
    private String aadhar;
    private String email;
    private String tradeApplied;

    // ======== PRESENT WORKING DETAILS
    private String officeAddress;
    private String employeeIdNumber;
    private String employerMobile;
    private String employerEmail;
    private String industryRegistrationDetails;

    // ===== ESTABLISHMENT & STATUTORY DETAILS =====
    private String atsRegistered;
    private String msmeRegistered;
    private String factoriesAct;
    private String shopsAct;

    private LocalDate apprenticeActDate;

    private String experienceCert;
    private String characterCert;

    private String gpfEpfNo;
    private LocalDate gpfEpfDate;

    private String esiNo;
    private LocalDate esiDate;

    // ======== EDUCATIONAL & TECHNICAL QUALIFICATIONS
    private List<CandidateQualificationModel> qualifications;

    // =========== WORK EXPERIENCE DETAILS
    private List<WorkExperienceModel> workExperiences;




}
