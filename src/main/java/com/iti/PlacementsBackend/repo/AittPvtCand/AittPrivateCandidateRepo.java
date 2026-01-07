package com.iti.PlacementsBackend.repo.AittPvtCand;

import com.iti.PlacementsBackend.entity.aittpvtcand.AittPrivateCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AittPrivateCandidateRepo extends JpaRepository<AittPrivateCandidateEntity, Long> {

    @Query(value = "SELECT nextval('aitt_applicant_reg_seq')", nativeQuery = true)
    Long getNextApplicationId();
}
