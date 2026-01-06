package com.iti.PlacementsBackend.entity.aittpvtcand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aitt_candidate_qualification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateQualificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_year", length = 10)
    private String fromYear;

    @Column(name = "to_year", length = 10)
    private String toYear;

    @Column(name = "institute_name", length = 255)
    private String instituteName;

    @Column(name = "trade_name", length = 150)
    private String tradeName;

    @Column(name = "exam_name", length = 150)
    private String examName;

    @Column(name = "ssc_marks", length = 20)
    private String sscMarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private AittPrivateCandidateEntity candidate;
}

