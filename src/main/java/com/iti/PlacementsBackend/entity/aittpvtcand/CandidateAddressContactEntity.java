package com.iti.PlacementsBackend.entity.aittpvtcand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aitt_candidate_address_contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateAddressContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permanent_address", columnDefinition = "TEXT")
    private String permanentAddress;

    @Column(name = "correspondence_address", columnDefinition = "TEXT")
    private String correspondenceAddress;

    @Column(name = "mobile", length = 10)
    private String mobile;

    @Column(name = "aadhar", length = 12)
    private String aadhar;

    @Column(name = "email", length = 150)
    private String email;

    private String trade;

    // Link back to candidate
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_id",
            nullable = false,
            unique = true
    )
    private AittPrivateCandidateEntity candidate;
}
