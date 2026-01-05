package com.iti.PlacementsBackend.controller;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;
import com.iti.PlacementsBackend.entity.hrm.DeptTest;
import com.iti.PlacementsBackend.entity.hrm.DeptTestId;
import com.iti.PlacementsBackend.entity.hrm.DeptTestMaster;
import com.iti.PlacementsBackend.entity.hrm.DesignationMaster;
import com.iti.PlacementsBackend.entity.hrm.Employee;
import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionDetails;
import com.iti.PlacementsBackend.entity.hrm.EmployeePromotionId;
import com.iti.PlacementsBackend.entity.hrm.EmployeeTransfer;
import com.iti.PlacementsBackend.entity.hrm.QualMast;
import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;
import com.iti.PlacementsBackend.entity.inplant.IndustriesEntity;
import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.model.hrm.EmployeeDetails;
import com.iti.PlacementsBackend.repo.hrm.EmployeePromotionDetailsRepository;
import com.iti.PlacementsBackend.service.hrm.CasteMasterService;
import com.iti.PlacementsBackend.service.hrm.DeptTestMasterService;
import com.iti.PlacementsBackend.service.hrm.DeptTestService;
import com.iti.PlacementsBackend.service.hrm.DesignationMasterService;
import com.iti.PlacementsBackend.service.hrm.EmployeePromotionDetailsSevice;
import com.iti.PlacementsBackend.service.hrm.EmployeeService;
import com.iti.PlacementsBackend.service.hrm.EmployeeTransferService;
import com.iti.PlacementsBackend.service.hrm.QualMastService;
import com.iti.PlacementsBackend.service.hrm.SubCasteMasterService;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hrm")
@CrossOrigin(origins = "*")
public class HrmController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImplantController.class);
	@Autowired
	private SubCasteMasterService subCasteMasterService;
	
	@Autowired
	private DeptTestMasterService deptTestMasterService;
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private DeptTestService deptTestService; 
	
	@Autowired
	private EmployeePromotionDetailsSevice employeePromotionDetailsSevice;
	
	@Autowired
	private DesignationMasterService designationMasterService;
	 @Autowired
	    private CasteMasterService casteMasterService;
	 @Autowired
	 private QualMastService qualMastService;
	 @Autowired
		private EmployeeTransferService employeeTransferService;
	 
	 
	
	// Create Employee
	 @PostMapping(value = "/saveEmployee", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> saveEmployee(
	            @RequestParam("employeeName") String employeeName,
	            @RequestParam("employeeCode") String employeeCode,
	            @RequestParam("ddoCode") String ddoCode,
	            @RequestParam("postSanctioningGoNo") String postSanctioningGoNo,
	            @RequestParam(value = "goCertificatePath[]", required = false) List<MultipartFile> goCertificatePath,
	            @RequestParam("designation") Long designation,
	            @RequestParam("contactNumber") String contactNumber,
	            @RequestParam("tradeName") String tradeName,
	            @RequestParam("dob") String dob,
	            @RequestParam("dor") String dor,
	            @RequestParam("academicQualification") Long academicQualification,
	            @RequestParam("technicalQualification") Long technicalQualification,
	            @RequestParam(value = "goTechCertificatePath[]", required=false) List<MultipartFile> goTechCertificatePath,
	            @RequestParam("reservationCategory") Long reservationCategory,
	            @RequestParam("subCaste") Long subCaste,
	            @RequestParam("initialAppointmentPost") Long initialAppointmentPost,
	            @RequestParam("dateOfInitialAppointment")  String dateOfInitialAppointment,
	            @RequestParam("dateOfReportingPresentStation")  String dateOfReportingPresentStation,
	            @RequestParam("promotionPost[]") List<Long> promotionPost,
	            @RequestParam(value = "certificatePath[]", required = false) List<MultipartFile> certificateFiles,
	            @RequestParam("reportingDate[]") List<String> reportingDate,
	            @RequestParam("placeOfReporting[]") List<String> placeOfReporting,
	            @RequestParam("testName[]") List<Long> testName,
	            @RequestParam("passDate[]") List<String> passDate,
	            
	            HttpServletRequest httpServletRequest) throws IOException {
		 
		 ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			System.out.println("claims=>" + claimsFromToken.toString());
			
		 
	        
	        try {
	            List<String> certificatePaths = new ArrayList<>();
	            List<String> goTechCertificatePaths = new ArrayList<>();
	            List<String> goCertificatePaths = new ArrayList<>();

	            // Save Files to Server & Get Paths
	         // Define the directory where files will be stored
	             String UPLOAD_DIR = "C:/employee-certificates/";

	            if (certificateFiles != null && !certificateFiles.isEmpty()) {
	                for (MultipartFile file : certificateFiles) {
	                    try {
	                        // Ensure the directory exists
	                        File uploadDir = new File(UPLOAD_DIR);
	                        if (!uploadDir.exists()) {
	                            uploadDir.mkdirs(); // Create the directory if it doesn't exist
	                        }

	                        // Generate a unique file name
	                        String originalFilename = file.getOriginalFilename();
	                        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
	                        String filePath = UPLOAD_DIR + uniqueFilename;

	                        // Save file to the specified path
	                        File destinationFile = new File(filePath);
	                        file.transferTo(destinationFile);

	                        // Store the path in the list
	                        certificatePaths.add(filePath);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        throw new RuntimeException("Failed to save file: " + file.getOriginalFilename(), e);
	                    }
	                }
	            }
	            if (goTechCertificatePath != null && !goTechCertificatePath.isEmpty()) {
	                for (MultipartFile file : goTechCertificatePath) {
	                	System.out.println("goTechCertificatePath--->in path");
	                    try {
	                        // Ensure the directory exists
	                        File uploadDir = new File(UPLOAD_DIR);
	                        if (!uploadDir.exists()) {
	                            uploadDir.mkdirs(); // Create the directory if it doesn't exist
	                        }

	                        // Generate a unique file name
	                        String originalFilename = file.getOriginalFilename();
	                        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
	                        String filePath = UPLOAD_DIR + uniqueFilename;

	                        // Save file to the specified path
	                        File destinationFile = new File(filePath);
	                        file.transferTo(destinationFile);

	                        // Store the path in the list
	                        goTechCertificatePaths.add(filePath);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        throw new RuntimeException("Failed to save file: " + file.getOriginalFilename(), e);
	                    }
	                }
	            }
	            if (goCertificatePath != null && !goCertificatePath.isEmpty()) {
	            	for (MultipartFile file : goCertificatePath) {
	            		System.out.println("goCertificatePath--->in path");
	            		try {
	            			// Ensure the directory exists
	            			File uploadDir = new File(UPLOAD_DIR);
	            			if (!uploadDir.exists()) {
	            				uploadDir.mkdirs(); // Create the directory if it doesn't exist
	            			}
	            			
	            			// Generate a unique file name
	            			String originalFilename = file.getOriginalFilename();
	            			String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
	            			String filePath = UPLOAD_DIR + uniqueFilename;
	            			
	            			// Save file to the specified path
	            			File destinationFile = new File(filePath);
	            			file.transferTo(destinationFile);
	            			
	            			// Store the path in the list
	            			goCertificatePaths.add(filePath);
	            		} catch (IOException e) {
	            			e.printStackTrace();
	            			throw new RuntimeException("Failed to save file: " + file.getOriginalFilename(), e);
	            		}
	            	}
	            }


	            // Create Employee Object
	            Employee employee = new Employee();
	            employee.setEmployeeName(employeeName);
	            employee.setEmployeeCode(employeeCode);
	            employee.setDdoCode(ddoCode);
	            employee.setPostSanctioningGoNo(postSanctioningGoNo);
	            employee.setGoCertificatePath(String.join(",", goCertificatePaths)); // Save paths as CSV
	            employee.setGoTechCertificatePath(String.join(",", goTechCertificatePaths)); // Save paths as CSV
	            employee.setDesignation(designation);
	            employee.setContactNumber(contactNumber);
	            employee.setTradeName(tradeName);
	            employee.setDob(java.sql.Date.valueOf(dob));
	            employee.setDor(java.sql.Date.valueOf(dor));
	            employee.setAcademicQualification(academicQualification);
	            employee.setTechnicalQualification(technicalQualification);
	            employee.setReservationCategory(reservationCategory);
	            employee.setSubCaste(subCaste);
	            employee.setInitialAppointmentPost(initialAppointmentPost);
	            employee.setDateOfInitialAppointment(java.sql.Date.valueOf(dateOfInitialAppointment));
	            employee.setDateOfReportingPresentStation(java.sql.Date.valueOf(dateOfReportingPresentStation));
	            employee.setItiCode(claimsFromToken.getInsCode());
	            employee.setEntryBy(claimsFromToken.getUsername());
	            employee.setEntryDate(LocalDateTime.now());
	            
	            
	            // List to hold EmployeePromotionDetails
	            List<EmployeePromotionDetails> promotionDetailsList = new ArrayList<>();
	            for (int i = 0; i < promotionPost.size(); i++) {
	                EmployeePromotionDetails promotionDetails = new EmployeePromotionDetails();
	                EmployeePromotionId employeePromotionId=new EmployeePromotionId(promotionPost.get(i), employeeCode);
	                promotionDetails.setId(employeePromotionId);
	                promotionDetails.setPlaceOfReporting(placeOfReporting.get(i));
	                promotionDetails.setItiCode(claimsFromToken.getInsCode());
	                promotionDetails.setCertificatePath(
	                    certificateFiles != null && certificateFiles.size() > i ? 
	                    certificatePaths.get(i) : null
	                );
	                promotionDetails.setReportingDate(java.sql.Date.valueOf(reportingDate.get(i)));
	                promotionDetailsList.add(promotionDetails);
	            }
	            
	           // List to hold DeptTest details
	            List<DeptTest> deptTestList = new ArrayList<>();
	            for (int i = 0; i < testName.size(); i++) {
	                DeptTest deptTest = new DeptTest();
	                DeptTestId deptTestId=new DeptTestId(testName.get(i), employeeCode);
	                deptTest.setId(deptTestId);
	                deptTest.setItiCode(claimsFromToken.getInsCode());
	                deptTest.setPassDate(java.sql.Date.valueOf(passDate.get(i)));
	                deptTestList.add(deptTest);
	            }
	            

	         // Save Employee along with related details
	            Employee savedEmployee = employeeService.saveEmployee(employee, promotionDetailsList, deptTestList);

	            return ResponseEntity.ok("Employee Data saved successfully");
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving employee: " + e.getMessage());
	        }
	    }
	 
	 
	
	
	@PostMapping("/castes/saveCastes")
    public ResponseEntity<CasteMaster> saveCaste(@RequestBody CasteMaster casteMaster) {
        CasteMaster savedCaste = casteMasterService.saveCaste(casteMaster);
        return ResponseEntity.ok(savedCaste);
    }

    @GetMapping("/castes/allCastes")
    public ResponseEntity<List<CasteMaster>> getAllCastes() {
        return ResponseEntity.ok(casteMasterService.getAllCastes());
    }

    @GetMapping("/castes/{id}")
    public ResponseEntity<CasteMaster> getCasteById(@PathVariable Long id) {
        Optional<CasteMaster> casteMaster = casteMasterService.getCasteById(id);
        return casteMaster.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/castes/category/{category}")
    public ResponseEntity<CasteMaster> getCasteByCategory(@PathVariable String category) {
        Optional<CasteMaster> casteMaster = casteMasterService.getCasteByCategory(category);
        return casteMaster.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/castes/update/{id}")
    public ResponseEntity<CasteMaster> updateCaste(@PathVariable Long id, @RequestBody CasteMaster casteDetails) {
        CasteMaster updatedCaste = casteMasterService.updateCaste(id, casteDetails);
        return updatedCaste != null ? ResponseEntity.ok(updatedCaste) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/castes/delete/{id}")
    public ResponseEntity<String> deleteCaste(@PathVariable Long id) {
        casteMasterService.deleteCaste(id);
        return ResponseEntity.ok("Caste record deleted successfully");
    }
    
    //qual Mast APIs
    // Create a new Qualification
    @PostMapping("/qualifications/saveQual")
    public ResponseEntity<QualMast> createQualification(@RequestBody QualMast qualMast) {
        QualMast savedQualification = qualMastService.saveQualification(qualMast);
        return ResponseEntity.ok(savedQualification);
    }

    // Get all Qualifications
    @GetMapping("/qualifications/allQual")
    public ResponseEntity<List<QualMast>> getAllQualifications() {
        return ResponseEntity.ok(qualMastService.getAllQualifications());
    }

    // Get Qualification by ID
    @GetMapping("/qualifications/{id}")
    public ResponseEntity<QualMast> getQualificationById(@PathVariable Long id) {
        Optional<QualMast> qualification = qualMastService.getQualificationById(id);
        return qualification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Qualification by ID
    @DeleteMapping("/qualifications/delete/{id}")
    public ResponseEntity<String> deleteQualification(@PathVariable Long id) {
        qualMastService.deleteQualification(id);
        return ResponseEntity.ok("Qualification deleted successfully.");
    }
    
    	//Designation Master Details APIs
 // Save Designation
    @PostMapping("/designations/save")
    public ResponseEntity<DesignationMaster> saveDesignation(@RequestBody DesignationMaster designation) {
        return ResponseEntity.ok(designationMasterService.saveDesignation(designation));
    }

    // Get all Designations
    @GetMapping("/designations/all")
    public ResponseEntity<List<DesignationMaster>> getAllDesignations() {
        return ResponseEntity.ok(designationMasterService.getAllDesignations());
    }

    // Get Designation by ID
    @GetMapping("/designations/{id}")
    public ResponseEntity<DesignationMaster> getDesignationById(@PathVariable Long id) {
        Optional<DesignationMaster> designation = designationMasterService.getDesignationById(id);
        return designation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get Designation by Name
    @GetMapping("/designations/name/{name}")
    public ResponseEntity<DesignationMaster> getDesignationByName(@PathVariable String name) {
        Optional<DesignationMaster> designation = designationMasterService.getDesignationByName(name);
        return designation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Designation
    @PutMapping("/designations/update/{id}")
    public ResponseEntity<DesignationMaster> updateDesignation(@PathVariable Long id, @RequestBody DesignationMaster designationDetails) {
        DesignationMaster updatedDesignation = designationMasterService.updateDesignation(id, designationDetails);
        return updatedDesignation != null ? ResponseEntity.ok(updatedDesignation) : ResponseEntity.notFound().build();
    }

    // Delete Designation
    @DeleteMapping("/designations/delete/{id}")
    public ResponseEntity<String> deleteDesignation(@PathVariable Long id) {
        designationMasterService.deleteDesignation(id);
        return ResponseEntity.ok("Designation deleted successfully.");
    }
    
    //Dept Tests APIs
 // Save Test
    @PostMapping("/tests/save")
    public ResponseEntity<DeptTestMaster> saveTest(@RequestBody DeptTestMaster test) {
        return ResponseEntity.ok(deptTestMasterService.saveTest(test));
    }

    // Get all Tests
    @GetMapping("/tests/all")
    public ResponseEntity<List<DeptTestMaster>> getAllTests() {
        return ResponseEntity.ok(deptTestMasterService.getAllTests());
    }

    // Get Test by ID
    @GetMapping("/tests/{id}")
    public ResponseEntity<DeptTestMaster> getTestById(@PathVariable Long id) {
        Optional<DeptTestMaster> test = deptTestMasterService.getTestById(id);
        return test.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get Test by Name
    @GetMapping("/tests/name/{name}")
    public ResponseEntity<DeptTestMaster> getTestByName(@PathVariable String name) {
        Optional<DeptTestMaster> test = deptTestMasterService.getTestByName(name);
        return test.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Test
    @PutMapping("/tests/update/{id}")
    public ResponseEntity<DeptTestMaster> updateTest(@PathVariable Long id, @RequestBody DeptTestMaster testDetails) {
        DeptTestMaster updatedTest = deptTestMasterService.updateTest(id, testDetails);
        return updatedTest != null ? ResponseEntity.ok(updatedTest) : ResponseEntity.notFound().build();
    }

    // Delete Test
    @DeleteMapping("/tests/delete/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
        deptTestMasterService.deleteTest(id);
        return ResponseEntity.ok("Test deleted successfully.");
    }
    
    
    //SubCastes Master APIS
 // Save SubCaste with Caste ID
    @PostMapping("/subcastes/save/{casteId}")
    public ResponseEntity<SubCasteMaster> saveSubCaste(@PathVariable Long casteId, @RequestBody SubCasteMaster subCaste) {
        return ResponseEntity.ok(subCasteMasterService.saveSubCaste(casteId, subCaste));
    }

    // Get all SubCastes
    @GetMapping("/subcastes/all")
    public ResponseEntity<List<SubCasteMaster>> getAllSubCastes() {
        return ResponseEntity.ok(subCasteMasterService.getAllSubCastes());
    }

    // Get SubCaste by ID
    @GetMapping("/subcastes/{subCasteId}")
    public ResponseEntity<SubCasteMaster> getSubCasteById(@PathVariable Long subCasteId) {
        Optional<SubCasteMaster> subCaste = subCasteMasterService.getSubCasteById(subCasteId);
        return subCaste.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get SubCastes by Caste ID
    @GetMapping("/subcastes/caste")
    public ResponseEntity<List<SubCasteMaster>> getSubCastesByCasteId(@RequestParam("casteId") Long casteId) {
        return ResponseEntity.ok(subCasteMasterService.getSubCastesByCasteId(casteId));
    }

    // Update SubCaste
    @PutMapping("/subcastes/update/{subCasteId}")
    public ResponseEntity<SubCasteMaster> updateSubCaste(@PathVariable Long subCasteId, @RequestBody SubCasteMaster subCasteDetails) {
        SubCasteMaster updatedSubCaste = subCasteMasterService.updateSubCaste(subCasteId, subCasteDetails);
        return updatedSubCaste != null ? ResponseEntity.ok(updatedSubCaste) : ResponseEntity.notFound().build();
    }

    // Delete SubCaste
    @DeleteMapping("/subcastes/delete/{subCasteId}")
    public ResponseEntity<String> deleteSubCaste(@PathVariable Long subCasteId) {
        subCasteMasterService.deleteSubCaste(subCasteId);
        return ResponseEntity.ok("SubCaste deleted successfully.");
    }

    
    
    
    // ITI LEVEL REPORT
//    @GetMapping("/employeeITIReport")
// 
// 	public ResponseEntity<?> getByItiCode(@RequestParam String itiCode) {
// 	    List<Employee> employees = employeeService.findByItiCode(itiCode);
// 	    System.out.println(employees.toString());
// 	  
// 	    
// 	    if (employees.isEmpty()) {
// 	        return new ResponseEntity<>("No employees found for the given ITI code.", HttpStatus.NOT_FOUND);
// 	    }
// 	    
// 	    return new ResponseEntity<>(employees, HttpStatus.OK);
// 	}
    
//    @GetMapping("/employeeITI")
//    
// 	public ResponseEntity<?> getByItiCode(@RequestParam String itiCode) {
// 	    List<DeptTest> deptTests = deptTestService.getByItiCode(itiCode);
// 	    System.out.println(deptTests.toString());
// 	  
// 	    
// 	    if (deptTests.isEmpty()) {
// 	        return new ResponseEntity<>("No employees found for the given ITI code.", HttpStatus.NOT_FOUND);
// 	    }
// 	    
// 	    return new ResponseEntity<>(deptTests, HttpStatus.OK);
// 	}
    
//    @GetMapping("/employeeFindById")
//    public ResponseEntity<?> findById(@RequestParam String employeeCode) {
//    	EmployeeDetails employeeDetails = new EmployeeDetails(); 
//    	List<DeptTest> deptTests = deptTestService.findByEmployeeCode(employeeCode);
//    	List<EmployeePromotionDetails> employeePromotionDetails = employeePromotionDetailsSevice.findById_EmployeeCode(employeeCode);
//    	System.out.println(deptTests.toString());
//    	
//    	  if (deptTests.isEmpty()) {
//   	        return new ResponseEntity<>("No employees found for the given ITI code.", HttpStatus.NOT_FOUND);
//   	    }
//   	    employeeDetails.setDeptTests(deptTests);
//   	    employeeDetails.setEmployeePromotionDetails(employeePromotionDetails);
//   	    return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
//   	}
//    
    
      
    @GetMapping("/employeeITIReport")
 	public ResponseEntity<?> getByItiCode(HttpServletRequest httpServletRequest) {

 		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
 		System.out.println("claims=>" + claimsFromToken.toString());

 		if (claimsFromToken.getRoleId().equalsIgnoreCase("4")) {

 			List<Employee> allByUserId = employeeService.findByItiCode(claimsFromToken.getInsCode());
 			System.out.println("employeeITIReport=listsize=>" + allByUserId.size());

 			return new ResponseEntity<List<Employee>>(allByUserId, HttpStatus.OK);
 		} else {
 			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
 		}
 	}
    
    @GetMapping("/employeeFindById")
    public ResponseEntity<?> findById(@RequestParam String employeeCode, HttpServletRequest httpServletRequest) {

        ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
        System.out.println("claims=>" + claimsFromToken.toString());

        // Allow only if roleId is "4" or "10"
        if (claimsFromToken.getRoleId().equalsIgnoreCase("4") || claimsFromToken.getRoleId().equalsIgnoreCase("10")) {

            EmployeeDetails employeeDetails = new EmployeeDetails(); 
            List<DeptTest> deptTests = deptTestService.findByEmployeeCode(employeeCode);
            List<EmployeePromotionDetails> employeePromotionDetails = employeePromotionDetailsSevice.findById_EmployeeCode(employeeCode);

            System.out.println(deptTests.toString());

            if (deptTests.isEmpty()) {
                return new ResponseEntity<>("No employees found for the given ITI code.", HttpStatus.NOT_FOUND);
            }

            employeeDetails.setDeptTests(deptTests);
            employeeDetails.setEmployeePromotionDetails(employeePromotionDetails);
            return new ResponseEntity<>(employeeDetails, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("You are not authorized to view the data.", HttpStatus.FORBIDDEN);
        }
    }
    
    @GetMapping("getAllEmployees")
   	public ResponseEntity<?> getAllLabItems(HttpServletRequest httpServletRequest){
       	ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
   		System.out.println("claims=>" + claimsFromToken.toString());
   		
   		if(!claimsFromToken.getRoleId().equalsIgnoreCase("10") && !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
   			return new ResponseEntity<String>("Your not authorized to view Lab data.", HttpStatus.BAD_REQUEST);
   		}
   		
   		return new ResponseEntity<List<Employee>>(employeeService.findAll(), HttpStatus.OK);
   	}
       
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
            @RequestParam("pwdPercentage") String pwdPercentage,
            @RequestParam("spouseWorkingPlace") String spouseWorkingPlace,
            @RequestParam("challengedChildren") String challengedChildren,
            @RequestParam("widowCase") String widowCase,
            @RequestParam("medicalGrounds") String medicalGrounds,
            @RequestParam("workedTribalArea") String workedTribalArea,
            @RequestParam("areaName") String areaName,
            @RequestParam(value = "officeBearerTerms",required = false) Integer officeBearerTerms,
            @RequestParam("officeBearerYears") Integer officeBearerYears,
            @RequestParam("passPercentage") String passPercentage,
            @RequestParam("placementPercentage") String placementPercentage,
            @RequestParam("remarks") String remarks,
            @RequestParam(value = "pbdCertificate", required = false) MultipartFile pbdCertificate,
            @RequestParam(value = "spouseCertificate", required = false) MultipartFile spouseCertificate,
            @RequestParam(value = "challengedChildrenCert", required = false) MultipartFile challengedChildrenCert,
            @RequestParam(value = "widowCertificate", required = false) MultipartFile widowCertificate,
            @RequestParam(value = "medicalCertificate", required = false) MultipartFile medicalCertificate,
            @RequestParam(value = "officeBearerCert", required = false) MultipartFile officeBearerCert,
            HttpServletRequest httpServletRequest) throws IOException {
		 
		 ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			System.out.println("claims=>" + claimsFromToken.toString());
			
        try {
            String uploadDir = "C:/employee-transfer-certificates/";
            EmployeeTransfer employeeTransfer = new EmployeeTransfer();

            // Set simple fields
            employeeTransfer.setEmployeeName(employeeName);
            employeeTransfer.setEmployeeCode(employeeCode);
            employeeTransfer.setDesignation(designation);
            
            // Optional trade name check for foreign key issue
            if (tradeName != null && !tradeName.trim().isEmpty()) {
                employeeTransfer.setTradeName(tradeName);
            } else {
                employeeTransfer.setTradeName(null);
            }
            
            employeeTransfer.setPresentWorkingStation(presentWorkingStation);
            employeeTransfer.setWorkingSinceAllCadres(Date.valueOf(workingSinceAllCadres));
            employeeTransfer.setDob(Date.valueOf(dob));
            employeeTransfer.setDor(Date.valueOf(dor));
            employeeTransfer.setServiceYears(serviceYears);
            employeeTransfer.setServiceMonths(serviceMonths);
            employeeTransfer.setServiceDays(serviceDays);
            employeeTransfer.setBenchmarkDisability(benchmarkDisability);
            employeeTransfer.setPbdCategory(pbdCategory);
            employeeTransfer.setPwdPercentage(pwdPercentage);
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
            employeeTransfer.setItiCode(claimsFromToken.getInsCode());
            employeeTransfer.setEntryBy(claimsFromToken.getUsername());
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


    
}
