package com.iti.PlacementsBackend.model.AittPvtCand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateQualificationModel {

    private String fromYear;
    private String toYear;
    private String instituteName;
    private String tradeName;
    private String examName;
    private String sscMarks;
}
