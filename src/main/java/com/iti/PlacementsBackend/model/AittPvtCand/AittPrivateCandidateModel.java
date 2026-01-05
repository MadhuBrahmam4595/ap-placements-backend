package com.iti.PlacementsBackend.model.AittPvtCand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AittPrivateCandidateModel {

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
}
