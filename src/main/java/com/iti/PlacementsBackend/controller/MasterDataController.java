
package com.iti.PlacementsBackend.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;
import com.iti.PlacementsBackend.repo.hrm.CasteMasterRepository;
import com.iti.PlacementsBackend.repo.hrm.SubCasteMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;
import com.iti.PlacementsBackend.entity.hrm.Employee;
import com.iti.PlacementsBackend.entity.hrm.EmployeeTransfer;
import com.iti.PlacementsBackend.entity.inplant.ImplantEntity;
import com.iti.PlacementsBackend.entity.inplant.IndustriesEntity;
import com.iti.PlacementsBackend.entity.inplant.IndustryMaster;
import com.iti.PlacementsBackend.entity.labs.LabEntity;
import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;
import com.iti.PlacementsBackend.entity.master.DistsStatewise;
import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.entity.master.States_mastEntity;
import com.iti.PlacementsBackend.entity.plcmts.PlacementEntity;
import com.iti.PlacementsBackend.model.ApiError;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.model.DashBoadModel;
import com.iti.PlacementsBackend.model.plcmts.AjaxResponseBody;
import com.iti.PlacementsBackend.model.plcmts.StateSkillDevelopmentPlanModel;
import com.iti.PlacementsBackend.projection.DashBoardGovtOrPvtSeatsProj;
import com.iti.PlacementsBackend.projection.ITIsSeatsStatisticsProj;
import com.iti.PlacementsBackend.projection.UniversalProjection;
import com.iti.PlacementsBackend.projection.inplant.ImplantProjection;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiIndustriesProj;
import com.iti.PlacementsBackend.projection.plcmts.StateSkillDevelopmentPlanProj;
import com.iti.PlacementsBackend.repo.master.ItiRepo;
import com.iti.PlacementsBackend.repo.master.ItiTradeMasterRepo;
import com.iti.PlacementsBackend.service.DashBoardServices;
import com.iti.PlacementsBackend.service.ItitradeService;
import com.iti.PlacementsBackend.service.hrm.CasteMasterService;
import com.iti.PlacementsBackend.service.hrm.EmployeeService;
import com.iti.PlacementsBackend.service.hrm.EmployeeTransferService;
import com.iti.PlacementsBackend.service.inplant.ImplantService;
import com.iti.PlacementsBackend.service.inplant.IndustriesService;
import com.iti.PlacementsBackend.service.inplant.IndustryMasterService;
import com.iti.PlacementsBackend.service.labs.LabItemsService;
import com.iti.PlacementsBackend.service.labs.LabsService;
import com.iti.PlacementsBackend.service.master.DistsStatewiseService;
import com.iti.PlacementsBackend.service.master.ItiService;
import com.iti.PlacementsBackend.service.master.States_mastService;
import com.iti.PlacementsBackend.service.plcmts.PlacementService;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/masterdata")
@CrossOrigin(origins = "*")
@Validated
public class MasterDataController {

	private static final Logger logger = LoggerFactory.getLogger(MasterDataController.class);

	@Autowired
	private DashBoardServices boardServices;
	
	@Autowired
	private EmployeeTransferService employeeTransferService;

	@Autowired
	private MyUtil myUtil;
	@Autowired
	private ItiRepo itiRepo;
	@Autowired
	private ItiTradeMasterRepo itiTradeMasterRepo;
	@Autowired
	private ItiService itiService;
	@Autowired
	private DistsStatewiseService distsStatewiseService;
	@Autowired
	private States_mastService states_mastService;
	
	@Autowired
	private ItitradeService ititradeService;
	
	@Autowired
	private LabsService labsService;

	@Autowired
	private ImplantService implantService;
	
	@Autowired
	private IndustryMasterService industryMasterService;
	
	@Autowired
	private IndustriesService industriesService;
	

	@Autowired
	private PlacementService placementService;
	
	@Autowired
	private LabItemsService labItemsService;
	
	@Autowired
	private CasteMasterService casteMasterService;

	// test api
	@GetMapping("getAllLabItems")
	public ResponseEntity<?> getAllLabItems() {

		return new ResponseEntity<List<LabItemsEntity>>(labItemsService.getAll(), HttpStatus.OK);
	}
	
