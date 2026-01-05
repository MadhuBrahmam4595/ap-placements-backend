package com.iti.PlacementsBackend.repo.plcmts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.plcmts.PlacementEntity;
import com.iti.PlacementsBackend.projection.UniversalProjection;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportApprProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportHigherEduProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportJobProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportSelfEmpProj;
import com.iti.PlacementsBackend.projection.plcmts.PlacementProjection;
import com.iti.PlacementsBackend.projection.plcmts.PlcmtDistWiseCountReport;
import com.iti.PlacementsBackend.projection.plcmts.PlcmtNodalDistLevelCountReport;
import com.iti.PlacementsBackend.projection.plcmts.PlcmtYearWiseReportModel;
import com.iti.PlacementsBackend.projection.plcmts.StateSkillDevelopmentPlanProj;


@Repository
public interface PlacementRepo extends JpaRepository<PlacementEntity, Long> {

	@Query(value = "select * from placements.placements where trade_code = ? and passyear=?", nativeQuery = true)
	List<PlacementEntity> getTradeReport(String passyear, String trade_code);

	@Query(value = "select a.*,b.distname, c.statename from placements.placements a inner join dists_statewise b on a.pdistrict = b.distcode inner join states_mast c on a.pstate = c.statecode where iti_code=? and passyear=? and trade_code=? and ptype=?", nativeQuery = true)
	List<PlacementEntity> getPtypeReport(String iti_code, String passyear, String trade_code, String ptype);

	@Query(value = "select * from placements.placements where adm_num = ?", nativeQuery = true)
	List<PlacementEntity> getPlcmtByAdmnum(String adm_num);

	@Query(value = "select * from placements.placements where entry_by = ?", nativeQuery = true)
	List<PlacementEntity> getPlcmtEntryBy(String entry_by);

	@Query(value = "SELECT COALESCE(passyear, 'Total') AS passyear,SUM(CASE WHEN ptype='Job' THEN 1 ELSE 0 END) AS job,SUM(CASE WHEN ptype='OJ' THEN 1 ELSE 0 END) AS oj,SUM(CASE WHEN ptype='Apprenticeship' THEN 1 ELSE 0 END) AS apprenticeship, SUM(CASE WHEN ptype='OA' THEN 1 ELSE 0 END) AS oa,SUM(CASE WHEN ptype='HigherEducation' THEN 1 ELSE 0 END) AS higherEducation,SUM(CASE WHEN ptype='SelfEmployment' THEN 1 ELSE 0 END) AS selfEmployment, COUNT(*) AS total FROM  placements.placements WHERE passyear != '' GROUP BY  ROLLUP(passyear) ORDER BY   passyear;", nativeQuery = true)
	List<PlcmtYearWiseReportModel> getPlcmtYearWiseReport();

	@Query(value = "SELECT dist_code,dist_name, (select count(*) from placements.placements where entry_distcode=dist_mst.dist_code and ptype in ('Job','OJ'))job,   (select count(*) from placements.placements where entry_distcode=dist_mst.dist_code and    ptype in ('Apprenticeship','OA'))apprenticeship, (select count(*) from placements.placements where dist_code=dist_mst.dist_code and ptype='SelfEmployment')selfEmployment, (select count(*) from placements.placements where dist_code=dist_mst.dist_code and  ptype='HigherEducation')higherEducation from dist_mst order by dist_name ", nativeQuery = true)
	List<PlcmtDistWiseCountReport> getPlcmtDistWiseCountReport();

	// ITI Reports
	// placement type is JOB or OJ
	@Query(value = "select a.pid,a.adm_num,a.name,a.plcmt_year,a.pname_of_company,a.ppostname,a.psalary,a.phrno,b.statename,c.distname,paddress,schedule_date||'---'||schedule_desc as schedule \r\n"
			+ " from placements.placements a "
			+ " left join states_mast b on a.pstate=b.statecode \r\n"
			+ " left join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " left join placements.placements_schedules d on a.schedule_id = cast(d.plcmt_id as character varying) \r\n"
			+ " where ptype=:ptype and a.entry_by=:entryBy order by plcmt_year ", nativeQuery = true)
	List<ItiReportJobProj> getItiReportJobAndOJ(String ptype, String entryBy);

