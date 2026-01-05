package com.iti.PlacementsBackend.service.impl.plcmts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.entity.plcmts.PlacementEntity;
import com.iti.PlacementsBackend.entity.plcmts.PlcmtScheduleEntryEntity;
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
import com.iti.PlacementsBackend.repo.plcmts.PlacementRepo;
import com.iti.PlacementsBackend.service.master.ItiTradeMasterService;
import com.iti.PlacementsBackend.service.plcmts.PlacementService;
import com.iti.PlacementsBackend.service.plcmts.PlcmtScheduleEntryService;
import com.iti.PlacementsBackend.util.MyUtil;


@Service
public class PlacementServiceImpl implements PlacementService {

	@Autowired
	private PlacementRepo repo;
	@Autowired
	private PlcmtScheduleEntryService plcmtScheduleEntryService;
	@Autowired
	private ItiTradeMasterService itiTradeMasterService;

	public PlacementEntity savePlacement(PlacementEntity placement) {
		this.repo.save(placement);
		return placement;
	}

	public void deletePlcmtById(Long pid) {
		this.repo.deleteById(pid);
	}

	public PlacementEntity getPlcmtById(Long pid) {
		return (PlacementEntity) this.repo.findById(pid).get();
	}

	public List<PlacementEntity> getAll() {
		return this.repo.findAll();
	}

	@Override
	public List<ItiReportJobProj> getItiReportJobAndOJ(String ptype, String entryBy) {
		// TODO Auto-generated method stub
		return repo.getItiReportJobAndOJ(ptype, entryBy);
	}

	@Override
	public List<ItiReportJobProj> getItiReportJobAndOJAndYear(String ptype, String entryBy, String plcmtYear) {
		// TODO Auto-generated method stub
		return repo.getItiReportJobAndOJAndYear(ptype, entryBy, plcmtYear);
	}

	@Override
	public List<ItiReportApprProj> getItiReportApprAndOA(String ptype, String entryBy) {
		// TODO Auto-generated method stub
		return repo.getItiReportApprAndOA(ptype, entryBy);
	}

	@Override
	public List<ItiReportApprProj> getItiReportApprAndOAAndYear(String ptype, String entryBy, String plcmtYear) {
		// TODO Auto-generated method stub
		return repo.getItiReportApprAndOAAndYear(ptype, entryBy, plcmtYear);
	}

	@Override
	public List<ItiReportHigherEduProj> getItiReportHigherEdu(String iticode, String ptype) {
		// TODO Auto-generated method stub
		return repo.getItiReportHigherEdu(iticode, ptype);
	}

	@Override
	public List<ItiReportHigherEduProj> getItiReportHigherEduAndYear(String iticode, String ptype, String plcmtYear) {
		// TODO Auto-generated method stub
		return repo.getItiReportHigherEduAndYear(iticode, ptype, plcmtYear);
	}

	@Override
	public List<ItiReportSelfEmpProj> getItiReportSelfEmp(String iticode, String ptype) {
		// TODO Auto-generated method stub
		return repo.getItiReportSelfEmp(iticode, ptype);
	}

	@Override
	public List<ItiReportSelfEmpProj> getItiReportSelfEmpAndYear(String iticode, String ptype, String plcmtYear) {
		// TODO Auto-generated method stub
		return repo.getItiReportSelfEmpAndYear(iticode, ptype, plcmtYear);
	}

