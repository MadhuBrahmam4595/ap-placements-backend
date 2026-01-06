package com.iti.PlacementsBackend.entity.aittpvtcand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aitt_candidate_work_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateWorkDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "office_address", columnDefinition = "TEXT")
    private String officeAddress;

    @Column(name = "employee_id_number", length = 50)
    private String employeeIdNumber;

    @Column(name = "employer_mobile", length = 15)
    private String employerMobile;

    @Column(name = "employer_email", length = 150)
    private String employerEmail;

    @Column(name = "industry_registration_details", columnDefinition = "TEXT")
    private String industryRegistrationDetails;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_id",
            nullable = false,
            unique = true
    )
    private AittPrivateCandidateEntity candidate;
}
