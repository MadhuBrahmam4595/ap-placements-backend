package com.iti.PlacementsBackend.service.inplant;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.iti.PlacementsBackend.entity.inplant.ImplantEntity;
import com.iti.PlacementsBackend.projection.DistReportProj;
import com.iti.PlacementsBackend.projection.inplant.ImplantProjection;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportIndustrieswiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiIndustriesProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiwiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportTradesProj;
import com.iti.PlacementsBackend.projection.inplant.TwoYearsDataProjection;

public interface ImplantService {
	
	public ImplantEntity saveImplant(ImplantEntity implantEntity);
	public Optional<ImplantEntity> getImplant(Long implantId);
	public List<ImplantEntity> getAllImplants();
	
	public void deleteImplant(Long implantId);
	
	public List<ImplantEntity> getByItiCode(String itiCode);
	
	public List<DistReportProj> getByItiDistCode(String distCode);
	public List<DistReportProj> getAllImplantForNodal();
	
	public Long getItisDistinctCount();
	public Long getIndustriesDistinctCount();
	public Long getTradesDistinctCount();
	public Long getTraineesCount();
	
	public List<TraineesReportItiwiseProj> getTraineesReportItiwise();
	public List<TraineesReportIndustrieswiseProj> getTraineesReportIndustrieswise();
	public List<TraineesReportTradesProj> getTraineesReportTrades();
	
	public List<TraineesReportItiIndustriesProj> getTraineesReportItiIndustries(String iti_code);
	public List<ImplantProjection> getImplantIndustriesTraineesData(Long industryId);
	public List<ImplantProjection> getImplantTradesData(String trade_short);
	
	public List<ImplantEntity> findBySlno(Long slno);
	
	public List<ImplantEntity> findInplantWithinDateRange( Date from_date, Date to_date);
	
	public List<DistReportProj> findAllImplantForNodalBetweenDates(Date fromDate, Date toDate);
	
	public Integer getSumOfStudent();
	
	public List<Map<String, Object>> getReport(@Param("year") String year, @Param("itiType") String itiType);
	
    public List<TwoYearsDataProjection> getfetchTwoYearReport(@Param("itiType") String itiType, @Param("currentYear")String currentYear,@Param("lastYear")String lastYear);

	
	
}

