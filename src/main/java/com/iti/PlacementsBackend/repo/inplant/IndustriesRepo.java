package com.iti.PlacementsBackend.repo.inplant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iti.PlacementsBackend.entity.inplant.IndustriesEntity;
import com.iti.PlacementsBackend.projection.MaxCountProj;

public interface IndustriesRepo extends JpaRepository<IndustriesEntity, Long>{
	
	public List<IndustriesEntity> findByDistCode(Integer distCode);
	public List<IndustriesEntity> findByItiCode(Integer itiCode);
	
	@Query(value="select max(slno) from implant.industries",nativeQuery = true)
	public MaxCountProj getMaxSlno();
	
	public IndustriesEntity findByIndustryIdAndItiCodeAndTradeShort(Long industryId,Integer itiCode,String tradeShort);
	public List<IndustriesEntity> findByIndustryId(Long industryId);
	
	

}

