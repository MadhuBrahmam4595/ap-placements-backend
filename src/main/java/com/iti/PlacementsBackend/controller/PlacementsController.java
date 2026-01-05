package com.iti.PlacementsBackend.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iti.PlacementsBackend.entity.AdmissionsEntity;
import com.iti.PlacementsBackend.entity.master.DistsStatewise;
import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.entity.master.OldDistMasterEntity;
import com.iti.PlacementsBackend.entity.master.States_mastEntity;
import com.iti.PlacementsBackend.entity.plcmts.PlacementEntity;
import com.iti.PlacementsBackend.entity.plcmts.PlcmtScheduleEntryEntity;
import com.iti.PlacementsBackend.model.AdmissionModel;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.model.plcmts.AjaxResponseBody;
import com.iti.PlacementsBackend.model.plcmts.DistReportAppreaAndOAModel;
import com.iti.PlacementsBackend.model.plcmts.DistReportHighEduModel;
import com.iti.PlacementsBackend.model.plcmts.DistReportJobAndOJModel;
import com.iti.PlacementsBackend.model.plcmts.DistReportSelfEmpModel;
import com.iti.PlacementsBackend.model.plcmts.PlacementsModel;
import com.iti.PlacementsBackend.model.plcmts.Plcmt_Report_Bean;
import com.iti.PlacementsBackend.model.plcmts.ResponseRest;
import com.iti.PlacementsBackend.projection.DistPlcmtSchedulesProj;
import com.iti.PlacementsBackend.projection.GetNextValProje;
import com.iti.PlacementsBackend.projection.UniversalProjection;
import com.iti.PlacementsBackend.projection.plcmts.GetAdmDetailsByName;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportApprProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportHigherEduProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportJobProj;
import com.iti.PlacementsBackend.projection.plcmts.ItiReportSelfEmpProj;
import com.iti.PlacementsBackend.projection.plcmts.PlacementProjection;
import com.iti.PlacementsBackend.projection.plcmts.PlcmtNodalDistLevelCountReport;
import com.iti.PlacementsBackend.projection.plcmts.PlcmtYearWiseReportModel;
import com.iti.PlacementsBackend.repo.plcmts.PlacementRepo;
import com.iti.PlacementsBackend.service.AdmissionsService;
import com.iti.PlacementsBackend.service.master.DistsStatewiseService;
import com.iti.PlacementsBackend.service.master.ItiService;
import com.iti.PlacementsBackend.service.master.ItiTradeMasterService;
import com.iti.PlacementsBackend.service.master.OldDistMasterService;
import com.iti.PlacementsBackend.service.master.States_mastService;
import com.iti.PlacementsBackend.service.plcmts.PlacementService;
import com.iti.PlacementsBackend.service.plcmts.PlcmtScheduleEntryService;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/plcmt")
@CrossOrigin(origins = { "*" })
public class PlacementsController {

	private static final Logger logger = LoggerFactory.getLogger(PlacementsController.class);

	@Autowired
	private PlcmtScheduleEntryService plcmtScheduleEntryService;
	@Autowired
	private OldDistMasterService oldDistMasterService;
	@Autowired
	private ItiService itiService;
	@Autowired
	private PlacementService placementService;
	@Autowired
	private PlacementRepo placementRepo;
	@Autowired
	private MyUtil myUtil;
	@Autowired
	private States_mastService states_mastService;
	@Autowired
	private DistsStatewiseService distsStatewiseService;
	@Autowired
	private ItiTradeMasterService itiTradeMasterService;
	@Autowired
	private AdmissionsService admissionsService;

	// Schedule date wise data
	@PostMapping("plcmtScheduleDatewiseReport")
	public ResponseEntity<?> plcmtScheduleDatewiseReport(@RequestBody PlacementsModel placementsModel) {
		logger.info("placementsModel=>" + placementsModel.toString());

		List<PlacementEntity> findPlacementsByScheduleDateAndPtype = placementService
				.findPlacementsByScheduleDateAndPtype(placementsModel.getFromDate(), placementsModel.getToDate(),
						placementsModel.getPtype());
		logger.info("findPlacementsByScheduleDateAndPtype=>" + findPlacementsByScheduleDateAndPtype.size());

		return new ResponseEntity<List<PlacementEntity>>(findPlacementsByScheduleDateAndPtype, HttpStatus.OK);
	}

	// Placements Schedulewise Data Report
	@GetMapping("getSchedulesCountDistwise")
	public List<UniversalProjection> getSchedulesCountDistwise() {
		return plcmtScheduleEntryService.getSchedulesCountDistwise();
	}

