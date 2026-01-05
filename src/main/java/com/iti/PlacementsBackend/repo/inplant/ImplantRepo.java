package com.iti.PlacementsBackend.repo.inplant;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iti.PlacementsBackend.entity.inplant.ImplantEntity;
import com.iti.PlacementsBackend.projection.DistReportProj;
import com.iti.PlacementsBackend.projection.inplant.ImplantProjection;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportIndustrieswiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiIndustriesProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiwiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportTradesProj;
import com.iti.PlacementsBackend.projection.inplant.TwoYearsDataProjection;

public interface ImplantRepo extends JpaRepository<ImplantEntity, Long>{
	
	public List<ImplantEntity> findByItiCode(String itiCode);
	
	@Query(value = "select \r\n" + 
			" a.implant_id,a.description,\r\n" + 
			" a.distcode as implant_distcode,f.distname as implant_distname,\r\n" + 
			" f.distname as implant_statename,f.statecode as implant_statecode,\r\n" + 
			" a.entry_date,a.faculty_name,a.from_date,a.hr_no,a.industry_address,  \r\n" + 
			" a.iti_code,b.iti_name,a.location,a.no_of_days,a.no_of_students,a.slno,d.industry_name,a.to_date, \r\n" + 
			" a.trade_short,e.trade_name,b.dist_code as itidistcode,c.dist_name as itidistname \r\n" + 
			"from implant.implant a \r\n" + 
			" left join  iti b on a.iti_code=b.iti_code \r\n" + 
			" left join  dist_mst c on b.dist_code=c.dist_code  \r\n" + 
			" left join  dists_statewise f on a.distcode=f.distcode  \r\n" + 
			" left join implant.industries d on a.slno=d.slno  \r\n" + 
			" left join  ititrade_master e on a.trade_short=e.trade_short \r\n" + 
			" where b.dist_code=:distCode  order by b.dist_code,a.iti_code,a.slno",nativeQuery = true)
	public List<DistReportProj> findByItiDistCode(String distCode);
	
	@Query(value = "select  \r\n" + 
			" a.implant_id,a.description,\r\n" + 
			" a.distcode as implant_distcode,f.distname as implant_distname,\r\n" + 
			" f.statecode as implant_statecode,f.statename as implant_statename,\r\n" + 
			" a.entry_date,a.faculty_name,a.from_date,a.hr_no,a.industry_address,  \r\n" + 
			" a.iti_code,b.iti_name,a.location,a.no_of_days,a.no_of_students,a.slno,d.industry_name,a.to_date, \r\n" + 
			" a.trade_short,e.trade_name,b.dist_code as itidistcode,c.dist_name as itidistname \r\n" + 
			" from implant.implant a \r\n" + 
			" left join  iti b on a.iti_code=b.iti_code \r\n" + 
			" left join  dist_mst c on b.dist_code=c.dist_code \r\n" + 
			" left join  dists_statewise f on a.distcode=f.distcode \r\n" + 
			" left join implant.industries d on a.slno=d.slno  \r\n" + 
			" left join  ititrade_master e on a.trade_short=e.trade_short \r\n" + 
			" order by b.dist_code,a.iti_code,a.slno",nativeQuery = true)
	public List<DistReportProj> findAllImplantForNodal();
	
	@Query(value="SELECT COUNT(DISTINCT iti_code) FROM implant.implant", nativeQuery = true)
	public Long getItisDistinctCount();
	@Query(value="SELECT COUNT(DISTINCT d.industry_id) FROM implant.implant a left join implant.industries d on a.slno=d.slno ", nativeQuery = true)
	public Long getIndustriesDistinctCount();
	@Query(value="SELECT COUNT(DISTINCT trade_short) FROM implant.implant", nativeQuery = true)
	public Long getTradesDistinctCount();
	@Query(value="SELECT sum(no_of_students) FROM implant.implant", nativeQuery = true)
	public Long getTraineesCount();
	
