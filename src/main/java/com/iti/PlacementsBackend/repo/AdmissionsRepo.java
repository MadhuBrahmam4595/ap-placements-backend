package com.iti.PlacementsBackend.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iti.PlacementsBackend.entity.AdmissionsEntity;
import com.iti.PlacementsBackend.projection.plcmts.GetAdmDetailsByName;


public interface AdmissionsRepo extends JpaRepository<AdmissionsEntity, String> {
   @Query( value = "select name,fname,adm_num as admNum from admissions.iti_admissions where name ilike %:name%",  nativeQuery = true  )
   List<GetAdmDetailsByName> getByNameslikes(String name);

   AdmissionsEntity getByAdmNum(String admNum);
   
}