	@Override
	public List<DistReportJobAndOJModel> getDistReportJobAndOJ(String ptype, String plcmtYear, String itiCode,String distCode) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("getDistReportJobAndOJ ptype=>"+ptype);
		System.out.println("getDistReportJobAndOJ plcmtYear=>"+plcmtYear);
		System.out.println("getDistReportJobAndOJ itiCode=>"+itiCode);
		System.out.println("getDistReportJobAndOJ distCode=>"+distCode);
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DistReportJobAndOJModel> list = new ArrayList<>();
		try {
			MyUtil myUtil=new MyUtil();
			connection = myUtil.getConnection();
			 String query = "select a.adm_num,a.name,a.plcmt_year,a.pname_of_company,a.ppostname,a.psalary,a.phrno,\r\n" + 
			 		" b.statename,c.distname,paddress,a.schedule_id \r\n" + 
			 		" from placements.placements a \r\n" + 
			 		" inner join states_mast b on a.pstate=b.statecode \r\n" + 
			 		" inner join dists_statewise c on a.pdistrict=c.distcode \r\n" + 
			 		" where a.ptype='"+ptype+"' AND a.entry_distcode='"+distCode+"'";
			 
			 if(plcmtYear == null || plcmtYear.isEmpty()) {
			 }else { query = query + " and plcmt_year='"+plcmtYear+"'"; }
			 
			 if(itiCode == null || itiCode.isEmpty()) {
			 }else { query = query + " and entry_by='"+itiCode+"'"; }
			 
			 ps = connection.prepareStatement(query);
			 System.out.println("query=>"+ps);
			 rs = ps.executeQuery();
			 System.out.println("resultset count=>"+rs.getFetchSize());
			 
			 while(rs.next()) {
				 DistReportJobAndOJModel bean = new DistReportJobAndOJModel();
				 bean.setAdm_num(rs.getString("adm_num")==null ? "" :rs.getString("adm_num"));
				 bean.setName(rs.getString("name")==null ? "" :rs.getString("name"));
				 bean.setPlcmt_year(rs.getString("plcmt_year")==null ? "":rs.getString("plcmt_year"));
				 bean.setPname_of_company(rs.getString("pname_of_company")==null ? "":rs.getString("pname_of_company"));
				 bean.setPpostname(rs.getString("ppostname")==null ? "":rs.getString("ppostname"));
				 bean.setPsalary(rs.getString("psalary")==null? "":rs.getString("psalary"));
				 bean.setPhrno(rs.getString("phrno")==null ? "":rs.getString("phrno"));
				 bean.setStatename(rs.getString("statename")==null ? "":rs.getString("statename"));
				 bean.setDistname(rs.getString("distname")==null ? "":rs.getString("distname"));
				 bean.setPaddress(rs.getString("paddress")==null ? "":rs.getString("paddress"));
				 //bean.setSchedule(rs.getString("schedule")==null ? "":rs.getString("schedule"));
				 
				 if(rs.getString("schedule_id")==null) {
					 bean.setSchedule("");
				 }else if(rs.getString("schedule_id").equalsIgnoreCase("null")) {
					 bean.setSchedule("");
				 } else {
					 PlcmtScheduleEntryEntity findByPlcmtId = plcmtScheduleEntryService.findByPlcmtId(Long.valueOf(rs.getString("schedule_id")));
					 if(findByPlcmtId == null) {
						 bean.setSchedule("");
					 }else {
						 bean.setSchedule(findByPlcmtId.getScheduleDate()+"---"+findByPlcmtId.getScheduleDesc());
					 }
				 }
				 
				 list.add(bean);
			 }
			 System.out.println("list size=>"+list.size());
			
		} catch (Exception e) {
			System.out.println("exception=>"+e);
			e.printStackTrace();
			 if(connection != null) {
				 connection.close();
			 }
		}

