package com.iti.PlacementsBackend.entity.aittpvtcand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "aitt_candidate_work_experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateWorkExperienceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String industryName;
    private String designation;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String yearsMonths;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private AittPrivateCandidateEntity candidate;
}