	@Query(value="select b.dist_code,c.dist_name,a.iti_code,b.iti_name, \r\n"
			+ "count(distinct industry_id) as noOfIndustries, \r\n"
			+ "count(distinct d.trade_short) as noOfTrades, \r\n"
			+ "sum(no_of_students) as sumOfTrainees from implant.implant a \r\n"
			+ "left join iti b on a.iti_code=b.iti_code \r\n"
			+ "left join dist_mst c on c.dist_code=b.dist_code \r\n"
			+ "left join implant.industries d on a.slno=d.slno\r\n"
			+ "group by b.dist_code,c.dist_name,a.iti_code,b.iti_name order by b.dist_code,a.iti_code",nativeQuery = true)
	public List<TraineesReportItiwiseProj> getTraineesReportItiwise();
	
	@Query(value="select  d.industry_name,d.industry_id,count(a.trade_short) as noOfTrades\r\n"
			+ "from implant.implant a \r\n"
			+ "left join implant.industries d on a.slno=d.slno \r\n"
			+ "group by  d.industry_name,d.industry_id\r\n"
			+ "order by  d.industry_name,d.industry_id",nativeQuery = true)
	public List<TraineesReportIndustrieswiseProj> getTraineesReportIndustrieswise();
	
	@Query(value="select  a.trade_short,b.trade_name,count(distinct slno) AS slno ,sum(no_of_students) as noofstudents\r\n"
			+ "			from implant.implant a \r\n"
			+ "			 left join ititrade_master b on a.trade_short=b.trade_short\r\n"
			+ "			 where a.trade_short is not null\r\n"
			+ "			 group by  a.trade_short,b.trade_name\r\n"
			+ "			 order by b.trade_name", nativeQuery = true)
	public List<TraineesReportTradesProj> getTraineesReportTrades();
	
	@Query(value="select distinct e.dist_code,e.dist_name,a.iti_code,d.iti_name,\r\n"
			+ "b.industry_id,b.industry_name,b.trade_short,c.trade_name,\r\n"
			+ "sum(no_of_students) as noOfTrainees from implant.implant a\r\n"
			+ "left join implant.industries b on a.slno=b.slno\r\n"
			+ "left join ititrade_master c on b.trade_short=c.trade_short\r\n"
			+ "left join iti d on a.iti_code=d.iti_code\r\n"
			+ "left join dist_mst e on d.dist_code =e.dist_code\r\n"
			+ "where a.iti_code=:iti_code\r\n"
			+ "group by e.dist_code,e.dist_name,a.iti_code,d.iti_name,\r\n"
			+ "b.industry_id,b.industry_name,b.trade_short,c.trade_name",nativeQuery = true)
	public List<TraineesReportItiIndustriesProj> getTraineesReportItiIndustries(String iti_code);
	
	@Query(value="select distinct e.dist_code,e.dist_name,a.iti_code,d.iti_name,b.industry_id,b.industry_name,b.trade_short,c.trade_name,\r\n"
			+ "sum(no_of_students) as noOfTrainees from implant.implant a\r\n"
			+ "left join implant.industries b on a.slno=b.slno\r\n"
			+ "left join ititrade_master c on b.trade_short=c.trade_short\r\n"
			+ "left join iti d on a.iti_code=d.iti_code\r\n"
			+ "left join dist_mst e on e.dist_code=d.dist_code\r\n"
			+ "where b.industry_id=:industryId\r\n"
			+ "group by a.iti_code,d.iti_name,b.industry_id,b.industry_name,b.trade_short,c.trade_name,e.dist_code,e.dist_name",nativeQuery = true)
	public List<ImplantProjection> getImplantIndustriesTraineesData(Long industryId);
	
	@Query(value="select a.iti_code,c.iti_name,c.dist_code,d.dist_name,a.trade_short,b.trade_name,\r\n"
			+ "sum(no_of_students) as noOfTrainees,e.industry_id,e.industry_name from implant.implant a\r\n"
			+ "left join ititrade_master b on b.trade_short=a.trade_short\r\n"
			+ "left join iti c on a.iti_code=c.iti_code\r\n"
			+ "left join dist_mst d on d.dist_code=c.dist_code\r\n"
			+ "left join implant.industries e on e.slno=a.slno\r\n"
			+ "where a.trade_short=:trade_short\r\n"
			+ "group by a.iti_code,a.trade_short,b.trade_name,c.iti_name,c.dist_code,d.dist_name,e.industry_id,e.industry_name\r\n"
			+ "order by c.dist_code,a.iti_code",nativeQuery = true)
	public List<ImplantProjection> getImplantTradesData(String trade_short);
	