		return list;
	}
	
	@Override
	public List<DistReportAppreaAndOAModel> getDistReportAppreAndOA(String ptype, String plcmtYear, String itiCode,
			String distCode) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("getDistReportAppreAndOA ptype=>"+ptype);
		System.out.println("getDistReportAppreAndOA plcmtYear=>"+plcmtYear);
		System.out.println("getDistReportAppreAndOA itiCode=>"+itiCode);
		System.out.println("getDistReportAppreAndOA distCode=>"+distCode);
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DistReportAppreaAndOAModel> list = new ArrayList<>();
		try {
			MyUtil myUtil=new MyUtil();
			connection = myUtil.getConnection();
			 String query = "select a.adm_num,a.name,a.plcmt_year,a.pname_of_company,a.ptrade,\r\n" + 
			 		"a.pstipendamt,a.phrno, a.paaprstartdate,a.paaprenddate, b.statename,c.distname,paddress,a.schedule_id \r\n" + 
			 		"from placements.placements a  \r\n" + 
			 		"inner join states_mast b on a.pstate=b.statecode  \r\n" + 
			 		"inner join dists_statewise c on a.pdistrict=c.distcode  \r\n" + 
			 		" where a.ptype='"+ptype+"' AND a.entry_distcode='"+distCode+"'";
			 
			 if(plcmtYear == null || plcmtYear.isEmpty()) {
			 }else { query = query + " and plcmt_year='"+plcmtYear+"'"; }
			 
			 if(itiCode == null || itiCode.isEmpty()) {
			 }else { query = query + " and entry_by='"+itiCode+"'"; }
			 
			 ps = connection.prepareStatement(query);
			 System.out.println("query=>"+ps);
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 DistReportAppreaAndOAModel bean = new DistReportAppreaAndOAModel();
				 bean.setAdm_num(rs.getString("adm_num")==null ? "" :rs.getString("adm_num"));
				 bean.setName(rs.getString("name")==null ? "" :rs.getString("name"));
				 bean.setPlcmt_year(rs.getString("plcmt_year")==null ? "":rs.getString("plcmt_year"));
				 bean.setPname_of_company(rs.getString("pname_of_company")==null ? "":rs.getString("pname_of_company"));
				 
				 if(rs.getString("ptrade")==null) {
					 bean.setTrade_name("");
				 }else {
					    ItiTradeMasterEntity findByTradeCode = itiTradeMasterService.findByTradeCode(Integer.parseInt(rs.getString("ptrade")));
					 if(findByTradeCode == null) {
						 bean.setTrade_name("");
					 }else {
						 bean.setTrade_name(findByTradeCode.getTradeName());
					 }
				 }
				 bean.setPstipendamt(rs.getString("pstipendamt")==null ? "" :rs.getString("pstipendamt"));
				 bean.setPhrno(rs.getString("phrno")==null ? "":rs.getString("phrno"));
				 bean.setPaaprstartdate(rs.getString("paaprstartdate")==null ? "":rs.getString("paaprstartdate"));
				 bean.setPaaprenddate(rs.getString("paaprenddate")==null ? "":rs.getString("paaprenddate"));
				 bean.setStatename(rs.getString("statename")==null ? "":rs.getString("statename"));
				 bean.setDistname(rs.getString("distname")==null ? "":rs.getString("distname"));
				 bean.setPaddress(rs.getString("paddress")==null ? "":rs.getString("paddress"));
				 //bean.setSchedule(rs.getString("schedule")==null ? "":rs.getString("schedule"));
				 
				 if(rs.getString("schedule_id")==null) {
					 bean.setSchedule("");
				 }
				 else if(rs.getString("schedule_id").equalsIgnoreCase("null")) {
					 bean.setSchedule("");
				 }
				 else {
					 PlcmtScheduleEntryEntity findByPlcmtId = plcmtScheduleEntryService.findByPlcmtId(Long.valueOf(rs.getString("schedule_id")));
					 if(findByPlcmtId == null) {
						 bean.setSchedule("");
					 }else {
						 bean.setSchedule(findByPlcmtId.getScheduleDate()+"---"+findByPlcmtId.getScheduleDesc());
					 }
				 }
				 
				 list.add(bean);
			 }
			 System.out.println("list size=>"+list.size());
			
		} catch (Exception e) {
			 if(connection != null) {
				 connection.close();
			 }
		}

		return list;
	}
	
	@Override
	public List<DistReportHighEduModel> getDistReportHighEdu(String ptype, String plcmtYear, String itiCode,
			String distCode) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("getDistReportHighEdu ptype=>"+ptype);
		System.out.println("getDistReportHighEdu plcmtYear=>"+plcmtYear);
		System.out.println("getDistReportHighEdu itiCode=>"+itiCode);
		System.out.println("getDistReportHighEdu distCode=>"+distCode);
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DistReportHighEduModel> list = new ArrayList<>();
		
		try {
			MyUtil myUtil=new MyUtil();
			connection = myUtil.getConnection();
			 String query = "select adm_num,name,plcmt_year,pcoursename,pclgname, \r\n" + 
			 		" b.statename,c.distname,paddress from placements.placements a \r\n" + 
			 		" inner join states_mast b on a.pstate=b.statecode \r\n" + 
			 		" inner join dists_statewise c on a.pdistrict=c.distcode \r\n" + 
			 		" where a.ptype='"+ptype+"' AND a.dist_code='"+distCode+"'";
			 
			 if(plcmtYear == null || plcmtYear.isEmpty()) {
			 }else { query = query + " and plcmt_year='"+plcmtYear+"'"; }
			 
			 if(itiCode == null || itiCode.isEmpty()) {
			 }else { query = query + " and entry_by='"+itiCode+"'"; }
			 
			 ps = connection.prepareStatement(query);
			 System.out.println("query=>"+ps);
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 DistReportHighEduModel bean = new DistReportHighEduModel();
				 bean.setAdm_num(rs.getString("adm_num")==null ? "" :rs.getString("adm_num"));
				 bean.setName(rs.getString("name")==null ? "" :rs.getString("name"));
				 bean.setPlcmt_year(rs.getString("plcmt_year")==null ? "":rs.getString("plcmt_year"));
				 bean.setPcoursename(rs.getString("pcoursename")==null ? "":rs.getString("pcoursename"));
				 bean.setPclgname(rs.getString("pclgname")==null ? "":rs.getString("pclgname"));
				 bean.setStatename(rs.getString("statename")==null ? "":rs.getString("statename"));
				 bean.setDistname(rs.getString("distname")==null ? "":rs.getString("distname"));
				 bean.setPaddress(rs.getString("paddress")==null ? "":rs.getString("paddress"));
				  
				 list.add(bean);
			 }
			 System.out.println("list size=>"+list.size());
			
		} catch (Exception e) {
			 if(connection != null) {
				 connection.close();
			 }
		}

		return list;
	}
	
	@Override
	public List<DistReportSelfEmpModel> getDistReportSelfEmp(String ptype, String plcmtYear, String itiCode,
			String distCode) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("getDistReportSelfEmp ptype=>"+ptype);
		System.out.println("getDistReportSelfEmp plcmtYear=>"+plcmtYear);
		System.out.println("getDistReportSelfEmp itiCode=>"+itiCode);
		System.out.println("getDistReportSelfEmp distCode=>"+distCode);
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DistReportSelfEmpModel> list = new ArrayList<>();
		
		try {
			MyUtil myUtil=new MyUtil();
			connection = myUtil.getConnection();
			 String query = "select adm_num,name,plcmt_year,pselfemp,pmonthincome, \r\n" + 
			 		"b.statename,c.distname,paddress from placements.placements a \r\n" + 
			 		" inner join states_mast b on a.pstate=b.statecode \r\n" + 
			 		" inner join dists_statewise c on a.pdistrict=c.distcode \r\n" + 
			 		"where a.dist_code='"+distCode+"' and a.ptype='"+ptype+"'";
			 
			 if(plcmtYear == null || plcmtYear.isEmpty()) {
			 }else { query = query + " and plcmt_year='"+plcmtYear+"'"; }
			 
			 if(itiCode == null || itiCode.isEmpty()) {
			 }else { query = query + " and entry_by='"+itiCode+"'"; }
			 
			 ps = connection.prepareStatement(query);
			 System.out.println("query=>"+ps);
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 DistReportSelfEmpModel bean = new DistReportSelfEmpModel();
				 bean.setAdm_num(rs.getString("adm_num")==null ? "" :rs.getString("adm_num"));
				 bean.setName(rs.getString("name")==null ? "" :rs.getString("name"));
				 bean.setPlcmt_year(rs.getString("plcmt_year")==null ? "":rs.getString("plcmt_year"));
				 bean.setPselfemp(rs.getString("pselfemp")==null ? "":rs.getString("pselfemp"));
				 bean.setPmonthincome(rs.getString("pmonthincome")==null ? "":rs.getString("pmonthincome"));
				 bean.setStatename(rs.getString("statename")==null ? "":rs.getString("statename"));
				 bean.setDistname(rs.getString("distname")==null ? "":rs.getString("distname"));
				 bean.setPaddress(rs.getString("paddress")==null ? "":rs.getString("paddress"));
				  
				 list.add(bean);
			 }
			 System.out.println("list size=>"+list.size());
			
		} catch (Exception e) {
			 if(connection != null) {
				 connection.close();
			 }
		}

		return list;
	}
	
	@Override
	public List<PlcmtNodalDistLevelCountReport> getPlcmtNodalDistLevelCountReport() {
		// TODO Auto-generated method stub
		return repo.getPlcmtNodalDistLevelCountReport();
	}

	@Override
	public List<PlacementEntity> getByPtypeAndPassyear(String ptype, String passyear) {
		// TODO Auto-generated method stub
		return repo.findByPtypeAndPassyear(ptype, passyear);
	}

	@Override
	public List<PlacementEntity> getByPtype(String ptype) {
		// TODO Auto-generated method stub
		return repo.findByPtype(ptype);
	}
	
	@Override
	public List<UniversalProjection> getDataGroupByScheduleId() {
		// TODO Auto-generated method stub
		return repo.getDataGroupByScheduleId();
	}
	
	@Override
	public List<ItiReportJobProj> getPlcmtsByPlcmtIdForJob(String scheduleId, String ptype) {
		// TODO Auto-generated method stub
		return repo.getPlcmtsByPlcmtIdForJob(scheduleId, ptype);
	}
	
	@Override
	public List<ItiReportApprProj> getPlcmtsByPlcmtIdForAppr(String scheduleId, String ptype) {
		// TODO Auto-generated method stub
		return repo.getPlcmtsByPlcmtIdForAppr(scheduleId, ptype);
	}
	
	@Override
	public List<PlacementEntity> findByScheduleId(String scheduleId) {
		// TODO Auto-generated method stub
		return repo.findByScheduleId(scheduleId);
	}

	@Override
	public List<PlacementEntity> getPlcmtByAdmnum(String adm_num) {
		// TODO Auto-generated method stub
		return repo.getPlcmtByAdmnum(adm_num);
	}

	@Override
	public Optional<PlacementEntity> getById(Long pid) {
		// TODO Auto-generated method stub
		return repo.findById(pid);
	}

	@Override
	public List<PlacementEntity> getJobAndApprData() {
		// TODO Auto-generated method stub
		return repo.getJobAndApprData();
	}
	
	@Override
	public List<PlacementEntity> findPlacementsByScheduleDate(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return repo.findPlacementsByScheduleDate(fromDate, toDate);
	}
	 @Override
	public List<PlacementEntity> findPlacementsByScheduleDateAndPtype(String fromDate, String toDate, String ptype) {
		// TODO Auto-generated method stub
		return repo.findPlacementsByScheduleDateAndPtype(fromDate, toDate, ptype);
	}
	 
	@Override
	public List<String> findDistinctItiCodesByPtype(String ptype) {
		// TODO Auto-generated method stub
		return repo.findDistinctItiCodesByPtype(ptype);
	}

	@Override
	public List<StateSkillDevelopmentPlanProj> stateSkillDevelopmentPlanReport1(String year) {
		// TODO Auto-generated method stub
		return repo.stateSkillDevelopmentPlanReport1(year);
	}
	@Override
	public StateSkillDevelopmentPlanProj stateSkillDevelopmentPlanReport2(String year, Integer tradecode) {
		// TODO Auto-generated method stub
		return repo.stateSkillDevelopmentPlanReport2(year, tradecode);
	}
	@Override
	public List<String> getDistinctYearOfAdmissons() {
		// TODO Auto-generated method stub
		return repo.getDistinctYearOfAdmissons();
	}

	@Override
	public List<PlacementProjection> getPlacementReport(String year, String itiType) {
        int previousYear = Integer.parseInt(year) - 1;
        int twoYearsBefore = Integer.parseInt(year) - 2;
        return repo.getPlacementReport(String.valueOf(previousYear), String.valueOf(twoYearsBefore),itiType);
    }

	@Override
	public List<PlacementProjection> getPlacementReport(String year) {
		// TODO Auto-generated method stub
		 int previousYear = Integer.parseInt(year) - 1;
	        int twoYearsBefore = Integer.parseInt(year) - 2;
	        return repo.getPlacementReport(String.valueOf(previousYear), String.valueOf(twoYearsBefore));
	}


	
}

