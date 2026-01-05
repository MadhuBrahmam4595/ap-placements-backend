package com.iti.PlacementsBackend.service.plcmts;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.iti.PlacementsBackend.entity.plcmts.PlacementEntity;
import com.iti.PlacementsBackend.model.plcmts.DistReportAppreaAndOAModel;
import com.iti.PlacementsBackend.model.plcmts.DistReportHighEduModel;
import com.iti.PlacementsBackend.model.plcmts.DistReportJobAndOJModel;
import com.iti.PlacementsBackend.model.plcmts.DistReportSelfEmpModel;
import com.iti.PlacementsBackend.projection.UniversalProjection;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportApprProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportHigherEduProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportJobProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportSelfEmpProj;
import com.iti.PlacementsBackend.projection.plcmts.PlacementProjection;
import com.iti.PlacementsBackend.projection.plcmts.PlcmtNodalDistLevelCountReport;
import com.iti.PlacementsBackend.projection.plcmts.StateSkillDevelopmentPlanProj;

public interface PlacementService {

	PlacementEntity savePlacement(PlacementEntity placement);
	
	Optional<PlacementEntity> getById(Long pid);

	void deletePlcmtById(Long pid);

	PlacementEntity getPlcmtById(Long pid);

	List<PlacementEntity> getAll();

	// ITI Reports
	// Placement Type is JOB or OJ
	List<ItiReportJobProj> getItiReportJobAndOJ(String ptype, String entryBy);

	List<ItiReportJobProj> getItiReportJobAndOJAndYear(String ptype, String entryBy, String plcmtYear);

	// Placement Type is Apprenticeship or OA
	List<ItiReportApprProj> getItiReportApprAndOA(String ptype, String entryBy);

	List<ItiReportApprProj> getItiReportApprAndOAAndYear(String ptype, String entryBy, String plcmtYear);

	// Placement Type is Higher Education
	List<ItiReportHigherEduProj> getItiReportHigherEdu(String iticode, String ptype);

	List<ItiReportHigherEduProj> getItiReportHigherEduAndYear(String iticode, String ptype, String plcmtYear);

	// Placement Type is Self Employment
	List<ItiReportSelfEmpProj> getItiReportSelfEmp(String iticode, String ptype);

	List<ItiReportSelfEmpProj> getItiReportSelfEmpAndYear(String iticode, String ptype, String plcmtYear);

	// District Level Reports
	List<DistReportJobAndOJModel> getDistReportJobAndOJ(String ptype, String plcmtYear, String itiCode, String distCode)
			throws SQLException;

	List<DistReportAppreaAndOAModel> getDistReportAppreAndOA(String ptype, String plcmtYear, String itiCode,
			String distCode) throws SQLException;

	List<DistReportHighEduModel> getDistReportHighEdu(String ptype, String plcmtYear, String itiCode, String distCode)
			throws SQLException;

	List<DistReportSelfEmpModel> getDistReportSelfEmp(String ptype, String plcmtYear, String itiCode, String distCode)
			throws SQLException;

	// Nodal Reports
	List<PlcmtNodalDistLevelCountReport> getPlcmtNodalDistLevelCountReport();

	List<PlacementEntity> getByPtypeAndPassyear(String ptype, String passyear);

	List<PlacementEntity> getByPtype(String ptype);

	List<UniversalProjection> getDataGroupByScheduleId();

	List<ItiReportJobProj> getPlcmtsByPlcmtIdForJob(String scheduleId, String ptype);

	List<ItiReportApprProj> getPlcmtsByPlcmtIdForAppr(String scheduleId, String ptype);

	List<PlacementEntity> findByScheduleId(String scheduleId);

	List<PlacementEntity> getPlcmtByAdmnum(String adm_num);
	
	List<PlacementEntity> getJobAndApprData();
	
	List<PlacementEntity> findPlacementsByScheduleDate(String fromDate, String toDate);
	List<PlacementEntity> findPlacementsByScheduleDateAndPtype(String fromDate, String toDate, String ptype);
	
	//dashboard apis
	List<String> findDistinctItiCodesByPtype(String ptype);
	
	//yearwise placement report with gendercount
	List<StateSkillDevelopmentPlanProj> stateSkillDevelopmentPlanReport1(String year);
	StateSkillDevelopmentPlanProj stateSkillDevelopmentPlanReport2(String year,Integer tradecode);
	List<String> getDistinctYearOfAdmissons();
	
	//Placement report like current year + seniors all modules data
	 List<PlacementProjection> getPlacementReport(@Param("year") String year,@Param("itiType") String itiType);

	List<PlacementProjection> getPlacementReport(String year);

	

}