	@Query(value = "select a.pid,a.adm_num,a.name,a.plcmt_year,a.pname_of_company,a.ppostname,a.psalary,a.phrno,b.statename,c.distname,paddress,schedule_date||'---'||schedule_desc as schedule \r\n"
			+ " from placements.placements a "
			+ " left join states_mast b on a.pstate=b.statecode \r\n"
			+ " left join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " left join placements.placements_schedules d on a.schedule_id = cast(d.plcmt_id as character varying) \r\n"
			+ " where ptype=:ptype and a.entry_by=:entryBy and plcmt_year=:plcmtYear order by plcmt_year ", nativeQuery = true)
	List<ItiReportJobProj> getItiReportJobAndOJAndYear(String ptype, String entryBy, String plcmtYear);

	// Placement type is Apprenticeship or OA
	@Query(value = "select a.pid,a.adm_num,a.name,a.plcmt_year,a.pname_of_company,e.trade_name, a.pstipendamt,a.phrno,\r\n"
			+ " a.paaprstartdate,a.paaprenddate,\r\n"
			+ " b.statename,c.distname,paddress,schedule_date||'---'||schedule_desc as schedule \r\n"
			+ " from placements.placements a  "
			+ " left join states_mast b on a.pstate=b.statecode  \r\n"
			+ " left join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " left join placements.placements_schedules d on a.schedule_id =  cast(d.plcmt_id as character varying) \r\n"
			+ " left join ititrade_master e on cast(e.trade_short as character varying) = a.ptrade\r\n"
			+ " where ptype=:ptype and a.entry_by=:entryBy order by plcmt_year", nativeQuery = true)
	List<ItiReportApprProj> getItiReportApprAndOA(String ptype, String entryBy);

	@Query(value = "select a.pid,a.adm_num,a.name,a.plcmt_year,a.pname_of_company,e.trade_name, a.pstipendamt,a.phrno,\r\n"
			+ " a.paaprstartdate,a.paaprenddate,\r\n"
			+ " b.statename,c.distname,paddress,schedule_date||'---'||schedule_desc as schedule \r\n"
			+ " from placements.placements a  "
			+ " left join states_mast b on a.pstate=b.statecode  \r\n"
			+ " left join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " left join placements.placements_schedules d on a.schedule_id =  cast(d.plcmt_id as character varying) \r\n"
			+ " left join ititrade_master e on cast(e.trade_short as character varying) = a.ptrade\r\n"
			+ " where ptype=:ptype and a.entry_by=:entryBy and plcmt_year=:plcmtYear order by plcmt_year", nativeQuery = true)
	List<ItiReportApprProj> getItiReportApprAndOAAndYear(String ptype, String entryBy, String plcmtYear);

