package com.iti.PlacementsBackend.entity.aittpvtcand;

import java.time.LocalDate;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;
import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aitt_private_candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AittPrivateCandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- BASIC DETAILS ---------- */

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "applicant_name", length = 150)
    private String applicantName;

    @Column(name = "father_name", length = 150)
    private String fatherName;

    @Column(name = "father_occupation", length = 100)
    private String fatherOccupation;

    @Column(name = "mother_name", length = 150)
    private String motherName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender", length = 20)
    private String gender;

    /* ---------- CASTE DETAILS ---------- */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caste_id")
    private CasteMaster caste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcaste_id")
    private SubCasteMaster subCaste;

    /* ---------- PWD & EWS ---------- */

    @Column(name = "pwd_flag", length = 3)
    private String pwdFlag; // Yes / No

    @Column(name = "pwd_category", length = 50)
    private String pwdCategory;

    @Column(name = "ews_flag", length = 3)
    private String ewsFlag; // Yes / No

    /* ---------- PHOTO ---------- */

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type", length = 50)
    private String photoContentType;


 }