	public List<ImplantEntity> findBySlno(Long slno);
	
	@Query(value="select * from implant.implant where from_date>=:from_date and to_date<=:to_date",nativeQuery = true)
	public List<ImplantEntity> findInplantWithinDateRange(Date from_date,Date to_date);
	
	//inplant data between from and to date.
	@Query(value = "select a.implant_id,a.description, a.distcode as implant_distcode,f.distname as implant_distname, \r\n"
			+ "f.statecode as implant_statecode,f.statename as implant_statename, a.entry_date,a.faculty_name,\r\n"
			+ "a.from_date,a.hr_no,a.industry_address, a.iti_code,b.iti_name,a.location,a.no_of_days,a.no_of_students,\r\n"
			+ "a.slno,d.industry_name,a.to_date, a.trade_short,e.trade_name,b.dist_code as itidistcode,c.dist_name as itidistname  \r\n"
			+ "from implant.implant a  \r\n"
			+ "left join  iti b on a.iti_code=b.iti_code  \r\n"
			+ "left join  dist_mst c on b.dist_code=c.dist_code  \r\n"
			+ "left join  dists_statewise f on a.distcode=f.distcode  \r\n"
			+ "left join implant.industries d on a.slno=d.slno   \r\n"
			+ "left join  ititrade_master e on a.trade_short=e.trade_short  \r\n"
			+ "where a.from_date>=:fromDate and a.to_date<=:toDate\r\n"
			+ "order by b.dist_code,a.iti_code,a.slno",nativeQuery = true)
	public List<DistReportProj> findAllImplantForNodalBetweenDates(Date fromDate, Date toDate);
	
	@Query(value="select CAST(sum(no_of_students) as int) from implant.implant",nativeQuery = true)
	 public Integer getSumOfStudent();
	
	
	
	 @Query(value = " WITH aggregated_implant AS (\r\n"
	 		+ "		            SELECT \r\n"
	 		+ "		                iti_code, \r\n"
	 		+ "		                SUM(CASE WHEN to_date < NOW() THEN no_of_students ELSE 0 END) AS total_completed,\r\n"
	 		+ "		                SUM(CASE WHEN to_date > NOW() THEN no_of_students ELSE 0 END) AS total_undertraining\r\n"
	 		+ "		            FROM \r\n"
	 		+ "		                implant.implant\r\n"
	 		+ "		            GROUP BY \r\n"
	 		+ "		                iti_code\r\n"
	 		+ "		        )\r\n"
	 		+ "		        SELECT \r\n"
	 		+ "		            dm.dist_name AS distName,\r\n"
	 		+ "		            iti.iti_name AS itiName,\r\n"
	 		+ "		            COUNT(CASE WHEN ia.year_of_admission = :year THEN 1 END) AS admitted,\r\n"
	 		+ "		            COALESCE(ai.total_completed, 0) AS completed,\r\n"
	 		+ "		            COALESCE(ai.total_undertraining, 0) AS undertraining,\r\n"
	 		+ "		            COUNT(CASE WHEN ia.year_of_admission = :year THEN 1 END) - \r\n"
	 		+ "		            (COALESCE(ai.total_completed, 0) + COALESCE(ai.total_undertraining, 0)) AS balance\r\n"
	 		+ "		        FROM \r\n"
	 		+ "		            iti\r\n"
	 		+ "		        INNER JOIN \r\n"
	 		+ "		            dist_mst dm ON iti.dist_code = dm.dist_code\r\n"
	 		+ "		        LEFT JOIN \r\n"
	 		+ "		            admissions.iti_admissions ia ON iti.iti_code = ia.iti_code AND ia.year_of_admission = :year\r\n"
	 		+ "		        LEFT JOIN \r\n"
	 		+ "		            aggregated_implant ai ON iti.iti_code = ai.iti_code\r\n"
	 		+ "		        WHERE \r\n"
	 		+ "		            iti.govt = :itiType\r\n"
	 		+ "		        GROUP BY \r\n"
	 		+ "		            dm.dist_name, iti.iti_name, ai.total_completed, ai.total_undertraining\r\n"
	 		+ "		        ORDER BY \r\n"
	 		+ "		            dm.dist_name, iti.iti_name", nativeQuery = true)
		    List<Map<String, Object>> getReport(@Param("year") String year, @Param("itiType") String itiType);
	 
	 
	 //Implant training data based on 2 years
	 
