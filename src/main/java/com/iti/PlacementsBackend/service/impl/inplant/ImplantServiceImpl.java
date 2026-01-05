package com.iti.PlacementsBackend.service.impl.inplant;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.inplant.ImplantEntity;
import com.iti.PlacementsBackend.projection.DistReportProj;
import com.iti.PlacementsBackend.projection.inplant.ImplantProjection;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportIndustrieswiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiIndustriesProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiwiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportTradesProj;
import com.iti.PlacementsBackend.projection.inplant.TwoYearsDataProjection;
import com.iti.PlacementsBackend.repo.inplant.ImplantRepo;
import com.iti.PlacementsBackend.service.inplant.ImplantService;

@Service
public class ImplantServiceImpl implements ImplantService{
	
	@Autowired private ImplantRepo implantRepo;

	@Override
	public ImplantEntity saveImplant(ImplantEntity implantEntity) {
		// TODO Auto-generated method stub
		return implantRepo.save(implantEntity);
	}

	@Override
	public Optional<ImplantEntity> getImplant(Long implantId) {
		// TODO Auto-generated method stub
		return implantRepo.findById(implantId);
	}

	@Override
	public List<ImplantEntity> getAllImplants() {
		// TODO Auto-generated method stub
		return implantRepo.findAll();
	}

	@Override
	public void deleteImplant(Long implantId) {
		// TODO Auto-generated method stub
		implantRepo.deleteById(implantId);
	}

	@Override
	public List<ImplantEntity> getByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return implantRepo.findByItiCode(itiCode);
	}

	@Override
	public List<DistReportProj> getByItiDistCode(String distCode) {
		// TODO Auto-generated method stub
		return implantRepo.findByItiDistCode(distCode);
	}

	@Override
	public List<DistReportProj> getAllImplantForNodal() {
		// TODO Auto-generated method stub
		return implantRepo.findAllImplantForNodal();
	}
	
	@Override
	public Long getItisDistinctCount() {
		// TODO Auto-generated method stub
		return implantRepo.getItisDistinctCount();
	}
	@Override
	public Long getIndustriesDistinctCount() {
		// TODO Auto-generated method stub
		return implantRepo.getIndustriesDistinctCount();
	}
	@Override
	public Long getTradesDistinctCount() {
		// TODO Auto-generated method stub
		return implantRepo.getTradesDistinctCount();
	}
	@Override
	public Long getTraineesCount() {
		// TODO Auto-generated method stub
		return implantRepo.getTraineesCount();
	}
	@Override
	public List<TraineesReportItiwiseProj> getTraineesReportItiwise() {
		// TODO Auto-generated method stub
		return implantRepo.getTraineesReportItiwise();
	}
	@Override
	public List<TraineesReportIndustrieswiseProj> getTraineesReportIndustrieswise() {
		// TODO Auto-generated method stub
		return implantRepo.getTraineesReportIndustrieswise();
	}
	@Override
	public List<TraineesReportTradesProj> getTraineesReportTrades() {
		// TODO Auto-generated method stub
		return implantRepo.getTraineesReportTrades();
	}
	@Override
	public List<TraineesReportItiIndustriesProj> getTraineesReportItiIndustries(String iti_code) {
		// TODO Auto-generated method stub
		return implantRepo.getTraineesReportItiIndustries(iti_code);
	}
	@Override
	public List<ImplantProjection> getImplantIndustriesTraineesData(Long industryId) {
		// TODO Auto-generated method stub
		return implantRepo.getImplantIndustriesTraineesData(industryId);
	}
	@Override
	public List<ImplantProjection> getImplantTradesData(String trade_short) {
		// TODO Auto-generated method stub
		return implantRepo.getImplantTradesData(trade_short);
	}

	@Override
	public List<ImplantEntity> findBySlno(Long slno) {
		// TODO Auto-generated method stub
		return implantRepo.findBySlno(slno);
	}

	@Override
	public List<ImplantEntity> findInplantWithinDateRange(Date from_date, Date to_date) {
		// TODO Auto-generated method stub
		return implantRepo.findInplantWithinDateRange(from_date, to_date);
	}
	@Override
	public List<DistReportProj> findAllImplantForNodalBetweenDates(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return implantRepo.findAllImplantForNodalBetweenDates(fromDate, toDate);
	}
	@Override
	public Integer getSumOfStudent() {
		// TODO Auto-generated method stub
		return implantRepo.getSumOfStudent();
	}

	@Override
	public List<Map<String, Object>> getReport(String year, String itiType) {
		// TODO Auto-generated method stub
		return implantRepo.getReport(year, itiType);
	}

	@Override
	public List<TwoYearsDataProjection> getfetchTwoYearReport(String itiType, String currentYear, String lastYear) {
		// TODO Auto-generated method stub
		return implantRepo.getfetchTwoYearReport( currentYear, lastYear,itiType);
	}

	

	
}