	@GetMapping("getPlcmtSchedulesInDist")
	public ResponseEntity<?> getPlcmtSchedulesInDist(String distCode, String scheduleType) {
		System.out.println("getPlcmtSchedulesInDist=>distCode=>" + distCode);
		System.out.println("getPlcmtSchedulesInDist=>scheduleType=>" + scheduleType);

		try {
			if (scheduleType.equalsIgnoreCase("distAllSchedules")) {
				List<PlcmtScheduleEntryEntity> list = plcmtScheduleEntryService.findByDistCode(distCode);

				for (PlcmtScheduleEntryEntity bean : list) {
					Optional<OldDistMasterEntity> dist = oldDistMasterService.getByDistCode(bean.getDistCode());
					if (dist.isPresent()) {
						bean.setDistName(dist.get().getDist_name());
					} else {
						bean.setDistName("");
					}

					Optional<ItiEntity> itis = itiService.getByItiCodee(bean.getScheduleLocation());
					if (itis.isPresent()) {
						bean.setItiName(itis.get().getItiName());
					} else {
						bean.setItiName("");
					}
				}
				List<UniversalProjection> dataGroupByScheduleId = placementService.getDataGroupByScheduleId();
				System.out.println("dataGroupByScheduleId=>" + dataGroupByScheduleId.size());
				for (PlcmtScheduleEntryEntity bean : list) {
					for (UniversalProjection data : dataGroupByScheduleId) {
						if (data.getStrCol1() == null) {
							bean.setPlcmtsCount("");
						} else {
							bean.setPlcmtsCount(String.valueOf(data.getLongCol1()));
						}
					}
				}

				return new ResponseEntity<List<PlcmtScheduleEntryEntity>>(list, HttpStatus.OK);
			}
			if (scheduleType.equalsIgnoreCase("Apprenticeship") || scheduleType.equalsIgnoreCase("Job")) {
				List<PlcmtScheduleEntryEntity> list = plcmtScheduleEntryService.findByDistCodeAndScheduleType(distCode,
						scheduleType);
				for (PlcmtScheduleEntryEntity bean : list) {
					Optional<OldDistMasterEntity> dist = oldDistMasterService.getByDistCode(bean.getDistCode());
					if (dist.isPresent()) {
						bean.setDistName(dist.get().getDist_name());
					} else {
						bean.setDistName("");
					}

					Optional<ItiEntity> itis = itiService.getByItiCodee(bean.getScheduleLocation());
					if (itis.isPresent()) {
						bean.setItiName(itis.get().getItiName());
					} else {
						bean.setItiName("");
					}

					List<PlacementEntity> findByScheduleId = placementService
							.findByScheduleId(String.valueOf(bean.getPlcmtId()));
					System.out.println("=============="
							+ String.valueOf(bean.getScheduleId() + "====================" + findByScheduleId.size()));
					bean.setPlcmtsCount(String.valueOf(findByScheduleId.size()));
				}
				// List<UniversalProjection> dataGroupByScheduleId =
				// placementService.getDataGroupByScheduleId();

//				for(PlcmtScheduleEntryEntity bean:list) {
//					for(UniversalProjection data:dataGroupByScheduleId) {
//						System.out.println("dataGroupByScheduleId=>"+data.getStrCol1());
//						if(data.getStrCol1() == null) {
//							bean.setPlcmtsCount("");
//						}else {
//							bean.setPlcmtsCount(String.valueOf(data.getLongCol1()));
//							System.out.println("getPlcmtsCount=>"+bean.getPlcmtsCount());
//						}
//					}
//				}

				return new ResponseEntity<List<PlcmtScheduleEntryEntity>>(list, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Something went wrong while getting data.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("No data found.", HttpStatus.NOT_FOUND);

	}

	@GetMapping("getPlcmtsByPlcmtId")
	public ResponseEntity<?> getPlcmtsByPlcmtId(String scheduleId, String ptype) {
		System.out.println("getPlcmtsByPlcmtId=>scheduleId=>" + scheduleId);
		System.out.println("getPlcmtsByPlcmtId=>ptype=>" + ptype);

		if (ptype.equalsIgnoreCase("Job")) {
			List<ItiReportJobProj> plcmtsByPlcmtIdForJob = placementService.getPlcmtsByPlcmtIdForJob(scheduleId, ptype);
			System.out.println("plcmtsByPlcmtIdForJob=>" + plcmtsByPlcmtIdForJob.size());
			return new ResponseEntity<List<ItiReportJobProj>>(plcmtsByPlcmtIdForJob, HttpStatus.OK);
		}
		if (ptype.equalsIgnoreCase("Apprenticeship")) {
			List<ItiReportApprProj> plcmtsByPlcmtIdForAppr = placementService.getPlcmtsByPlcmtIdForAppr(scheduleId,
					ptype);
			System.out.println("plcmtsByPlcmtIdForAppr=>" + plcmtsByPlcmtIdForAppr.size());
			return new ResponseEntity<List<ItiReportApprProj>>(plcmtsByPlcmtIdForAppr, HttpStatus.OK);
		}

		return null;
	}

	// Placements State Report
	@GetMapping("getPlcmtNodalDistLevelCountReport")
	public ResponseEntity<?> getPlcmtNodalDistLevelCountReport() {

		List<PlcmtNodalDistLevelCountReport> plcmtNodalDistLevelCountReport = placementService
				.getPlcmtNodalDistLevelCountReport();
		System.out.println("plcmtNodalDistLevelCountReport=>" + plcmtNodalDistLevelCountReport.size());

		return new ResponseEntity<List<PlcmtNodalDistLevelCountReport>>(plcmtNodalDistLevelCountReport, HttpStatus.OK);
	}

	@GetMapping("getDistReportJobAndOJ")
	List<DistReportJobAndOJModel> getDistReportJobAndOJ(String ptype, String plcmtYear, String itiCode, String distCode)
			throws SQLException {
		List<DistReportJobAndOJModel> distReportJobAndOJ = placementService.getDistReportJobAndOJ(ptype, plcmtYear,
				itiCode, distCode);
		return distReportJobAndOJ;
	}

	@GetMapping("getDistReportAppreAndOA")
	List<DistReportAppreaAndOAModel> getDistReportAppreAndOA(String ptype, String plcmtYear, String itiCode,
			String distCode) throws SQLException {
		List<DistReportAppreaAndOAModel> distReportAppreAndOA = placementService.getDistReportAppreAndOA(ptype,
				plcmtYear, itiCode, distCode);
		return distReportAppreAndOA;
	}

	@GetMapping("getDistReportHighEdu")
	List<DistReportHighEduModel> getDistReportHighEdu(String ptype, String plcmtYear, String itiCode, String distCode)
			throws SQLException {
		List<DistReportHighEduModel> distReportAppreAndOA = placementService.getDistReportHighEdu(ptype, plcmtYear,
				itiCode, distCode);
		return distReportAppreAndOA;
	}

	@GetMapping("getDistReportSelfEmp")
	List<DistReportSelfEmpModel> getDistReportSelfEmp(String ptype, String plcmtYear, String itiCode, String distCode)
			throws SQLException {
		List<DistReportSelfEmpModel> distReportAppreAndOA = placementService.getDistReportSelfEmp(ptype, plcmtYear,
				itiCode, distCode);
		return distReportAppreAndOA;
	}

	// Year wise count report
	@PostMapping("getPlcmtYearWiseReport")
	public ResponseEntity<?> getPlcmtYearWiseReport(HttpServletRequest httpServletRequest) {
		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		if (!claimsFromToken.getRoleId().equalsIgnoreCase("2") && !claimsFromToken.getRoleId().equalsIgnoreCase("10")
				&& !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
			String msg = "Your Not Authorized to this Page";
			ResponseRest resp = new ResponseRest();
			resp.setMsg(msg);
			return new ResponseEntity<ResponseRest>(resp, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<PlcmtYearWiseReportModel>>(placementRepo.getPlcmtYearWiseReport(),
					HttpStatus.OK);
		}
	}

	@GetMapping("getPlcmtsByPtypeAndPassyear")
	List<PlacementEntity> findByPtypeAndPassyear(String ptype, String passyear) throws SQLException {
		System.out.println("getPlcmtsByPtypeAndPassyear=ptype=>" + ptype);
		System.out.println("getPlcmtsByPtypeAndPassyear=passyear=>" + passyear);

		List<PlacementEntity> byPtypeAndPassyear = new ArrayList<>();
		try {
			if (passyear.equalsIgnoreCase("Total")) {
				byPtypeAndPassyear = placementService.getByPtype(ptype);
			} else {
				byPtypeAndPassyear = placementService.getByPtypeAndPassyear(ptype, passyear);
			}
			System.out.println("byPtypeAndPassyear=list size=>" + byPtypeAndPassyear.size());

			Optional<DistsStatewise> dist = null;
			States_mastEntity state = null;
			PlcmtScheduleEntryEntity findByPlcmtId = null;
			ItiTradeMasterEntity findByTradeCode = null;

			for (PlacementEntity bean : byPtypeAndPassyear) {
				System.out.println("bean=>" + bean.toString());

				// placements state
				state = states_mastService.getByStatecode(bean.getPstate());
				System.out.println("state=>" + state.toString());
				// placements distirict
				dist = distsStatewiseService.getDistStatewise(bean.getPdistrict());
				System.out.println("dist=>" + dist.toString());

				// entry distcode
				Optional<OldDistMasterEntity> byDistCode = oldDistMasterService.getByDistCode(bean.getEntry_distcode());
				if (byDistCode.isPresent()) {
					bean.setEntry_distcode(byDistCode.get().getDist_name());
				}
				// entry iticode
				Optional<ItiEntity> byItiCodee = itiService.getByItiCodee(bean.getEntry_by());
				if (byItiCodee.isPresent()) {
					bean.setEntry_by(byItiCodee.get().getItiName());
				}

				System.out.println("bean======before=====>" + bean.toString());

				if (bean.getTrade_code() == null || bean.getTrade_code().equalsIgnoreCase("null")) {
					bean.setStdTradeName("");
				} else {
					ItiTradeMasterEntity findByTradeCode2 = itiTradeMasterService
							.findByTradeCode(Integer.parseInt(bean.getTrade_code()));
					System.out.println("findByTradeCode2=>" + findByTradeCode2);
					if (findByTradeCode2 == null) {
						System.out.println("================================");
						bean.setStdTradeName("");
					} else {
						bean.setStdTradeName(findByTradeCode2.getTradeName());
					}
				}

				bean.setPstate(state.getStatename());
				// bean.setPdistrict(dist.get().getDistname());

				if (bean.getPtype().equalsIgnoreCase("Job") || bean.getPtype().equalsIgnoreCase("Apprenticeship")) {

					if (bean.getScheduleId() == null) {
						bean.setScheduleId("");
					} else if (bean.getScheduleId().equalsIgnoreCase("null")) {
						bean.setScheduleId("");
					} else {
						System.out.println("bean.getScheduleId==>" + bean.getScheduleId());
						findByPlcmtId = plcmtScheduleEntryService.findByPlcmtId(Long.valueOf(bean.getScheduleId()));
						System.out.println("findByPlcmtId==>" + findByPlcmtId);

						String itiName = myUtil.getItiNameByItiCode(findByPlcmtId.getScheduleLocation());

						if (findByPlcmtId.getScheduleDesc() == null || findByPlcmtId.getScheduleDesc().isEmpty()
								|| findByPlcmtId.getScheduleDesc().equalsIgnoreCase("null")) {
							bean.setScheduleId(itiName + "---" + findByPlcmtId.getScheduleDate() + "---");
						} else {
							bean.setScheduleId(itiName + "---" + findByPlcmtId.getScheduleDate() + "---"
									+ findByPlcmtId.getScheduleDesc());
						}
					}

				}

				if (bean.getPtype().equalsIgnoreCase("OJ") || bean.getPtype().equalsIgnoreCase("OA")) {

					if (bean.getPtrade() == null) {
						bean.setPtrade("");
					} else if (bean.getPtrade().equalsIgnoreCase("null") || bean.getPtrade().equalsIgnoreCase("")) {
						bean.setPtrade("");
					} else {
						findByTradeCode = itiTradeMasterService.findByTradeCode(Integer.parseInt(bean.getPtrade()));
						bean.setPtrade(findByTradeCode.getTradeName());
					}
					String itiName = myUtil.getItiNameByItiCode(bean.getEntry_by());
					bean.setScheduleId(itiName);
				}

				if (bean.getPtype().equalsIgnoreCase("HigherEducation")
						|| bean.getPtype().equalsIgnoreCase("SelfEmployment")) {
					String itiName = myUtil.getItiNameByItiCode(bean.getIti_code());
					bean.setScheduleId(itiName);
				}

				if (bean.getPlcmtYear() == null) {
					bean.setPlcmtYear("");
				} else if (bean.getPlcmtYear().equalsIgnoreCase("null")) {
					bean.setPlcmtYear("");
				}
				System.out.println("bean=====after======>" + bean.toString());
			}
			System.out.println("byPtypeAndPassyear list size=>" + byPtypeAndPassyear.size());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byPtypeAndPassyear;
	}

	// dist report
	@PostMapping({ "getAllPlcmts" })
	public ResponseEntity<?> getAllPlcmts(HttpServletRequest httpServletRequest) throws SQLException {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("4") && !claimsFromToken.getRoleId().equalsIgnoreCase("10")
				&& !claimsFromToken.getRoleId().equalsIgnoreCase("2")
				&& !claimsFromToken.getRoleId().equalsIgnoreCase("3")) {
			return new ResponseEntity((MultiValueMap) null, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println("roleId=>4");
			List<PlacementEntity> listPlacements = this.placementRepo.getPlcmtEntryBy(claimsFromToken.getInsCode());
			List<PlacementEntity> listPlacements2 = new ArrayList();

			PlacementEntity lp;
			for (Iterator var6 = listPlacements.iterator(); var6.hasNext(); listPlacements2.add(lp)) {
				lp = (PlacementEntity) var6.next();
				if (lp.getPtype().equalsIgnoreCase("A")) {
					lp.setTrade_name(this.myUtil.getTradeName(lp.getTrade_code()));
				}
			}

			System.out.println("listPlacements = > " + listPlacements2.size());
			return new ResponseEntity(listPlacements2, HttpStatus.OK);
		}
	}

	// ITI Reports
	// Placement Type is JOB and OJ
	@GetMapping("getItiReportJob")
	public List<ItiReportJobProj> getItiReportJob(String ptype, String entryBy, String plcmtYear) {
		System.out.println("getItiReportJob");
		System.out.println("getItiReportJob ptype=>" + ptype);
		System.out.println("getItiReportJob entryBy=>" + entryBy);
		System.out.println("getItiReportJob plcmtYear=>" + plcmtYear);
		if (plcmtYear == null || plcmtYear.isEmpty()) {
			System.out.println("plcmtYear is null");
			return placementService.getItiReportJobAndOJ(ptype, entryBy);
		} else {
			return placementService.getItiReportJobAndOJAndYear(ptype, entryBy, plcmtYear);
		}

	}

	// Placement Type is Apprenticeship and OA
	@GetMapping("getItiReportAppr")
	public List<ItiReportApprProj> getItiReportAppr(String ptype, String entryBy, String plcmtYear) {
		System.out.println("getItiReportAppr");
		System.out.println("getItiReportAppr ptype=>" + ptype);
		System.out.println("getItiReportAppr entryBy=>" + entryBy);
		System.out.println("getItiReportAppr plcmtYear=>" + plcmtYear);

		if (plcmtYear == null || plcmtYear.isEmpty()) {
			System.out.println("plcmtYear is null");
			return placementService.getItiReportApprAndOA(ptype, entryBy);
		} else {
			return placementService.getItiReportApprAndOAAndYear(ptype, entryBy, plcmtYear);
		}
	}

	// Placement Type is Higher Education
	@GetMapping("getItiReportHighEdu")
	public List<ItiReportHigherEduProj> getItiReportHighEdu(String ptype, String iticode, String plcmtYear) {
		System.out.println("getItiReportHighEdu");
		System.out.println("getItiReportHighEdu ptype=>" + ptype);
		System.out.println("getItiReportHighEdu iticode=>" + iticode);
		System.out.println("getItiReportHighEdu plcmtYear=>" + plcmtYear);

		if (plcmtYear == null || plcmtYear.isEmpty()) {
			System.out.println("plcmtYear is null");
			return placementService.getItiReportHigherEdu(iticode, ptype);
		} else {
			return placementService.getItiReportHigherEduAndYear(iticode, ptype, plcmtYear);
		}

	}

	// Placement Type is Self Employement
	@GetMapping("getItiReportSelfEmp")
	public List<ItiReportSelfEmpProj> getItiReportSelfEmp(String ptype, String iticode, String plcmtYear) {
		System.out.println("getItiReportHighEdu");
		System.out.println("getItiReportHighEdu ptype=>" + ptype);
		System.out.println("getItiReportHighEdu iticode=>" + iticode);
		System.out.println("getItiReportHighEdu plcmtYear=>" + plcmtYear);

		if (plcmtYear == null || plcmtYear.isEmpty()) {
			System.out.println("plcmtYear is null");
			return placementService.getItiReportSelfEmp(iticode, ptype);
		} else {
			return placementService.getItiReportSelfEmpAndYear(iticode, ptype, plcmtYear);
		}

	}

	// plcmt schedule entry
	@GetMapping("getDistPlcmtSchedules")
	List<DistPlcmtSchedulesProj> getDistPlcmtSchedules(String distCode) {
		return plcmtScheduleEntryService.getDistPlcmtSchedules(distCode);
	}

	@PostMapping("savePlcmtScheduleEntry")
	public ResponseEntity<?> savePlcmtScheduleEntry(@RequestBody PlcmtScheduleEntryEntity plcmtScheduleEntry,
			HttpServletRequest servletRequest) {
		System.out.println("plcmtScheduleEntry=>" + plcmtScheduleEntry.toString());

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(servletRequest);
		System.out.println("claimsFromToken=>" + claimsFromToken.toString());

		if (claimsFromToken.getRoleId().equalsIgnoreCase("3")) {
			GetNextValProje proje = plcmtScheduleEntryService.getNextVal();
			plcmtScheduleEntry.setPlcmtId(Long.valueOf(proje.getNextval()));

			plcmtScheduleEntry.setDistCode(claimsFromToken.getInsCode());
			plcmtScheduleEntry.setEntryBy(claimsFromToken.getInsCode());
			plcmtScheduleEntry.setEntryDateTime(LocalDateTime.now());

			PlcmtScheduleEntryEntity savedPlcmtScheduleEntry = (PlcmtScheduleEntryEntity) plcmtScheduleEntryService
					.savePSE(plcmtScheduleEntry);
			String msg = "Schedule Saved Successfully with Placement ID: " + savedPlcmtScheduleEntry.getPlcmtId()
					+ ". Save this id for entering placements entry.";
			ResponseRest resp = new ResponseRest();
			resp.setMsg(msg);
			return new ResponseEntity<ResponseRest>(resp, HttpStatus.OK);
		} else {
			String msg = "Your Not Authorized to this Page";
			ResponseRest resp = new ResponseRest();
			resp.setMsg(msg);
			return new ResponseEntity<ResponseRest>(resp, HttpStatus.NOT_FOUND);
		}
	}

	// plcmt entry
	@PostMapping({ "getCandAdmInfoByLikeName" })
	public ResponseEntity<?> getCandAdmInfoByLikeName(@RequestParam("name") String name) {
		System.out.println("/api/plcmt/reports/savePlcmtScheduleEntry" + name);
		List<GetAdmDetailsByName> adm_nums = admissionsService.getByNameslikes(name);
		System.out.println("adm_nums=>" + adm_nums.size());
		return new ResponseEntity<List<GetAdmDetailsByName>>(adm_nums, HttpStatus.OK);
	}

	@PostMapping("getByAdmNum")
	public ResponseEntity<?> getByAdmNum(@RequestParam("admNum") String admNum) {
		System.out.println("/api/plcmt/reports/getByAdmNum" + admNum);

		AdmissionsEntity admNumData = admissionsService.getByAdmNum(admNum);
		// System.out.println("admNumData=>" + admNumData.toString());

		AdmissionModel admissionModel = new AdmissionModel();
		List<OldDistMasterEntity> oldDistMaster = oldDistMasterService.getAll();
		System.out.println("oldDistMaster=>size=>" + oldDistMaster.size());

		List<DistsStatewise> distsStateWise = distsStatewiseService.getAllDistStatewise();
		System.out.println("distsStateWise=>size=>" + distsStateWise.size());

		List<States_mastEntity> states = states_mastService.getAllStates();
		System.out.println("states=>size=>" + states.size());

		List<ItiTradeMasterEntity> trades = itiTradeMasterService.findAllByOrderByTradeNameAsc();
		System.out.println("trades=>size=>" + trades.size());

		List<ItiEntity> itis = itiService.getAllItis();
		// List<ItiCodeAndNameProj> itis = itiService.getItiCodeAndName();
		System.out.println("itis=>size=>" + itis.size());

		System.out.println("getIti_code=>" + admNumData.getIti_code());

		admissionModel.setAdm_num(admNumData.getAdmNum());
		admissionModel.setName(admNumData.getName());
		admissionModel.setIti_code(admNumData.getIti_code());

		Optional<ItiEntity> itiNames = itis.stream().filter((a) -> {
			return a.getItiCode().equals(admNumData.getIti_code());
		}).findFirst();
		if (itiNames.isPresent()) {
			admissionModel.setIti_name(((ItiEntity) itiNames.get()).getItiName());
		}
		System.out.println("itiNames" + ((ItiEntity) itiNames.get()).getItiName());

		admissionModel.setDist_code(admNumData.getDist_code());
		Optional<OldDistMasterEntity> oldDists = oldDistMaster.stream().filter((a) -> {
			return a.getDist_code().equals(admNumData.getDist_code());
		}).findFirst();
		if (oldDists.isPresent()) {
			admissionModel.setDist_name(((OldDistMasterEntity) oldDists.get()).getDist_name());
		}

		System.out.println("oldDists" + ((OldDistMasterEntity) oldDists.get()).getDist_name());
		admissionModel.setYear_of_admission(admNumData.getYear_of_admission());
		admissionModel.setTrade_code(String.valueOf(admNumData.getTrade_code()));
		Optional<ItiTradeMasterEntity> trade = trades.stream().filter((t) -> {
			return t.getTradeCode().equals(admNumData.getTrade_code());
		}).findFirst();
		if (trade.isPresent()) {
			admissionModel.setTrade_name(((ItiTradeMasterEntity) trade.get()).getTradeName());
		}

		System.out.println("trade" + ((ItiTradeMasterEntity) trade.get()).getTradeName());
		AjaxResponseBody arb = new AjaxResponseBody();
		arb.setResult(admissionModel);
		arb.setDists(distsStateWise);
		arb.setStates(states);
		arb.setTrades(trades);
		System.out.println("adm_nums=>" + admNumData.toString());
		return new ResponseEntity<AjaxResponseBody>(arb, HttpStatus.OK);
	}

	@PostMapping("getAllByPlcmtType")
	public ResponseEntity<?> getAllByPlcmtType(@RequestParam("scheduleType") String scheduleType,
			@RequestParam("ins_code") String ins_code) {
		System.out.println("scheduleType=>" + scheduleType);
		System.out.println("ins_code=>" + ins_code);
		List<PlcmtScheduleEntryEntity> plcmtScheduleEntry = plcmtScheduleEntryService
				.findByScheduleTypeAndScheduleLocation(scheduleType, ins_code);
		System.out.println("plcmtScheduleEntry list size=>" + plcmtScheduleEntry.size());

		return new ResponseEntity<List<PlcmtScheduleEntryEntity>>(plcmtScheduleEntry, HttpStatus.OK);
	}

	@PostMapping("getCandPlcmtDetails")
	public ResponseEntity<?> getCandPlcmtDetails(@RequestParam("admNum") String admNum) {
		System.out.println("scheduleType=>" + admNum);
		List<PlacementEntity> plcmt = placementService.getPlcmtByAdmnum(admNum);
		return new ResponseEntity<List<PlacementEntity>>(plcmt, HttpStatus.OK);
	}

	@PostMapping("savePlcmtDetails")
	public ResponseEntity<?> savePlcmtDetails(@RequestBody Plcmt_Report_Bean bean, HttpServletRequest request) {
		System.out.println("Plcmt_Report_Bean=>" + bean.toString());

		PlacementEntity plcmtBean = new PlacementEntity();
		plcmtBean.setEntry_by(bean.getEntry_by());
		plcmtBean.setPtype(bean.getPtype());
		plcmtBean.setAdm_num(bean.getAdm_num());
		plcmtBean.setDist_name(bean.getDist_name());
		plcmtBean.setIti_name(bean.getIti_name());
		plcmtBean.setName(bean.getName());
		plcmtBean.setPassmonth(bean.getPassmonth());
		plcmtBean.setPassyear(bean.getPassyear());
		plcmtBean.setYear_of_admission(bean.getYear_of_admission());
		plcmtBean.setTrade_code(bean.getTrade_code());
		plcmtBean.setTrade_name(bean.getTrade_name());
		plcmtBean.setDist_code(bean.getDist_code());
		plcmtBean.setPstate(bean.getPstate());
		plcmtBean.setPaddress(bean.getPaddress());
		plcmtBean.setPdistrict(Integer.parseInt(bean.getPdistrict()));
		plcmtBean.setIti_code(bean.getIti_code());
		plcmtBean.setPlcmtYear(bean.getPlcmtYear());
		String distCode = this.myUtil.getEntryDistCode(bean.getEntry_by());
		if (bean.getPtype().equalsIgnoreCase("HigherEducation")) {
			plcmtBean.setPclgname(bean.getPclgname());
			plcmtBean.setPcoursename(bean.getPcoursename());
			plcmtBean.setEntry_distcode(distCode);
		}

		if (bean.getPtype().equalsIgnoreCase("Job")) {
			plcmtBean.setPname_of_company(bean.getPname_of_company());
			plcmtBean.setPpostname(bean.getPpostname());
			plcmtBean.setPsalary(bean.getPsalary());
			plcmtBean.setPhrno(bean.getPhrno());
			plcmtBean.setScheduleId(bean.getScheduleId());
			plcmtBean.setPlcmtId(Long.valueOf(bean.getScheduleId()));
			plcmtBean.setEntry_distcode(distCode);
		}

		if (bean.getPtype().equalsIgnoreCase("OJ")) {
			plcmtBean.setPname_of_company(bean.getPname_of_company());
			plcmtBean.setPpostname(bean.getPpostname());
			plcmtBean.setPsalary(bean.getPsalary());
			plcmtBean.setPhrno(bean.getPhrno());
			plcmtBean.setEntry_distcode(distCode);
		}

		if (bean.getPtype().equalsIgnoreCase("Apprenticeship")) {
			plcmtBean.setPname_of_company(bean.getPname_of_company());
			plcmtBean.setPhrno(bean.getPhrno());
			plcmtBean.setPtrade(bean.getPtrade());
			plcmtBean.setPtradeShort(bean.getPtrade());
			plcmtBean.setPstipendamt(bean.getPstipendamt());
			plcmtBean.setPaaprstartdate(bean.getPaaprstartdate());
			plcmtBean.setPaaprenddate(bean.getPaaprenddate());
			plcmtBean.setScheduleId(bean.getScheduleId());
			plcmtBean.setPlcmtId(Long.valueOf(bean.getScheduleId()));
			plcmtBean.setEntry_distcode(distCode);
		}

		if (bean.getPtype().equalsIgnoreCase("OA")) {
			plcmtBean.setPname_of_company(bean.getPname_of_company());
			plcmtBean.setPhrno(bean.getPhrno());
			plcmtBean.setPtrade(bean.getPtrade());
			plcmtBean.setPtradeShort(bean.getPtrade());
			plcmtBean.setPstipendamt(bean.getPstipendamt());
			plcmtBean.setPaaprstartdate(bean.getPaaprstartdate());
			plcmtBean.setPaaprenddate(bean.getPaaprenddate());
			plcmtBean.setEntry_distcode(distCode);
		}

		if (bean.getPtype().equalsIgnoreCase("SelfEmployment")) {
			plcmtBean.setPselfemp(bean.getPselfemp());
			plcmtBean.setPmonthincome(bean.getPmonthincome());
			plcmtBean.setEntry_distcode(distCode);
		}

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(request);

		plcmtBean.setUserId(claimsFromToken.getInsCode());
		plcmtBean.setEntryDate(LocalDateTime.now());

		String msg = "";

		try {
			this.placementRepo.save(plcmtBean);
			msg = "plaement details are ADDED successfully with placement ID: " + plcmtBean.getPid();
		} catch (Exception var7) {
			msg = "Something went wrong while saving placement data";
			var7.printStackTrace();
		}

		ResponseRest resp = new ResponseRest();
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}

	@DeleteMapping("deletePlcmts")
	public ResponseEntity<?> deletePlcmts(@RequestParam("pid") String pid, HttpServletRequest httpServletRequest) {
		System.out.println("./deletePlcmts/pid=>" + pid);

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			System.out.println("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
				return new ResponseEntity<String>("Your not authorized to enter Industries data.",
						HttpStatus.BAD_REQUEST);
			}
			
			// Decode Base64 PID
			byte[] decodePid = Base64.getDecoder().decode(pid);
			String decodedString = new String(decodePid);
			System.out.println("Decoded PID: " + decodedString);
			
			
			
			Optional<PlacementEntity> byId = placementService.getById(Long.valueOf(decodedString));
			if (byId.isPresent()) {
				PlacementEntity placementEntity = byId.get();
				String ptype = placementEntity.getPtype();
				if (ptype.equalsIgnoreCase("Job") || ptype.equalsIgnoreCase("OJ")
						|| ptype.equalsIgnoreCase("Apprenticeship") || ptype.equalsIgnoreCase("OA")) {
					if (claimsFromToken.getInsCode().equalsIgnoreCase(placementEntity.getEntry_by())) {
						placementService.deletePlcmtById(placementEntity.getPid());
						return new ResponseEntity<String>("DATA IS DELETED SUCCESSFULLY.", HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("YOUR NOT AUTHORIZED TO DELETE THIS DATA.",
								HttpStatus.BAD_REQUEST);
					}
				}
				if (ptype.equalsIgnoreCase("HigherEducation") || ptype.equalsIgnoreCase("SelfEmployment")) {
					if (claimsFromToken.getInsCode().equalsIgnoreCase(placementEntity.getIti_code())) {
						placementService.deletePlcmtById(placementEntity.getPid());
						return new ResponseEntity<String>("DATA IS DELETED SUCCESSFULLY.", HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("YOUR NOT AUTHORIZED TO DELETE THIS DATA.",
								HttpStatus.BAD_REQUEST);
					}
				}
			} else {
				return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN ID", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("SOMETHING WENT WRONG,EXCEPTION ARISED.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("SOMETHING WENT WRONG.", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("getPlcmtsById")
	public ResponseEntity<?> getPlcmtsById(@RequestParam String pid, HttpServletRequest httpServletRequest) {
		System.out.println("getPlcmtsById=>pid=>" + pid);

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			System.out.println("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
				return new ResponseEntity<String>("Your not authorized to enter Industries data.",
						HttpStatus.BAD_REQUEST);
			}

			byte[] decodePid = Base64.getDecoder().decode(pid);
			String decodedString = new String(decodePid);
			System.out.println(decodedString);

			Optional<PlacementEntity> byId = placementService.getById(Long.valueOf(decodedString));
			if (byId.isPresent()) {
				PlacementEntity placementEntity = byId.get();
				System.out.println("placementEntity=>" + placementEntity.toString());
				String ptype = placementEntity.getPtype();
				if (ptype.equalsIgnoreCase("Job") || ptype.equalsIgnoreCase("OJ")
						|| ptype.equalsIgnoreCase("Apprenticeship") || ptype.equalsIgnoreCase("OA")) {
					if (claimsFromToken.getInsCode().equalsIgnoreCase(placementEntity.getEntry_by())) {
						return new ResponseEntity<PlacementEntity>(placementEntity, HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("YOUR NOT AUTHORIZED TO EDIT THIS DATA.",
								HttpStatus.BAD_REQUEST);
					}
				}
				if (ptype.equalsIgnoreCase("HigherEducation") || ptype.equalsIgnoreCase("SelfEmployment")) {
					if (claimsFromToken.getInsCode().equalsIgnoreCase(placementEntity.getIti_code())) {
						return new ResponseEntity<PlacementEntity>(placementEntity, HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("YOUR NOT AUTHORIZED TO EDIT THIS DATA.",
								HttpStatus.BAD_REQUEST);
					}
				}
			} else {
				return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN ID", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("SOMETHING WENT WRONG,EXCEPTION ARISED.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("SOMETHING WENT WRONG.", HttpStatus.BAD_REQUEST);
	}
	
	//passed year 2024 like data comes 2023 1year trade and 2022 2 years trade
	@GetMapping("getCurrentAndSeniorsData")
	public ResponseEntity<List<PlacementProjection>> getCurrentAndSeniorsData(
            @RequestParam("year") String year,
			@RequestParam("itiType") String itiType) {
		System.out.println("itiType-------->"+itiType);
		if(itiType.equalsIgnoreCase("All")) {
			 List<PlacementProjection> report = placementService.getPlacementReport(year);
		        System.out.println("getCurrentAndSeniordData---->"+year);
		        return ResponseEntity.ok(report);
		}
		else {
        List<PlacementProjection> report = placementService.getPlacementReport(year, itiType);
        System.out.println("getCurrentAndSeniordData---->"+year);
        return ResponseEntity.ok(report);
		}
    }

}
