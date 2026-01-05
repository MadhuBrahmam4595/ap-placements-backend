package com.iti.PlacementsBackend.service.inplant;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.inplant.IndustriesEntity;
import com.iti.PlacementsBackend.projection.MaxCountProj;


public interface IndustriesService {
	
	public Optional<IndustriesEntity> getIndustries(Long slno);
	public IndustriesEntity saveIndustry(IndustriesEntity industriesEntity);
	public Optional<IndustriesEntity> getIndustry(Long industryId);
	public List<IndustriesEntity> getAllIndustries();
	
	public void deleteIndustry(Long implantId);
	
	public List<IndustriesEntity> getByDistCode(Integer distCode);
	public List<IndustriesEntity> getByItiCode(Integer itiCode);
	
	public MaxCountProj getMaxSlno();
	public IndustriesEntity getByIndustryIdAndItiCodeAndTradeShort(Long industryId,Integer itiCode,String tradeShort);
	public List<IndustriesEntity> getByIndustryId(Long industryId);

}