	 @Query(value = "WITH aggregated_implant AS (\r\n"
	 		+ "    SELECT \r\n"
	 		+ "        iti_code, \r\n"
	 		+ "        SUM(CASE WHEN to_date < NOW() THEN no_of_students ELSE 0 END) AS total_completed,\r\n"
	 		+ "        SUM(CASE WHEN to_date > NOW() THEN no_of_students ELSE 0 END) AS total_undertraining\r\n"
	 		+ "    FROM \r\n"
	 		+ "        implant.implant\r\n"
	 		+ "    GROUP BY \r\n"
	 		+ "        iti_code\r\n"
	 		+ ")\r\n"
	 		+ "\r\n"
	 		+ "SELECT \r\n"
	 		+ "    dm.dist_name,\r\n"
	 		+ "    iti.iti_name,\r\n"
	 		+ "    SUM(CASE WHEN ia.year_of_admission = :currentYear THEN 1 ELSE 0 END) + \r\n"
	 		+ "    SUM(CASE WHEN ia.year_of_admission = :lastYear THEN 1 ELSE 0 END) AS admitted,\r\n"
	 		+ "    COALESCE(ai.total_completed, 0) AS completed,\r\n"
	 		+ "    COALESCE(ai.total_undertraining, 0) AS undertraining,\r\n"
	 		+ "    SUM(CASE WHEN ia.year_of_admission = :currentYear THEN 1 ELSE 0 END) + \r\n"
	 		+ "    SUM(CASE WHEN ia.year_of_admission = :lastYear THEN 1 ELSE 0 END) - \r\n"
	 		+ "    (COALESCE(ai.total_completed, 0) + COALESCE(ai.total_undertraining, 0)) AS balance\r\n"
	 		+ "FROM \r\n"
	 		+ "    iti\r\n"
	 		+ "INNER JOIN \r\n"
	 		+ "    dist_mst dm ON iti.dist_code = dm.dist_code\r\n"
	 		+ "LEFT JOIN \r\n"
	 		+ "    admissions.iti_admissions ia ON iti.iti_code = ia.iti_code AND ia.year_of_admission IN (:lastYear, :currentYear)\r\n"
	 		+ "LEFT JOIN \r\n"
	 		+ "    aggregated_implant ai ON iti.iti_code = ai.iti_code\r\n"
	 		+ "LEFT JOIN \r\n"
	 		+ "    ititrade itm ON iti.iti_code = itm.iti_code AND ia.trade_code = itm.trade_code\r\n"
	 		+ "LEFT JOIN \r\n"
	 		+ "    ititrade_master tm ON itm.trade_code = tm.trade_code \r\n"
	 		+ "    AND ia.year_of_admission = :lastYear AND tm.durationyrs = '24'\r\n"
	 		+ "WHERE \r\n"
	 		+ "    iti.govt = :itiType\r\n"
	 		+ "GROUP BY \r\n"
	 		+ "    dm.dist_name, iti.iti_name, ai.total_completed, ai.total_undertraining\r\n"
	 		+ "ORDER BY \r\n"
	 		+ "    dm.dist_name, iti.iti_name;\r\n"
	 		+ "\r\n"
	 		+ "", nativeQuery = true)
		    List<TwoYearsDataProjection> getfetchTwoYearReport(@Param("currentYear")String currentYear,@Param("lastYear")String lastYear,@Param("itiType") String itiType);
		    
		}
		
	