	// Placement type is Higher Education
	@Query(value = "select a.pid,adm_num,name,plcmt_year,pcoursename,pclgname,\r\n"
			+ " b.statename,c.distname,paddress from placements.placements a\r\n"
			+ " inner join states_mast b on a.pstate=b.statecode\r\n"
			+ " inner join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " where iti_code=:iticode and ptype=:ptype order by plcmt_year", nativeQuery = true)
	List<ItiReportHigherEduProj> getItiReportHigherEdu(String iticode, String ptype);

	@Query(value = "select a.pid,adm_num,name,plcmt_year,pcoursename,pclgname,\r\n"
			+ "b.statename,c.distname,paddress from placements.placements a\r\n"
			+ " inner join states_mast b on a.pstate=b.statecode\r\n"
			+ " inner join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " where iti_code=:iticode and ptype=:ptype and plcmt_year=:plcmtYear order by plcmt_year", nativeQuery = true)
	List<ItiReportHigherEduProj> getItiReportHigherEduAndYear(String iticode, String ptype, String plcmtYear);

	// Placement type is Self Employment
	@Query(value = "select a.pid,adm_num,name,plcmt_year,pselfemp,pmonthincome,\r\n"
			+ " b.statename,c.distname,paddress from placements.placements a\r\n"
			+ " inner join states_mast b on a.pstate=b.statecode\r\n"
			+ " inner join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " where iti_code=:iticode and ptype=:ptype order by plcmt_year", nativeQuery = true)
	List<ItiReportSelfEmpProj> getItiReportSelfEmp(String iticode, String ptype);

	@Query(value = "select a.pid,adm_num,name,plcmt_year,pselfemp,pmonthincome,\r\n"
			+ " b.statename,c.distname,paddress from placements.placements a\r\n"
			+ " inner join states_mast b on a.pstate=b.statecode\r\n"
			+ " inner join dists_statewise c on a.pdistrict=c.distcode \r\n"
			+ " where iti_code=:iticode and ptype=:ptype and plcmt_year=:plcmtYear order by plcmt_year", nativeQuery = true)
	List<ItiReportSelfEmpProj> getItiReportSelfEmpAndYear(String iticode, String ptype, String plcmtYear);

	// Nodal Reports
	@Query(value = "WITH placement_counts AS (\r\n" + "    SELECT \r\n" + "        dist_code,\r\n"
			+ "        dist_name,\r\n"
			+ "        (SELECT COUNT(*) FROM placements.placements WHERE entry_distcode = dist_mst.dist_code AND ptype = 'Job') AS job,\r\n"
			+ "        (SELECT COUNT(*) FROM placements.placements WHERE entry_distcode = dist_mst.dist_code AND ptype = 'OJ') AS oj,\r\n"
			+ "        (SELECT COUNT(*) FROM placements.placements WHERE entry_distcode = dist_mst.dist_code AND ptype = 'Apprenticeship') AS apprenticeship,\r\n"
			+ "        (SELECT COUNT(*) FROM placements.placements WHERE entry_distcode = dist_mst.dist_code AND ptype = 'OA') AS oa,\r\n"
			+ "        (SELECT COUNT(*) FROM placements.placements WHERE dist_code = dist_mst.dist_code AND ptype = 'SelfEmployment') AS selfEmployment,\r\n"
			+ "        (SELECT COUNT(*) FROM placements.placements WHERE dist_code = dist_mst.dist_code AND ptype = 'HigherEducation') AS higherEducation\r\n"
			+ "    FROM dist_mst\r\n" + ")\r\n" + "SELECT \r\n" + "    dist_code,\r\n" + "    dist_name,\r\n"
			+ "    job,\r\n" + "    oj,\r\n" + "    apprenticeship,\r\n" + "    oa,\r\n" + "    selfEmployment,\r\n"
			+ "    higherEducation,\r\n" 
			+ "    (job + oj + apprenticeship + oa + selfEmployment + higherEducation) AS horizontal_sum\r\n"
			+ " FROM placement_counts\r\n" + "UNION ALL\r\n" + "SELECT \r\n" + "    'TOTAL' AS dist_code,\r\n"
			+ "    'TOTAL' AS dist_name,\r\n" + "    SUM(job),\r\n" + "    SUM(oj),\r\n"
			+ "    SUM(apprenticeship),\r\n" + "    SUM(oa),\r\n" + "    SUM(selfEmployment),\r\n"
			+ "    SUM(higherEducation),\r\n"
			+ "    (SUM(job) + SUM(oj) + SUM(apprenticeship) + SUM(oa) + SUM(selfEmployment) + SUM(higherEducation)) AS horizontal_sum\r\n"
			+ " FROM placement_counts\r\n" + "\r\n" + "", nativeQuery = true)
	List<PlcmtNodalDistLevelCountReport> getPlcmtNodalDistLevelCountReport();

	List<PlacementEntity> findByPtypeAndPassyear(String ptype, String passyear);

	List<PlacementEntity> findByPtype(String ptype);

	@Query(value = "select schedule_id as strCol1, count(*) as longCol1 from placements.placements where ptype in('Job','Apprenticeship') group by schedule_id", nativeQuery = true)
	List<UniversalProjection> getDataGroupByScheduleId();
	
	
	
	@Query(value = "select a.adm_num,a.name,a.plcmt_year,a.pname_of_company,a.ppostname,a.psalary,a.phrno,b.statename,c.distname,paddress,schedule_date||'---'||schedule_desc as schedule \r\n"
			+ " from placements.placements a \r\n" + " inner join states_mast b on a.pstate=b.statecode \r\n"
			+ " inner join dists_statewise c on a.pdistrict=c.distcode  \r\n"
			+ " inner join placements.placements_schedules d on a.schedule_id = cast(d.plcmt_id as character varying) \r\n"
			+ " where a.schedule_id=:scheduleId and a.ptype=:ptype  order by plcmt_year ", nativeQuery = true)
	List<ItiReportJobProj> getPlcmtsByPlcmtIdForJob(String scheduleId,String ptype);
	
	@Query(value = "select a.adm_num,a.name,a.plcmt_year,a.pname_of_company,e.trade_name, a.pstipendamt,a.phrno, \r\n" + 
			" a.paaprstartdate,a.paaprenddate, \r\n" + 
			" b.statename,c.distname,paddress,schedule_date||'---'||schedule_desc as schedule  \r\n" + 
			" from placements.placements a   inner join states_mast b on a.pstate=b.statecode  \r\n" + 
			" inner join dists_statewise c on a.pdistrict=c.distcode   \r\n" + 
			" inner join placements.placements_schedules d on a.schedule_id =  \r\n" + 
			" cast(d.plcmt_id as character varying) \r\n" + 
			" inner join ititrade_master e on cast(e.trade_code as character varying) = a.ptrade \r\n" + 
			" where a.schedule_id=:scheduleId and ptype=:ptype order by plcmt_year", nativeQuery = true)
	List<ItiReportApprProj> getPlcmtsByPlcmtIdForAppr(String scheduleId,String ptype);
	
	List<PlacementEntity> findByScheduleId(String scheduleId);
	
	@Query(value = "select * from placements.placements where ptype in('Job','Apprenticeship')",nativeQuery = true)
	List<PlacementEntity> getJobAndApprData();
	
	@Query(value = "SELECT p.* " +
            "FROM placements.placements p " +
            "JOIN placements.placements_schedules ps ON p.plcmt_id = ps.plcmt_id " +
            "WHERE ps.schedule_date BETWEEN :fromDate AND :toDate order by dist_code,iti_code", nativeQuery = true)
	List<PlacementEntity> findPlacementsByScheduleDate(String fromDate, String toDate);
	
	@Query(value = "SELECT p.* " +
			"FROM placements.placements p " +
			"JOIN placements.placements_schedules ps ON p.plcmt_id = ps.plcmt_id " +
			"WHERE ps.schedule_date BETWEEN :fromDate AND :toDate and ptype=:ptype  order by dist_code,iti_code", nativeQuery = true)
	List<PlacementEntity> findPlacementsByScheduleDateAndPtype(String fromDate, String toDate, String ptype);
	
	
	//dashboard apis
	@Query(value = "SELECT DISTINCT iti_code FROM placements.placements WHERE ptype = :ptype", nativeQuery = true)
	List<String> findDistinctItiCodesByPtype(@Param("ptype") String ptype);
	
	
	//State Skill Development Plan (SSDP)
	@Query(value="SELECT  sm.trade_code,tm.trade_name,\r\n"
			+ "COUNT(DISTINCT sm.iti_code) AS iti_count, SUM((each.value)::INTEGER) AS total_strength\r\n"
			+ "FROM  iti_seatmatrix sm \r\n"
			+ "join ititrade_master tm on sm.trade_code=tm.trade_code\r\n"
			+ "cross join LATERAL EACH(sm.strength) WHERE sm.year = :year\r\n"
			+ "GROUP BY sm.trade_code,tm.trade_name ORDER BY sm.trade_code",nativeQuery = true)
	List<StateSkillDevelopmentPlanProj> stateSkillDevelopmentPlanReport1(String year);
	@Query(value="select count(case when adms.gender='male' then 1 end) as totalmale, \r\n"
			+ "count(case when adms.gender='female' then 1 end) as totalfemale,\r\n"
			+ "count(adms.*) as totalgender,\r\n"
			+ "count(plcmts.*) as totalplcmts\r\n"
			+ "from admissions.iti_admissions adms \r\n"
			+ "left join placements.placements plcmts on plcmts.adm_num = adms.adm_num \r\n"
			+ "where (adms.year_of_admission=:year OR adms.year_of_admission IS NULL) and adms.trade_code=:tradecode",nativeQuery = true)
	StateSkillDevelopmentPlanProj stateSkillDevelopmentPlanReport2(String year,Integer tradecode);
	
	@Query(value="select distinct year_of_admission from admissions.iti_admissions order by year_of_admission",nativeQuery = true)
	List<String> getDistinctYearOfAdmissons();
	
	
	@Query(value="WITH admitted_counts AS (\r\n"
			+ "    SELECT \r\n"
			+ "        ia.iti_code,\r\n"
			+ "        COUNT(DISTINCT CASE WHEN ia.year_of_admission = :previousYear AND tm.durationyrs = 12 THEN ia.adm_num END) AS admitted1Year,\r\n"
			+ "        COUNT(DISTINCT CASE WHEN ia.year_of_admission = :twoYearsBefore AND tm.durationyrs = 24 THEN ia.adm_num END) AS admitted2Year\r\n"
			+ "    FROM admissions.iti_admissions ia\r\n"
			+ "    LEFT JOIN ititrade_master tm ON ia.trade_code = tm.trade_code\r\n"
			+ "    WHERE ia.year_of_admission IN (:previousYear, :twoYearsBefore) \r\n"
			+ "        AND tm.durationyrs IN (12, 24)\r\n"
			+ "    GROUP BY ia.iti_code\r\n"
			+ "),\r\n"
			+ "\r\n"
			+ "placement_counts AS (\r\n"
			+ "    SELECT \r\n"
			+ "        p.iti_code,\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Job', 'OJ') \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS placed_job_other,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Apprenticeship', 'OA') \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS apprenticeship_training,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'SelfEmployment' \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS self_employment,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'HigherEducation' \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS higher_education,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Job', 'OJ') \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS placed_job_other_2yr,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Apprenticeship', 'OA') \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS apprenticeship_training_2yr,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'SelfEmployment' \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS self_employment_2yr,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'HigherEducation' \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS higher_education_2yr\r\n"
			+ "    FROM placements.placements p\r\n"
			+ "    LEFT JOIN admissions.iti_admissions ia ON p.iti_code = ia.iti_code AND p.adm_num = ia.adm_num\r\n"
			+ "    LEFT JOIN ititrade_master tm ON ia.trade_code = tm.trade_code\r\n"
			+ "    WHERE ia.year_of_admission IN (:previousYear, :twoYearsBefore) \r\n"
			+ "        AND tm.durationyrs IN (12, 24)\r\n"
			+ "    GROUP BY p.iti_code\r\n"
			+ ")\r\n"
			+ "\r\n"
			+ "SELECT \r\n"
			+ "    dm.dist_name AS District,\r\n"
			+ "    iti.iti_name AS ITI_NAME,\r\n"
			+ "    iti.ncvt_code AS MIS_CODE,\r\n"
			+ "    \r\n"
			+ "    -- Admitted counts\r\n"
			+ "    COALESCE(ac.admitted1Year, 0) AS Admitted1Year,\r\n"
			+ "    COALESCE(ac.admitted2Year, 0) AS Admitted2Year,\r\n"
			+ "    COALESCE(ac.admitted1Year, 0) + COALESCE(ac.admitted2Year, 0) AS TotalAppeared,  \r\n"
			+ "\r\n"
			+ "    -- Placement counts\r\n"
			+ "    COALESCE(pc.placed_job_other, 0) + COALESCE(pc.placed_job_other_2yr, 0) AS JOJ,\r\n"
			+ "    COALESCE(pc.apprenticeship_training, 0) + COALESCE(pc.apprenticeship_training_2yr, 0) AS AOA,\r\n"
			+ "    COALESCE(pc.self_employment, 0) + COALESCE(pc.self_employment_2yr, 0) AS SelfEmployment,\r\n"
			+ "    COALESCE(pc.higher_education, 0) + COALESCE(pc.higher_education_2yr, 0) AS HigherEducation,\r\n"
			+ "\r\n"
			+ "    -- Total Placement (sum of all placement categories)\r\n"
			+ "    COALESCE(pc.placed_job_other, 0) + COALESCE(pc.placed_job_other_2yr, 0) + \r\n"
			+ "    COALESCE(pc.apprenticeship_training, 0) + COALESCE(pc.apprenticeship_training_2yr, 0) + \r\n"
			+ "    COALESCE(pc.self_employment, 0) + COALESCE(pc.self_employment_2yr, 0) + \r\n"
			+ "    COALESCE(pc.higher_education, 0) + COALESCE(pc.higher_education_2yr, 0) AS TotalPlacement\r\n"
			+ "\r\n"
			+ "FROM iti\r\n"
			+ "LEFT JOIN dist_mst dm ON iti.dist_code = dm.dist_code\r\n"
			+ "LEFT JOIN admitted_counts ac ON iti.iti_code = ac.iti_code\r\n"
			+ "LEFT JOIN placement_counts pc ON iti.iti_code = pc.iti_code\r\n"
			+ " WHERE iti.govt=:itiType\r\n"
			+ "ORDER BY dm.dist_name, iti.iti_name;\r\n"
			+ " ",nativeQuery = true)
	 List<PlacementProjection> getPlacementReport(@Param("previousYear") String previousYear, @Param("twoYearsBefore") String twoYearsBefore, @Param("itiType") String itiType);
     
	@Query(value="WITH admitted_counts AS (\r\n"
			+ "    SELECT \r\n"
			+ "        ia.iti_code,\r\n"
			+ "        COUNT(DISTINCT CASE WHEN ia.year_of_admission = :previousYear AND tm.durationyrs = 12 THEN ia.adm_num END) AS admitted1Year,\r\n"
			+ "        COUNT(DISTINCT CASE WHEN ia.year_of_admission = :twoYearsBefore AND tm.durationyrs = 24 THEN ia.adm_num END) AS admitted2Year\r\n"
			+ "    FROM admissions.iti_admissions ia\r\n"
			+ "    LEFT JOIN ititrade_master tm ON ia.trade_code = tm.trade_code\r\n"
			+ "    WHERE ia.year_of_admission IN (:previousYear, :twoYearsBefore) \r\n"
			+ "        AND tm.durationyrs IN (12, 24)\r\n"
			+ "    GROUP BY ia.iti_code\r\n"
			+ "),\r\n"
			+ "\r\n"
			+ "placement_counts AS (\r\n"
			+ "    SELECT \r\n"
			+ "        p.iti_code,\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Job', 'OJ') \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS placed_job_other,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Apprenticeship', 'OA') \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS apprenticeship_training,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'SelfEmployment' \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS self_employment,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'HigherEducation' \r\n"
			+ "                 AND ia.year_of_admission = :previousYear \r\n"
			+ "                 AND tm.durationyrs = 12 \r\n"
			+ "            THEN p.adm_num END) AS higher_education,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Job', 'OJ') \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS placed_job_other_2yr,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype IN ('Apprenticeship', 'OA') \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS apprenticeship_training_2yr,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'SelfEmployment' \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS self_employment_2yr,\r\n"
			+ "\r\n"
			+ "        COUNT(DISTINCT CASE \r\n"
			+ "            WHEN p.ptype = 'HigherEducation' \r\n"
			+ "                 AND ia.year_of_admission = :twoYearsBefore \r\n"
			+ "                 AND tm.durationyrs = 24 \r\n"
			+ "            THEN p.adm_num END) AS higher_education_2yr\r\n"
			+ "    FROM placements.placements p\r\n"
			+ "    LEFT JOIN admissions.iti_admissions ia ON p.iti_code = ia.iti_code AND p.adm_num = ia.adm_num\r\n"
			+ "    LEFT JOIN ititrade_master tm ON ia.trade_code = tm.trade_code\r\n"
			+ "    WHERE ia.year_of_admission IN (:previousYear, :twoYearsBefore) \r\n"
			+ "        AND tm.durationyrs IN (12, 24)\r\n"
			+ "    GROUP BY p.iti_code\r\n"
			+ ")\r\n"
			+ "\r\n"
			+ "SELECT \r\n"
			+ "    dm.dist_name AS District,\r\n"
			+ "    iti.iti_name AS ITI_NAME,\r\n"
			+ "    iti.ncvt_code AS MIS_CODE,\r\n"
			+ "    \r\n"
			+ "    -- Admitted counts\r\n"
			+ "    COALESCE(ac.admitted1Year, 0) AS Admitted1Year,\r\n"
			+ "    COALESCE(ac.admitted2Year, 0) AS Admitted2Year,\r\n"
			+ "    COALESCE(ac.admitted1Year, 0) + COALESCE(ac.admitted2Year, 0) AS TotalAppeared,  \r\n"
			+ "\r\n"
			+ "    -- Placement counts\r\n"
			+ "    COALESCE(pc.placed_job_other, 0) + COALESCE(pc.placed_job_other_2yr, 0) AS JOJ,\r\n"
			+ "    COALESCE(pc.apprenticeship_training, 0) + COALESCE(pc.apprenticeship_training_2yr, 0) AS AOA,\r\n"
			+ "    COALESCE(pc.self_employment, 0) + COALESCE(pc.self_employment_2yr, 0) AS SelfEmployment,\r\n"
			+ "    COALESCE(pc.higher_education, 0) + COALESCE(pc.higher_education_2yr, 0) AS HigherEducation,\r\n"
			+ "\r\n"
			+ "    -- Total Placement (sum of all placement categories)\r\n"
			+ "    COALESCE(pc.placed_job_other, 0) + COALESCE(pc.placed_job_other_2yr, 0) + \r\n"
			+ "    COALESCE(pc.apprenticeship_training, 0) + COALESCE(pc.apprenticeship_training_2yr, 0) + \r\n"
			+ "    COALESCE(pc.self_employment, 0) + COALESCE(pc.self_employment_2yr, 0) + \r\n"
			+ "    COALESCE(pc.higher_education, 0) + COALESCE(pc.higher_education_2yr, 0) AS TotalPlacement\r\n"
			+ "\r\n"
			+ "FROM iti\r\n"
			+ "LEFT JOIN dist_mst dm ON iti.dist_code = dm.dist_code\r\n"
			+ "LEFT JOIN admitted_counts ac ON iti.iti_code = ac.iti_code\r\n"
			+ "LEFT JOIN placement_counts pc ON iti.iti_code = pc.iti_code\r\n"
			+ "ORDER BY dm.dist_name, iti.iti_name;\r\n"
			+ " ",nativeQuery = true)
	List<PlacementProjection> getPlacementReport(@Param("previousYear") String previousYear, @Param("twoYearsBefore") String twoYearsBefore);


}
