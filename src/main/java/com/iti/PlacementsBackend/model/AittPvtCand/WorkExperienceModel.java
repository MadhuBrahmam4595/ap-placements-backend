package com.iti.PlacementsBackend.model.AittPvtCand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceModel {

    private String industryName;
    private String designation;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String yearsMonths;
}

