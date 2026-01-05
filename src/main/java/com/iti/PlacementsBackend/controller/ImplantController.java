package com.iti.PlacementsBackend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iti.PlacementsBackend.entity.inplant.ImplantEntity;
import com.iti.PlacementsBackend.entity.inplant.IndustriesEntity;
import com.iti.PlacementsBackend.entity.inplant.IndustryMaster;
import com.iti.PlacementsBackend.entity.inplant.IndustryPartnerDetails;
import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.entity.master.OldDistMasterEntity;
import com.iti.PlacementsBackend.entity.plcmts.PlacementEntity;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.model.IndustryModel;
import com.iti.PlacementsBackend.model.inplant.DistinctCountModel;
import com.iti.PlacementsBackend.model.inplant.IndustryPartnerDetailsModel;
import com.iti.PlacementsBackend.model.plcmts.PlacementsModel;
import com.iti.PlacementsBackend.projection.DistReportProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportIndustrieswiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportItiwiseProj;
import com.iti.PlacementsBackend.projection.inplant.TraineesReportTradesProj;
import com.iti.PlacementsBackend.projection.inplant.TwoYearsDataProjection;
import com.iti.PlacementsBackend.service.inplant.ImplantService;
import com.iti.PlacementsBackend.service.inplant.IndustriesService;
import com.iti.PlacementsBackend.service.inplant.IndustryMasterService;
import com.iti.PlacementsBackend.service.inplant.IndustryPartnerDetailsService;
import com.iti.PlacementsBackend.service.master.ItiService;
import com.iti.PlacementsBackend.service.master.ItiTradeMasterService;
import com.iti.PlacementsBackend.service.master.OldDistMasterService;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/implant")
@CrossOrigin(origins = "*")
public class ImplantController {

	private static final Logger logger = LoggerFactory.getLogger(ImplantController.class);

	@Autowired
	private ImplantService implantService;
	@Autowired
	private IndustriesService industriesService;
	@Autowired
	private MyUtil myUtil;
	@Autowired
	private IndustryMasterService industryMasterService;
	@Autowired
	private ItiService itiService;
	@Autowired
	private OldDistMasterService oldDistMasterService;
	@Autowired
	private ItiTradeMasterService itiTradeService;

	// IMPLANT TABLE METHODS
	@PostMapping("/saveImplant")
	public ResponseEntity<?> saveImplant(@RequestBody ImplantEntity implantEntity,
			HttpServletRequest httpServletRequest) {
		logger.info("implantEntity=>" + implantEntity.toString());

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		Optional<IndustriesEntity> industries = industriesService.getIndustries(implantEntity.getSlno());
		if (industries.isPresent()) {
			implantEntity.setTradeShort(industries.get().getTradeShort());
			implantEntity.setItiCode(String.valueOf(industries.get().getItiCode()));
		}
		implantEntity.setItiCode(claimsFromToken.getInsCode());
		implantEntity.setEntryBy(claimsFromToken.getInsCode());
		implantEntity.setEntryDate(LocalDateTime.now());

		ImplantEntity saveImplant = implantService.saveImplant(implantEntity);
		return new ResponseEntity<ImplantEntity>(saveImplant, HttpStatus.OK);
	}