	// test api for all castes
	 @GetMapping("/all")
	    public ResponseEntity<List<CasteMaster>> getAllCastes() {
	        return ResponseEntity.ok(casteMasterService.getAllCastes());
	    }

	// test api
	@GetMapping("getImplantTradesData")
	public ResponseEntity<?> getImplantTradesData(String trade_short) {
		List<ImplantProjection> implantIndustriesTraineesData = implantService.getImplantTradesData(trade_short);
		return new ResponseEntity<List<ImplantProjection>>(implantIndustriesTraineesData, HttpStatus.OK);
	}

	// test api
	@GetMapping("getImplantIndustriesTraineesData")
	public ResponseEntity<?> getImplantIndustriesTraineesData(String industryId) {
		List<ImplantProjection> implantIndustriesTraineesData = implantService
				.getImplantIndustriesTraineesData(Long.valueOf(industryId));
		return new ResponseEntity<List<ImplantProjection>>(implantIndustriesTraineesData, HttpStatus.OK);
	}

	// test api
	@GetMapping("getTraineesReportItiIndustries")
	public ResponseEntity<?> getTraineesReportItiIndustries(String iti_code) {
		return new ResponseEntity<List<TraineesReportItiIndustriesProj>>(
				implantService.getTraineesReportItiIndustries(iti_code), HttpStatus.OK);
	}



	// trades for iti based on iticode
	@GetMapping("tradesInIti")
	public List<UniversalProjection> getTradesInIti(@RequestParam("iticode") String iticode) {
		// TODO Auto-generated method stub
		return ititradeService.getTradesInIti(iticode);
	}

	// test api
	@GetMapping("getAllIndustries")
	public List<IndustriesEntity> getAllIndustries() {
		return industriesService.getAllIndustries();
	}

	@GetMapping("/getDistsStatewise")
	public ResponseEntity<?> getDistsStatewise() {
		List<DistsStatewise> allDistStatewise = distsStatewiseService.getAllDistStatewise();
		return new ResponseEntity<List<DistsStatewise>>(allDistStatewise, HttpStatus.OK);

	}

