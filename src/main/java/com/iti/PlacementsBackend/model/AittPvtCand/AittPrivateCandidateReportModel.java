package com.iti.PlacementsBackend.model.AittPvtCand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AittPrivateCandidateReportModel {

    private Long id;

    private String category;
    private String applicantName;
    private String fatherName;
    private String fatherOccupation;
    private String motherName;
    private LocalDate dob;
    private Integer age;
    private String gender;

    private String casteName;
    private String subCasteName;

    private String pwdFlag;
    private String pwdCategory;
    private String ewsFlag;

    private String photoBase64;
    private String photoContentType;


}