	// ITI LEVEL REPORT
	@GetMapping("/implantITIReport")
	public ResponseEntity<?> getByUserId(HttpServletRequest httpServletRequest) {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (claimsFromToken.getRoleId().equalsIgnoreCase("4")) {

			List<ImplantEntity> allByUserId = implantService.getByItiCode(claimsFromToken.getInsCode());
			System.out.println("implantITIReport=listsize=>" + allByUserId.size());

			return new ResponseEntity<List<ImplantEntity>>(allByUserId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
		}
	}

	// DIST LEVEL REPORT
	@GetMapping("/implantDistReport")
	public ResponseEntity<?> implantDistReport(HttpServletRequest httpServletRequest) {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("implantDistReport claims=>" + claimsFromToken.toString());

		if (claimsFromToken.getRoleId().equalsIgnoreCase("3")) {
			return new ResponseEntity<List<DistReportProj>>(
					implantService.getByItiDistCode(claimsFromToken.getInsCode()), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("implantNodalReport")
	public ResponseEntity<?> implantNodalReport(HttpServletRequest httpServletRequest) {
		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("implantDistReport claims=>" + claimsFromToken.toString());

		if (claimsFromToken.getRoleId().equalsIgnoreCase("10") || claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
			List<DistReportProj> allImplantForNodal = implantService.getAllImplantForNodal();
			return new ResponseEntity<List<DistReportProj>>(allImplantForNodal, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("implantNodalReportbetweendates")
	public ResponseEntity<?> implantNodalReportbetweendates(
			String fromDate,String toDate,
			HttpServletRequest httpServletRequest) {
		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("implantDistReport claims=>" + claimsFromToken.toString());
		
		if (claimsFromToken.getRoleId().equalsIgnoreCase("10") || claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
			 List<DistReportProj> allImplantForNodal = implantService.findAllImplantForNodalBetweenDates(java.sql.Date.valueOf(fromDate), java.sql.Date.valueOf(toDate));
			return new ResponseEntity<List<DistReportProj>>(allImplantForNodal, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
		}
		
	}

	// INDUSTRIES TABLE METHODS
	@GetMapping("/industriesByItiCode")
	public ResponseEntity<?> getByItiCode(HttpServletRequest httpServletRequest) {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		List<IndustriesEntity> byItiCode = industriesService
				.getByItiCode(Integer.parseInt(claimsFromToken.getInsCode()));
		byItiCode.forEach(a -> System.out.println(a.toString()));
		if (byItiCode.size() == 0) {
			return new ResponseEntity<String>("No industries found for your ITI", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<IndustriesEntity>>(byItiCode, HttpStatus.OK);
		}
	}

	@GetMapping("getIndustriesById")
	public ResponseEntity<?> getIndustriesById(@RequestParam("slno") Long slno) {
		System.out.println("/getIndustriesById/slno=" + slno);

		try {
			Optional<IndustriesEntity> industries = industriesService.getIndustries(slno);
			if (industries.isPresent()) {
				return new ResponseEntity<IndustriesEntity>(industries.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN SERIAL NUMBER: " + slno,
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("SOMETHING WENT WRONG, WHILE GETTING INDUSTRIES DATA",
					HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("industryMasterEntry")
	public ResponseEntity<?> industryMasterEntry(@RequestBody IndustryModel industryModel,
			HttpServletRequest httpServletRequest) {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")) {
			return new ResponseEntity<String>("Your not authorized to enter Industry data.", HttpStatus.BAD_REQUEST);
		}

		IndustryMaster byIndustryName = industryMasterService.getByIndustryName(industryModel.getIndustryName());
		if (byIndustryName == null) {
			// MaxCountProj maxCountIndustryMaster =
			// industryMasterService.getMaxCountIndustryMaster();

			IndustryMaster industryMaster = new IndustryMaster();
			industryMaster.setEntryTime(LocalDateTime.now());
			industryMaster.setEntryBy(claimsFromToken.getInsCode());
			// industryMaster.setIndustryId(maxCountIndustryMaster.getMax()+1);
			industryMaster.setIndustryName(industryModel.getIndustryName());
			industryMaster.setIndustryType(industryModel.getIndustryType());
			industryMaster.setIndustryAddress(industryModel.getIndustryAddress());

			IndustryMaster saveIndustryMaster = industryMasterService.saveIndustryMaster(industryMaster);

			if (saveIndustryMaster == null) {
				return new ResponseEntity<String>("Something went wrong while saving data.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>(
					"Industry Master details are saved succesfully with ID:" + industryMaster.getIndustryId(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(
					"This industry is already available with ID: " + byIndustryName.getIndustryId(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("updateIndustryMaster")
	public ResponseEntity<?> updateIndustryMaster(@RequestBody IndustryModel industryModel,
			HttpServletRequest httpServletRequest) {

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")) {
			return new ResponseEntity<String>("Your not authorized to enter Industry data.", HttpStatus.BAD_REQUEST);
		}

		Optional<IndustryMaster> industryMaster2 = industryMasterService
				.getIndustryMaster(industryModel.getIndustryId());
		if (industryMaster2.isPresent()) {
			IndustryMaster industryMaster = industryMaster2.get();
			industryMaster.setIndustryName(industryModel.getIndustryName());
			industryMaster.setIndustryType(industryModel.getIndustryType());
			industryMaster.setIndustryAddress(industryModel.getIndustryAddress());

			industryMaster.setEditBy(claimsFromToken.getInsCode());
			industryMaster.setEditTime(LocalDateTime.now());

			IndustryMaster saveIndustryMaster = industryMasterService.saveIndustryMaster(industryMaster);

			if (saveIndustryMaster == null) {
				return new ResponseEntity<String>("Something went wrong while saving data.", HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<String>(
					"Industry Master details are updated succesfully for ID:" + industryMaster.getIndustryId(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No data found for updating", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("deleteIndustryMaster")
	public ResponseEntity<?> deleteIndustryMaster(@RequestParam("industryId") Long industryId,
			HttpServletRequest httpServletRequest) {

		System.out.println("deleteIndustryMaster=industryId=>" + industryId);

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")) {
			return new ResponseEntity<String>("Your not authorized to enter Industry data.", HttpStatus.BAD_REQUEST);
		}

		try {
			Optional<IndustryMaster> industryMaster = industryMasterService.getIndustryMaster(industryId);
			if (industryMaster.isPresent()) {
				List<IndustriesEntity> byIndustryId = industriesService.getByIndustryId(industryId);
				if (byIndustryId.isEmpty()) {
					industryMasterService.deleteIndustryMaster(industryId);
					return new ResponseEntity<String>("Industry Master details are deleted successfully.",
							HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(
							"You could not deleted this industry, because this industry mapped with ITIs.",
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<String>("NO DATA FOUND", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("getAllIndustryMaster")
	public ResponseEntity<?> getIndustryMaster(HttpServletRequest httpServletRequest) {
		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10") && !claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
			return new ResponseEntity<String>("Your not authorized to enter Industry data.", HttpStatus.BAD_REQUEST);
		}
		List<IndustryMaster> allByOrderByIndustryNameAsc = industryMasterService.getAllByOrderByIndustryNameAsc();
		// allByOrderByIndustryNameAsc.stream().forEach(a->System.out.println(a.getIndustryName()));
		return new ResponseEntity<List<IndustryMaster>>(allByOrderByIndustryNameAsc, HttpStatus.OK);
	}

	@GetMapping("getAllIndustries")
	public ResponseEntity<?> getAllIndustries(HttpServletRequest httpServletRequest) {
		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")
				&& !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
			return new ResponseEntity<String>("Your not authorized to enter Industry data.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<IndustriesEntity>>(industriesService.getAllIndustries(), HttpStatus.OK);
	}

	@PostMapping("saveIndustries")
	public ResponseEntity<?> saveIndustries(@RequestBody IndustriesEntity industriesEntity,
			HttpServletRequest httpServletRequest) {
		System.out.println("saveIndustries=industriesEntity=>" + industriesEntity.toString());

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());

		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10") && !claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
			return new ResponseEntity<String>("Your not authorized to enter Industry data.", HttpStatus.BAD_REQUEST);
		}

		IndustriesEntity byIndustryIdAndItiCodeAndTradeShort = industriesService.getByIndustryIdAndItiCodeAndTradeShort(
				industriesEntity.getIndustryId(), industriesEntity.getItiCode(), industriesEntity.getTradeShort());
		if (byIndustryIdAndItiCodeAndTradeShort == null) {
//			MaxCountProj maxSlno = industriesService.getMaxSlno();
//			industriesEntity.setSlno(maxSlno.getMax()+1);

			Optional<IndustryMaster> industryMaster = industryMasterService
					.getIndustryMaster(industriesEntity.getIndustryId());
			if (industryMaster.isPresent()) {
				industriesEntity.setIndustryName(industryMaster.get().getIndustryName());
				industriesEntity.setIndustryType(industryMaster.get().getIndustryType());
			}

			Optional<ItiEntity> itiByCode = itiService.getByItiCodee(String.valueOf(industriesEntity.getItiCode()));
			if (itiByCode.isPresent()) {
				Optional<OldDistMasterEntity> byDistcode = oldDistMasterService
						.getByDistCode(itiByCode.get().getDistCode());
				if (byDistcode.isPresent()) {
					industriesEntity.setDistCode(Integer.parseInt(byDistcode.get().getDist_code()));
					industriesEntity.setDistName(byDistcode.get().getDist_name());
				}
				industriesEntity.setItiName(itiByCode.get().getItiName());
				industriesEntity.setNcvtMisCode(itiByCode.get().getNcvtCode());
			}
			Optional<ItiTradeMasterEntity> byTradeShort = itiTradeService
					.getByTradeShort(industriesEntity.getTradeShort());
			if (byTradeShort.isPresent()) {
				industriesEntity.setTradeCode(byTradeShort.get().getTradeCode());
				industriesEntity.setTradeName(byTradeShort.get().getTradeName());
			}
			industriesEntity.setEntryTime(LocalDateTime.now());
			industriesEntity.setEntryBy(claimsFromToken.getInsCode());
			industriesService.saveIndustry(industriesEntity);
			return new ResponseEntity<String>(
					"Mapping details are inserted successfully with ID: " + industriesEntity.getSlno(), HttpStatus.OK);
		} else {
			System.out.println("details already found");
			return new ResponseEntity<String>("This industry and trade is already mapped with this ITI.",
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getIndustryMasterById")
	public ResponseEntity<?> getIndustryMaster(@RequestParam("industryId") Long industryId) {

		Optional<IndustryMaster> industryMaster = industryMasterService.getIndustryMaster(industryId);
		if (industryMaster.isPresent()) {
			return new ResponseEntity<IndustryMaster>(industryMaster.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No data found with given industry id.", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("updateIndustries")
	public ResponseEntity<?> updateIndustries(@RequestBody IndustriesEntity industriesEntity,
			HttpServletRequest httpServletRequest) {
		System.out.println("./updateIndustries-industriesEntity->" + industriesEntity.toString());

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			System.out.println("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
				return new ResponseEntity<String>("Your not authorized to enter Industries data.",
						HttpStatus.BAD_REQUEST);
			}

			Optional<IndustriesEntity> industries = industriesService.getIndustries(industriesEntity.getSlno());
			System.out.println("###########################");
			if (industries.isPresent()) {
				IndustriesEntity industriesEntity2 = industries.get();

				// update industries information
				Optional<IndustryMaster> industryMaster = industryMasterService
						.getIndustryMaster(industriesEntity.getIndustryId());
				System.out.println("===============================");
				if (industryMaster.isPresent()) {
					industriesEntity2.setIndustryId(industriesEntity.getIndustryId());
					industriesEntity2.setIndustryName(industryMaster.get().getIndustryName());
					industriesEntity2.setIndustryType(industryMaster.get().getIndustryType());
				} else {
					return new ResponseEntity<String>("No data found about industry master.", HttpStatus.BAD_REQUEST);
				}

				// update industry master information
				Optional<ItiTradeMasterEntity> byTradeShort = itiTradeService
						.getByTradeShort(industriesEntity.getTradeShort());
				if (byTradeShort.isPresent()) {
					industriesEntity2.setTradeShort(industriesEntity.getTradeShort());
					industriesEntity2.setTradeCode(byTradeShort.get().getTradeCode());
					industriesEntity2.setTradeName(byTradeShort.get().getTradeName());
				} else {
					return new ResponseEntity<String>("No data found about trade master.", HttpStatus.BAD_REQUEST);
				}
				industriesService.saveIndustry(industriesEntity2);
				return new ResponseEntity<String>("DATA IS UPDATED SUCCESSFULLY.", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("NO DATA FOUND.", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("SOMETHING WENT WRONG.", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteIndustries")
	public ResponseEntity<?> deleteIndustries(@RequestParam("slno") Long slno, HttpServletRequest httpServletRequest) {
		System.out.println("./deleteIndustries/slno=>" + slno);

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			System.out.println("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
				return new ResponseEntity<String>("Your not authorized to enter Industries data.",
						HttpStatus.BAD_REQUEST);
			}

			List<ImplantEntity> findBySlno = implantService.findBySlno(slno);
			if (findBySlno.size() > 0) {
				return new ResponseEntity<String>(
						"Could not be deleted because Implant data is available with this industry.", HttpStatus.OK);
			}

			Optional<IndustriesEntity> industries = industriesService.getIndustries(slno);
			if (industries.isPresent()) {
				industriesService.deleteIndustry(slno);
				return new ResponseEntity<String>("Data is deleted Successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("NO DATA FOUND.", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("SOMETHING WENT WRONG.", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getDistinctCount")
	public ResponseEntity<?> getDistinctCount() {
		DistinctCountModel distinctCountModel = new DistinctCountModel();

		Long itisDistinctCount = implantService.getItisDistinctCount();
		Long industriesDistinctCount = implantService.getIndustriesDistinctCount();
		Long tradesDistinctCount = implantService.getTradesDistinctCount();
		Long traineesCount = implantService.getTraineesCount();

		distinctCountModel.setNoOfDistinctItis(itisDistinctCount);
		distinctCountModel.setNoOfDistinctIndustries(industriesDistinctCount);
		distinctCountModel.setNoOfDistinctTrades(tradesDistinctCount);
		distinctCountModel.setNoOfTrainees(traineesCount);

		return new ResponseEntity<DistinctCountModel>(distinctCountModel, HttpStatus.OK);
	}

	@GetMapping("getTraineesReportItiwise")
	public ResponseEntity<?> getTraineesReportItiwise() {
		return new ResponseEntity<List<TraineesReportItiwiseProj>>(implantService.getTraineesReportItiwise(),
				HttpStatus.OK);
	}

	@GetMapping("getTraineesReportIndustrieswise")
	public ResponseEntity<?> getTraineesReportIndustrieswise() {
		return new ResponseEntity<List<TraineesReportIndustrieswiseProj>>(
				implantService.getTraineesReportIndustrieswise(), HttpStatus.OK);
	}

	@GetMapping("getTraineesReportTrades")
	public ResponseEntity<?> getTraineesReportTrades() {
		return new ResponseEntity<List<TraineesReportTradesProj>>(implantService.getTraineesReportTrades(),
				HttpStatus.OK);
	}
	
	//Getting data between dates
	@GetMapping("getDatewiseDataReport")
	public ResponseEntity<?> getDatewiseDataReport(@RequestParam("from_date") String from_date, @RequestParam("to_date")  String to_date){
		System.out.println("From Date--->+from_date, && To Date--->+to_date");
		List<ImplantEntity> findInplantWithinDateRangeList = implantService.findInplantWithinDateRange(java.sql.Date.valueOf(from_date), java.sql.Date.valueOf(to_date));
	return new ResponseEntity<List<ImplantEntity>>(findInplantWithinDateRangeList, HttpStatus.OK);			
	}
	
	
	//Getting data 1 year based on ItiType and year 
	@GetMapping("/getInplantDataYearwise")
    public ResponseEntity<List<Map<String, Object>>> getInplantDataYearwise(
            @RequestParam("year") String year,
            @RequestParam("itiType") String itiType) {
        List<Map<String, Object>> report = implantService.getReport(year, itiType);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
	 
	//Getting data 2 years like 2024 and 2023 (24 months)  based on ItiType and years
	@GetMapping("/getInplantTwoYearsDataYearwise")
	public ResponseEntity<?>getInplantTwoYearsDataYearwise(
			@RequestParam("currentYear") String currentYear,
			@RequestParam("lastYear") String lastYear,
            @RequestParam("itiType") String itiType) {
        List<TwoYearsDataProjection> report = implantService.getfetchTwoYearReport(itiType, currentYear, lastYear);
       ObjectMapper mapper=new ObjectMapper();
        try {
			System.out.println(mapper.writeValueAsString(report));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(report, HttpStatus.OK);
			
			
	}
	
	
	

	// =============INDUSTRY PARTNER DETAILS APIs====================
	@Autowired
	private IndustryPartnerDetailsService industryPartnerDetailsService;

	//saving new industry partner details
	@PostMapping("saveIndustryPartnerDetails")
	public ResponseEntity<?> saveIndustryPartnerDetails(
			@RequestBody IndustryPartnerDetailsModel industryPartnerDetailsModel,
			HttpServletRequest httpServletRequest) {
		logger.info("industryPartnerDetailsModel=>" + industryPartnerDetailsModel.toString());

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			logger.info("claims=>" + claimsFromToken.toString());
			
			if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")
					&& !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
				return new ResponseEntity<String>("Your not authorized to enter data.", HttpStatus.BAD_REQUEST);
			}

			IndustryPartnerDetails industryPartnerDetails = new IndustryPartnerDetails();

			industryPartnerDetails.setItiCode(industryPartnerDetailsModel.getItiCode().trim());
			industryPartnerDetails.setDistCode(industryPartnerDetailsModel.getDistCode().trim());
			industryPartnerDetails.setRevisedLeadSector(industryPartnerDetailsModel.getRevisedLeadSector().trim());
			industryPartnerDetails
					.setRevisedLeadIndustryPartner(industryPartnerDetailsModel.getRevisedLeadIndustryPartner().trim());
			industryPartnerDetails.setProposedNewTrade(industryPartnerDetailsModel.getProposedNewTrade());
			industryPartnerDetails.setEntryDate(LocalDateTime.now());
			industryPartnerDetails.setEntryBy(claimsFromToken.getInsCode());

			IndustryPartnerDetails saveIndustryPartnerDetails = industryPartnerDetailsService
					.saveIndustryPartnerDetails(industryPartnerDetails);

			return new ResponseEntity<String>(
					"Industry Partner Details are saved successfully with ID: " + saveIndustryPartnerDetails.getPid(),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Something went wrong while saving Inudstry Partner Details. Please Try Again.",
					HttpStatus.BAD_REQUEST);
		}

	}

	//getting all records about industry partner details
	@GetMapping("getAllIndustryPartnerDetails")
	public ResponseEntity<?> getAllIndustryPartnerDetails(HttpServletRequest httpServletRequest) {

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			logger.info("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")
					&& !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
				return new ResponseEntity<String>("Your not authorized to get data.", HttpStatus.BAD_REQUEST);
			}
			List<IndustryPartnerDetails> allIndustryPartnerDetails = industryPartnerDetailsService
					.getAllIndustryPartnerDetails();
			logger.info("allIndustryPartnerDetails size=>" + allIndustryPartnerDetails.size());
			return new ResponseEntity<List<IndustryPartnerDetails>>(allIndustryPartnerDetails, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Something went wrong while getting Inudstry Partner Details. Please Try Again.",
					HttpStatus.BAD_REQUEST);
		}
	}
	//deleting a industry partner details by id
	@DeleteMapping("deleteIndustryPartnerDetailsById")
	public ResponseEntity<?> deleteIndustryPartnerDetailsById(HttpServletRequest httpServletRequest,
			@RequestParam("pid") String pid) {

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			logger.info("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")
					&& !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
				return new ResponseEntity<String>("Your not authorized to modify data.", HttpStatus.BAD_REQUEST);
			}
			Optional<IndustryPartnerDetails> industryPartnerDetails = industryPartnerDetailsService
					.getIndustryPartnerDetails(Long.valueOf(pid));
			if (industryPartnerDetails.isPresent()) {
				IndustryPartnerDetails industryPartnerDetails2 = industryPartnerDetails.get();
				industryPartnerDetails2.setEntryBy(claimsFromToken.getInsCode());
				industryPartnerDetails2.setEntryDate(LocalDateTime.now());
				industryPartnerDetailsService.saveIndustryPartnerDetails(industryPartnerDetails2);

				industryPartnerDetailsService.deleteIndustryPartnerDetailsById(industryPartnerDetails2.getPid());
				return new ResponseEntity<String>("Requested Industry Partner Details are deleted successfully.",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN INFORMATION.", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Something went wrong while deleting Inudstry Partner Details. Please Try Again.",
					HttpStatus.BAD_REQUEST);
		}
	}
	//updating industry partner details
	@PostMapping("updateIndustryPartnerDetails")
	public ResponseEntity<?> updateIndustryPartnerDetails(
			@RequestBody IndustryPartnerDetailsModel industryPartnerDetailsModel,
			HttpServletRequest httpServletRequest) {
		logger.info("industryPartnerDetailsModel=>" + industryPartnerDetailsModel.toString());

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			logger.info("claims=>" + claimsFromToken.toString());
			
			Optional<IndustryPartnerDetails> industryPartnerDetails2 = industryPartnerDetailsService
					.getIndustryPartnerDetails(Long.valueOf(industryPartnerDetailsModel.getPid()));
			
			if(industryPartnerDetails2.isPresent()) {
				IndustryPartnerDetails industryPartnerDetails = industryPartnerDetails2.get();
				industryPartnerDetails.setItiCode(industryPartnerDetailsModel.getItiCode().trim());
				industryPartnerDetails.setDistCode(industryPartnerDetailsModel.getDistCode().trim());
				industryPartnerDetails.setRevisedLeadSector(industryPartnerDetailsModel.getRevisedLeadSector().trim());
				industryPartnerDetails.setRevisedLeadIndustryPartner(industryPartnerDetailsModel.getRevisedLeadIndustryPartner().trim());
				industryPartnerDetails.setProposedNewTrade(industryPartnerDetailsModel.getProposedNewTrade());
				industryPartnerDetails.setEntryDate(LocalDateTime.now());
				industryPartnerDetails.setEntryBy(claimsFromToken.getInsCode());
				
				industryPartnerDetailsService.saveIndustryPartnerDetails(industryPartnerDetails);
				return new ResponseEntity<String>(
						"Industry Partner Details are Updated successfully.",
						HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN INFORMATION.", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Something went wrong while updating Inudstry Partner Details.",
					HttpStatus.BAD_REQUEST);
		}

	}
	//getting a record of industry partner details by id
	@GetMapping("getIndustryPartnerDetailsById")
	public ResponseEntity<?> getIndustryPartnerDetailsById(HttpServletRequest httpServletRequest,
			@RequestParam("pid") String pid) {

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			logger.info("claims=>" + claimsFromToken.toString());

			if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")
					&& !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
				return new ResponseEntity<String>("Your not authorized to get data.", HttpStatus.BAD_REQUEST);
			}
			Optional<IndustryPartnerDetails> industryPartnerDetails = industryPartnerDetailsService.getIndustryPartnerDetails(Long.valueOf(pid));
			if(industryPartnerDetails.isPresent()) {
				logger.info("industryPartnerDetails=>" + industryPartnerDetails.toString());
				return new ResponseEntity<IndustryPartnerDetails>(industryPartnerDetails.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN INFORMATION.", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Something went wrong while getting Inudstry Partner Details. Please Try Again.",
					HttpStatus.BAD_REQUEST);
		}
	}

}