	// Get all the claims from given token
	@PostMapping("/getAllClaims")
	public ResponseEntity<?> getAllClaims(HttpServletRequest request) {
		logger.info("getAllClaims");

		final String authorizationHeader = request.getHeader("Authorization").substring(7);
		System.out.println("authorizationHeader=>" + authorizationHeader);

		if (authorizationHeader == null) {
			return new ResponseEntity<>(
					new ApiError(HttpStatus.BAD_REQUEST, "Authorization header is required to get data."),
					HttpStatus.BAD_REQUEST);
		}

		String isValid = myUtil.validateToken(authorizationHeader);
		if (isValid.equalsIgnoreCase("true")) {
			ClaimsModel claimsModel = myUtil.getClaimsFromToken(request);
			if (claimsModel.getRoleId().equalsIgnoreCase("4")) {
				ItiEntity iti = itiRepo.findById(claimsModel.getInsCode()).get();
				claimsModel.setInsName(iti.getItiName());
			}
			return new ResponseEntity<>(claimsModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Given Token is Invalid."),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllTrades")
	public ResponseEntity<List<ItiTradeMasterEntity>> ititrade_master() {
		List<ItiTradeMasterEntity> ititrade_master = itiTradeMasterRepo.findAllByOrderByTradeNameAsc();
		return new ResponseEntity<>(ititrade_master, HttpStatus.OK);
	}

	@GetMapping("getITIsByDistCode")
	public ResponseEntity<List<ItiEntity>> getITIsByDistCode(HttpServletRequest httpServletRequest) {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("implantDistReport claims=>" + claimsFromToken.toString());

		List<ItiEntity> byDistCode = itiService.getByDistCode(claimsFromToken.getInsCode());
		return new ResponseEntity<List<ItiEntity>>(byDistCode, HttpStatus.OK);
	}

	@GetMapping("getAllItis")
	public ResponseEntity<?> getAllItis() {
		return new ResponseEntity<List<ItiEntity>>(itiService.getAllItis(), HttpStatus.OK);
	}

	@GetMapping("getItisInADist")
	public List<ItiEntity> getItisInADist(@RequestParam("distCode") String distCode) {
		return itiService.getByDistCode(distCode);
	}

	@GetMapping("/getMastersData")
	public AjaxResponseBody getMastersData() {
		System.out.println("/api/masterdata/getMastersData");

		AjaxResponseBody arb = new AjaxResponseBody();
		List<DistsStatewise> districts = distsStatewiseService.getAllDistStatewise();
		System.out.println("districts=>" + districts.size());
		arb.setDists(districts);

		List<States_mastEntity> states = states_mastService.getAllStates();
		System.out.println("states=>" + states.size());
		arb.setStates(states);

		List<ItiTradeMasterEntity> trades = this.itiTradeMasterRepo.findAll();
		System.out.println("trades=>" + trades.size());
		arb.setTrades(trades);

		return arb;
	}

	@GetMapping("getAllGovtItisInDist")
	public ResponseEntity<?> getAllGovtItisInDist(@RequestParam("dist_code") String dist_code) {
		System.out.println("/api/masterdata/getAllGovtItisInDist");
		System.out.println("distCode=>" + dist_code);
		List<ItiEntity> govtItis = this.itiRepo.getAllGovtItisInDist(dist_code);
		return new ResponseEntity<List<ItiEntity>>(govtItis, HttpStatus.OK);
	}

	@GetMapping("getAllIndustryMaster")
	public ResponseEntity<?> getAllIndustryMaster() {
		return new ResponseEntity<List<IndustryMaster>>(industryMasterService.getAllByOrderByIndustryNameAsc(),
				HttpStatus.OK);
	}

	// Dashboard APIs
	@GetMapping("countPlacementsGroupedByPtype")
	public ResponseEntity<?> countPlacementsGroupedByPtype() {

		List<PlacementEntity> all = placementService.getAll();
		Map<String, Long> collect = all.stream()
				.collect(Collectors.groupingBy(PlacementEntity::getPtype, Collectors.counting()));
		logger.info(collect.toString());

		return new ResponseEntity<Map<String, Long>>(collect, HttpStatus.OK);
	}

	@GetMapping("getdDistinctItiCodesByPtype")
	public ResponseEntity<?> getdDistinctItiCodesByPtype() {

		Map<String, Integer> map = new HashMap<>();

		List<String> jobItisCount = placementService.findDistinctItiCodesByPtype("Job");
		map.put("jobItisCount", jobItisCount.size());

		List<String> ojItisCount = placementService.findDistinctItiCodesByPtype("OJ");
		map.put("ojItisCount", ojItisCount.size());

		List<String> apprenticeshipItisCount = placementService.findDistinctItiCodesByPtype("Apprenticeship");
		map.put("apprenticeshipItisCount", apprenticeshipItisCount.size());

		List<String> oaItisCount = placementService.findDistinctItiCodesByPtype("OA");
		map.put("oaItisCount", oaItisCount.size());

		List<String> higherEducationItisCount = placementService.findDistinctItiCodesByPtype("HigherEducation");
		map.put("higherEducationItisCount", higherEducationItisCount.size());

		List<String> selfEmploymentItisCount = placementService.findDistinctItiCodesByPtype("SelfEmployment");
		map.put("selfEmploymentItisCount", selfEmploymentItisCount.size());

		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}

	@GetMapping("overviewdetails")
	public ResponseEntity<?> overviewdetails() {

		Map<String, Integer> map = new HashMap<>();
		List<PlacementEntity> allPlacement = placementService.getAll();
		map.put("allPlacement", allPlacement.size());

		List<ImplantEntity> allImplants = implantService.getAllImplants();
		map.put("allImplants", allImplants.size());

		List<LabEntity> allLabs = labsService.getAll();
		map.put("allLabs", allLabs.size());

		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}

	@GetMapping("inplantDashboardDetails")
	public ResponseEntity<?> inplantDashboardDetails() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//inplant table data
		List<ImplantEntity> allImplants = implantService.getAllImplants();
		map.put("inplantTotal", allImplants.size());
		List<String> inplantDistinctItis = allImplants.stream().map(ImplantEntity::getItiCode).distinct().collect(Collectors.toList());
		map.put("inplantDistinctItis", inplantDistinctItis.size());
		List<Long> inplantDistinctSlnos = allImplants.stream().map(ImplantEntity::getSlno).distinct().collect(Collectors.toList());
		map.put("inplantDistinctSlnos", inplantDistinctSlnos.size());
		Integer sumOfStudent = implantService.getSumOfStudent();
		map.put("sumOfStudent", sumOfStudent);
		
		//industries table data
		List<IndustriesEntity> allIndustries = industriesService.getAllIndustries();
		map.put("industriesTotal", allIndustries.size());
		List<Integer> industriesDistinctItis = allIndustries.stream().map(IndustriesEntity::getItiCode).distinct().collect(Collectors.toList());
		map.put("industriesDistinctItis", industriesDistinctItis.size());
		List<Long> industriesDistinctIndustries = allIndustries.stream().map(IndustriesEntity::getIndustryId).distinct().collect(Collectors.toList());
		map.put("industriesDistinctIndustries", industriesDistinctIndustries.size());
		List<String> industriesDistinctTrades = allIndustries.stream().map(IndustriesEntity::getTradeShort).distinct().collect(Collectors.toList());
		map.put("industriesDistinctTrades", industriesDistinctTrades.size());
		
		//industry_master table data
		List<IndustryMaster> allIndustryMaster = industryMasterService.getAllIndustryMaster();
		map.put("industryMasterTotal", allIndustryMaster.size());
		long industryMasterMajor = allIndustryMaster.stream().filter(industry-> "Major".equalsIgnoreCase(industry.getIndustryType())).count();
		long industryMasterMinor = allIndustryMaster.stream().filter(industry-> "Minor".equalsIgnoreCase(industry.getIndustryType())).count();
		map.put("industryMasterMajor", (int) industryMasterMajor);
		map.put("industryMasterMinor", (int) industryMasterMinor);
		
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	@GetMapping("labsDashboardDetails")
	public ResponseEntity<?> labsDashboardDetails(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		List<LabEntity> labsAll = labsService.getAll();
		map.put("labsTotal", labsAll.size());
		List<String> labsDistinctItis = labsAll.stream().map(LabEntity::getItiCode).distinct().collect(Collectors.toList());
		map.put("labsDistinctItis", labsDistinctItis.size());
		List<String> labsDistinctTrades = labsAll.stream().map(LabEntity::getTradeShort).distinct().collect(Collectors.toList());
		map.put("labsDistinctTrades", labsDistinctTrades.size());
		
		List<LabItemsEntity> labItemsAll = labItemsService.getAll();
		map.put("labItemsTotal", labItemsAll.size());
		List<String> labItemsDistinctItems = labItemsAll.stream().map(LabItemsEntity::getItemName).distinct().collect(Collectors.toList());
		map.put("labItemsDistinctItems", labItemsDistinctItems.size());
		
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	@GetMapping("stateSkillDevelopmentPlanReport")
	public ResponseEntity<?> stateSkillDevelopmentPlanReport(@RequestParam("year") String year){
		
		List<StateSkillDevelopmentPlanProj> list = placementService.stateSkillDevelopmentPlanReport1(year);
		if(list.isEmpty()) {
			return new ResponseEntity<String>("NO DATA FOUND", HttpStatus.BAD_REQUEST);
		}else {
			
			List<StateSkillDevelopmentPlanModel> stateSkillDevelopmentPlanReport = new ArrayList<StateSkillDevelopmentPlanModel>();
			
			for(StateSkillDevelopmentPlanProj bean:list) {
				StateSkillDevelopmentPlanProj genderwiseobject = placementService.stateSkillDevelopmentPlanReport2(year, bean.getTrade_code());
				
				StateSkillDevelopmentPlanModel sSDPModel = new StateSkillDevelopmentPlanModel();
				sSDPModel.setTradeCode(bean.getTrade_code());
				sSDPModel.setTradeName(bean.getTrade_name());
				sSDPModel.setItiCount(bean.getIti_count());
				sSDPModel.setTotalStrength(bean.getTotal_strength());
				
				sSDPModel.setTotalMale(genderwiseobject.getTotalmale());
				sSDPModel.setTotalFemale(genderwiseobject.getTotalfemale());
				sSDPModel.setTotalGender(genderwiseobject.getTotalgender());
				sSDPModel.setTotalPlcmts(genderwiseobject.getTotalplcmts());
				
				stateSkillDevelopmentPlanReport.add(sSDPModel);
			}
			System.out.println("stateSkillDevelopmentPlanReport size=>"+stateSkillDevelopmentPlanReport.size());
			return new ResponseEntity<List<StateSkillDevelopmentPlanModel>>(stateSkillDevelopmentPlanReport, HttpStatus.OK);
		}
		
		
		
	}
	
	@GetMapping("getDistinctYearOfAdmissons")
	public ResponseEntity<List<String>> getDistinctYearOfAdmissons(){
		return new ResponseEntity<List<String>>(placementService.getDistinctYearOfAdmissons(), HttpStatus.OK);
	}
	
	//DashBoard API's
	
	@GetMapping("dashBoardData")
	public ResponseEntity<?> dashBoardData() {
		System.out.println("dashBoardData");

		com.iti.PlacementsBackend.model.DashBoadModel dashBoadModel;
		try {
			 
			Long above20percentcollegescountgovt = boardServices.getAbove20percentcollegescountgovt();
			Long below20percentcollegescountgovt = boardServices.getBelow20percentcollegescountgovt();

			Long above20percentcollegescountpvt = boardServices.getAbove20percentcollegescountpvt();
			Long below20percentcollegescountpvt = boardServices.getBelow20percentcollegescountpvt();
 
			com.iti.PlacementsBackend.projection.DashBoardAllSeatsProj dashBoardAllSeats = boardServices.getDashBoardAllSeats();

			dashBoadModel = new com.iti.PlacementsBackend.model.DashBoadModel();
			 
			dashBoadModel.setAbove20percentcollegescountgovt(above20percentcollegescountgovt);
			dashBoadModel.setBelow20percentcollegescountgovt(below20percentcollegescountgovt);

			dashBoadModel.setAbove20percentcollegescountpvt(above20percentcollegescountpvt);
			dashBoadModel.setBelow20percentcollegescountpvt(below20percentcollegescountpvt);
 
			DashBoardGovtOrPvtSeatsProj dashBoardGovtSeats = boardServices.getDashBoardGovtOrPvtSeats("G");
			DashBoardGovtOrPvtSeatsProj dashBoardPvtSeats = boardServices.getDashBoardGovtOrPvtSeats("P");

			dashBoadModel.setDashBoardGovtSeats(dashBoardGovtSeats);
			dashBoadModel.setDashBoardPvtSeats(dashBoardPvtSeats);

			dashBoadModel.setDashBoardAllSeats(dashBoardAllSeats);

			System.out.println("dashBoadModel=>" + dashBoadModel.toString());
			return new ResponseEntity<DashBoadModel>(dashBoadModel, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.OK);

	}

	@GetMapping("hello")
	public ResponseEntity<?> hello() {
		return new ResponseEntity<String>("HELLO FROM CONTROLLER", HttpStatus.OK);
	}
	
	@GetMapping("getAbove20PercentItisStats")
	public ResponseEntity<?> get20percentstats(){
		System.out.println("getAbove20PercentItis");
		
		List<ITIsSeatsStatisticsProj> above20PercentItis = boardServices.getAbove20PercentItis();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("noOfItis", String.valueOf(above20PercentItis.size()));
		
		ITIsSeatsStatisticsProj above20PercentSeatsStats = boardServices.getAbove20PercentSeatsStats();
		map.put("strength", String.valueOf(above20PercentSeatsStats.getStrength()));
		map.put("strength_fill", String.valueOf(above20PercentSeatsStats.getStrength_fill()));
		map.put("strength_vacant", String.valueOf(above20PercentSeatsStats.getStrength_vacant()));
		map.put("fill_ratio", String.valueOf(above20PercentSeatsStats.getFill_ratio()));
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		
	}
	@GetMapping("getBelow20PercentItisStats")
	public ResponseEntity<?> getBelow20PercentItisStats(){
		System.out.println("getBelow20PercentItisStats");
		
		List<ITIsSeatsStatisticsProj> below20PercentItis;
		Map<String, String> map = new HashMap<String, String>();
		try {
			below20PercentItis = boardServices.getBelow20PercentItis();
			map.put("noOfItis", String.valueOf(below20PercentItis.size()));
			
			Integer strength=below20PercentItis.stream().mapToInt(ITIsSeatsStatisticsProj::getStrength).sum();
			map.put("strength", String.valueOf(strength));
			Integer strength_fill=below20PercentItis.stream().mapToInt(ITIsSeatsStatisticsProj::getStrength_fill).sum();
			map.put("strength_fill", String.valueOf(strength_fill));
			Integer strength_vacant=below20PercentItis.stream().mapToInt(ITIsSeatsStatisticsProj::getStrength_vacant).sum();
			map.put("strength_vacant", String.valueOf(strength_vacant));
			double fill_ratio = below20PercentItis.stream().mapToInt(ITIsSeatsStatisticsProj::getFill_ratio).average().orElse(0.0);
			
			DecimalFormat df = new DecimalFormat("0.00");
			String format = df.format(fill_ratio);
			map.put("fill_ratio", format);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("strength", "-");
			map.put("strength_fill", "-");
			map.put("strength_vacant", "-");
			map.put("fill_ratio", "-");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("getAbove20PercentItis")
	public ResponseEntity<?> getAbove20PercentItis(){
		System.out.println("getAbove20PercentItis");
		
		List<ITIsSeatsStatisticsProj> above20PercentItis = new ArrayList<ITIsSeatsStatisticsProj>();
		try {
			 above20PercentItis = boardServices.getAbove20PercentItis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<ITIsSeatsStatisticsProj>>(above20PercentItis, HttpStatus.OK);
	}
	@GetMapping("getBelow20PercentItis")
	public ResponseEntity<?> getBelow20PercentItis(){
		System.out.println("getBelow20PercentItis");
		
		List<com.iti.PlacementsBackend.projection.ITIsSeatsStatisticsProj> below20PercentItis = new ArrayList<ITIsSeatsStatisticsProj>();
		try {
			below20PercentItis = boardServices.getBelow20PercentItis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<ITIsSeatsStatisticsProj>>(below20PercentItis, HttpStatus.OK);
	}
	
 	
	
	//Save the employee transfer details api
    @PostMapping(value = "/saveEmployeeTransfer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveEmployeeTransfer(
            @RequestParam("employeeName") String employeeName,
            @RequestParam("employeeCode") String employeeCode,
            @RequestParam("designation") Long designation,
            @RequestParam("tradeName") String tradeName,
            @RequestParam("presentWorkingStation") String presentWorkingStation,
            @RequestParam("workingSinceAllCadres") String workingSinceAllCadres,
            @RequestParam("dob") String dob,
            @RequestParam("dor") String dor,
            @RequestParam("serviceYears") String serviceYears,
            @RequestParam("serviceMonths") String serviceMonths,
            @RequestParam("serviceDays") String serviceDays,
            @RequestParam("benchmarkDisability") String benchmarkDisability,
            @RequestParam(value = "pbdCategory", required = false) String pbdCategory,
            @RequestParam("spouseWorkingPlace") String spouseWorkingPlace,
            @RequestParam("challengedChildren") String challengedChildren,
            @RequestParam("widowCase") String widowCase,
            @RequestParam("medicalGrounds") String medicalGrounds,
            @RequestParam("workedTribalArea") String workedTribalArea,
            @RequestParam("areaName") String areaName,
            @RequestParam("officeBearerTerms") Integer officeBearerTerms,
            @RequestParam("officeBearerYears") Integer officeBearerYears,
            @RequestParam("passPercentage") String passPercentage,
            @RequestParam("placementPercentage") String placementPercentage,
            @RequestParam("remarks") String remarks,
            @RequestParam(value = "itiCode", required = false) String itiCode,
            @RequestParam(value = "pbdCertificate", required = false) MultipartFile pbdCertificate,
            @RequestParam(value = "spouseCertificate", required = false) MultipartFile spouseCertificate,
            @RequestParam(value = "challengedChildrenCert", required = false) MultipartFile challengedChildrenCert,
            @RequestParam(value = "widowCertificate", required = false) MultipartFile widowCertificate,
            @RequestParam(value = "medicalCertificate", required = false) MultipartFile medicalCertificate,
            @RequestParam(value = "officeBearerCert", required = false) MultipartFile officeBearerCert,
            HttpServletRequest request
    ) {
        try {
            String uploadDir = "C:/employee-transfer-certificates/";
            EmployeeTransfer employeeTransfer = new EmployeeTransfer();

            // Set simple fields
            employeeTransfer.setEmployeeName(employeeName);
            employeeTransfer.setEmployeeCode(employeeCode);
            employeeTransfer.setDesignation(designation);
            employeeTransfer.setTradeName(tradeName);
            employeeTransfer.setPresentWorkingStation(presentWorkingStation);
            employeeTransfer.setWorkingSinceAllCadres(Date.valueOf(workingSinceAllCadres));
            employeeTransfer.setDob(Date.valueOf(dob));
            employeeTransfer.setDor(Date.valueOf(dor));
            employeeTransfer.setServiceYears(serviceYears);
            employeeTransfer.setServiceMonths(serviceMonths);
            employeeTransfer.setServiceDays(serviceDays);
            employeeTransfer.setBenchmarkDisability(benchmarkDisability);
            employeeTransfer.setPbdCategory(pbdCategory);
            employeeTransfer.setSpouseWorkingPlace(spouseWorkingPlace);
            employeeTransfer.setChallengedChildren(challengedChildren);
            employeeTransfer.setWidowCase(widowCase);
            employeeTransfer.setMedicalGrounds(medicalGrounds);
            employeeTransfer.setWorkedTribalArea(workedTribalArea);
            employeeTransfer.setAreaName(areaName);
            employeeTransfer.setOfficeBearerTerms(officeBearerTerms);
            employeeTransfer.setOfficeBearerYears(officeBearerYears);
            employeeTransfer.setPassPercentage(passPercentage);
            employeeTransfer.setPlacementPercentage(placementPercentage);
            employeeTransfer.setRemarks(remarks);
            employeeTransfer.setItiCode(itiCode);

            // Optionally set system metadata manually
            employeeTransfer.setEntryBy("system"); // or set null/empty
            employeeTransfer.setEntryDate(LocalDateTime.now());

            // Save certificate files
            if (pbdCertificate != null && !pbdCertificate.isEmpty())
                employeeTransfer.setPbdCertificate(saveFile(pbdCertificate, uploadDir));
            if (spouseCertificate != null && !spouseCertificate.isEmpty())
                employeeTransfer.setSpouseCertificate(saveFile(spouseCertificate, uploadDir));
            if (challengedChildrenCert != null && !challengedChildrenCert.isEmpty())
                employeeTransfer.setChallengedChildrenCert(saveFile(challengedChildrenCert, uploadDir));
            if (widowCertificate != null && !widowCertificate.isEmpty())
                employeeTransfer.setWidowCertificate(saveFile(widowCertificate, uploadDir));
            if (medicalCertificate != null && !medicalCertificate.isEmpty())
                employeeTransfer.setMedicalCertificate(saveFile(medicalCertificate, uploadDir));
            if (officeBearerCert != null && !officeBearerCert.isEmpty())
                employeeTransfer.setOfficeBearerCert(saveFile(officeBearerCert, uploadDir));

            // Save to DB
            employeeTransferService.saveEmployeeTransfer(employeeTransfer);
            

            return ResponseEntity.ok("Employee Transfer Details saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving transfer Data: " + e.getMessage());
        }
    }
    private String saveFile(MultipartFile file, String uploadDir) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
        String filePath = uploadDir + uniqueFilename;
        file.transferTo(new File(filePath));
        return filePath;
    }

	@Autowired
	private CasteMasterRepository casteMasterRepository;

	@Autowired
	private SubCasteMasterRepository subCasteMasterRepository;

	@GetMapping("getAllByOrderByCasteCategoryAsc")
	public ResponseEntity<List<CasteMaster>> getAllByOrderByCasteCategoryAsc(){
		return ResponseEntity.ok(casteMasterRepository.findAllByOrderByCasteCategoryAsc());
	}

	@GetMapping("getByCasteMasterCasteIdOrderBySubCasteAsc")
	public ResponseEntity<List<SubCasteMaster>> getByCasteMasterCasteIdOrderBySubCasteAsc(@RequestParam Long casteId){
		return ResponseEntity.ok(subCasteMasterRepository.findByCasteMaster_CasteIdOrderBySubCasteAsc(casteId));
	}

	

}
