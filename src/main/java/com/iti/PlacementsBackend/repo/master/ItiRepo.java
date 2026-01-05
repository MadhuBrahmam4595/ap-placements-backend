package com.iti.PlacementsBackend.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.projection.ItiCodeAndNameProj;


public interface ItiRepo extends JpaRepository<ItiEntity, String>{
	
	 @Query( value = "select * from iti where govt='G' and dist_code=:distCode", nativeQuery = true )
	   List<ItiEntity> getAllGovtItisInDist(String distCode);
	   
	   @Query(value="select iti_code,iti_name from iti", nativeQuery = true)
	   List<ItiCodeAndNameProj> getItiCodeAndName();
	   
	   List<ItiEntity> findByDistCode(String distCode);
	   ItiEntity findByItiCode(String itiCode);
	   
	 
	   
	   

}
