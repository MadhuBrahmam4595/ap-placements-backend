package com.iti.PlacementsBackend.entity.aittpvtcand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "aitt_candidate_establishment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEstablishmentStatutoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ats_registered", length = 3)
    private String atsRegistered;

    @Column(name = "msme_registered", length = 3)
    private String msmeRegistered;

    @Column(name = "factories_act", length = 3)
    private String factoriesAct;

    @Column(name = "shops_act", length = 3)
    private String shopsAct;

    @Column(name = "apprentice_act_date")
    private LocalDate apprenticeActDate;

    @Column(name = "experience_cert", length = 3)
    private String experienceCert;

    @Column(name = "character_cert", length = 3)
    private String characterCert;

    @Column(name = "gpf_epf_no", length = 50)
    private String gpfEpfNo;

    @Column(name = "gpf_epf_date")
    private LocalDate gpfEpfDate;

    @Column(name = "esi_no", length = 50)
    private String esiNo;

    @Column(name = "esi_date")
    private LocalDate esiDate;

    // FK to candidate
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_id",
            nullable = false,
            unique = true
    )
    private AittPrivateCandidateEntity candidate;
}

