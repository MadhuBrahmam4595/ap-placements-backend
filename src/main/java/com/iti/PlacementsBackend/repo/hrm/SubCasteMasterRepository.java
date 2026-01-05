package com.iti.PlacementsBackend.repo.hrm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;

@Repository
public interface SubCasteMasterRepository extends JpaRepository<SubCasteMaster, Long> {
	 List<SubCasteMaster> findByCasteMaster_CasteId(Long casteId);

}
