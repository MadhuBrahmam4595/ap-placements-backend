package com.iti.PlacementsBackend.service.impl.inplant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.inplant.IndustriesEntity;
import com.iti.PlacementsBackend.projection.MaxCountProj;
import com.iti.PlacementsBackend.repo.inplant.IndustriesRepo;
import com.iti.PlacementsBackend.service.inplant.IndustriesService;

@Service
public class IndustriesServiceImpl implements IndustriesService{
	
	@Autowired
	private IndustriesRepo industriesRepo;

	@Override
	public IndustriesEntity saveIndustry(IndustriesEntity industriesEntity) {
		// TODO Auto-generated method stub
		return industriesRepo.save(industriesEntity);
	}

	@Override
	public Optional<IndustriesEntity> getIndustry(Long industryId) {
		// TODO Auto-generated method stub
		return industriesRepo.findById(industryId);
	}

	@Override
	public List<IndustriesEntity> getAllIndustries() {
		// TODO Auto-generated method stub
		return industriesRepo.findAll();
	}

	@Override
	public void deleteIndustry(Long implantId) {
		// TODO Auto-generated method stub
		industriesRepo.deleteById(implantId);
	}

	@Override
	public List<IndustriesEntity> getByDistCode(Integer distCode) {
		// TODO Auto-generated method stub
		return industriesRepo.findByDistCode(distCode);
	}

	@Override
	public List<IndustriesEntity> getByItiCode(Integer itiCode) {
		// TODO Auto-generated method stub
		return industriesRepo.findByItiCode(itiCode);
	}

	@Override
	public MaxCountProj getMaxSlno() {
		// TODO Auto-generated method stub
		return industriesRepo.getMaxSlno();
	}

	@Override
	public IndustriesEntity getByIndustryIdAndItiCodeAndTradeShort(Long industryId, Integer itiCode,
			String tradeShort) {
		// TODO Auto-generated method stub
		return industriesRepo.findByIndustryIdAndItiCodeAndTradeShort(industryId, itiCode, tradeShort);
	}

	@Override
	public Optional<IndustriesEntity> getIndustries(Long slno) {
		// TODO Auto-generated method stub
		return industriesRepo.findById(slno);
	}

	@Override
	public List<IndustriesEntity> getByIndustryId(Long industryId) {
		// TODO Auto-generated method stub
		return industriesRepo.findByIndustryId(industryId);
	}
	

}

