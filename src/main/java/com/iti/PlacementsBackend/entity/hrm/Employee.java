package com.iti.PlacementsBackend.entity.hrm;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;

import jakarta.persistence.*;

@Entity
@Table(name="employee",schema="hrms")
public class Employee {

    @Id
    @Column(name = "employee_code", unique = true, nullable = false)
    private String employeeCode;  

    @Column(name = "ddo_code", nullable = false)
    private String ddoCode;

    @Column(name = "post_sanctioning_go_no")
    private String postSanctioningGoNo;
    
    @Column(name = "iti_code")
    private String itiCode;
	@ManyToOne
	@JoinColumn(name = "iti_code",referencedColumnName = "itiCode", insertable = false, updatable = false)
	private ItiEntity itiEntity;

    @Column(nullable = false)
    private String goCertificatePath;  

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "designation")
    private Long designation;
    
    @ManyToOne
    @JoinColumn(name = "designation", referencedColumnName = "desig_code", insertable = false, updatable = false)
    private DesignationMaster designationMaster;
    

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "trade_name")
    private String tradeName;
    
    @ManyToOne
    @JoinColumn(name = "trade_name", referencedColumnName = "tradeShort", insertable = false, updatable = false)
    private ItiTradeMasterEntity itiTradeMasterEntity;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;

    @Temporal(TemporalType.DATE)
    @Column(name = "dor")
    private Date dor;

    @Column(name = "academic_qualification")
    private Long academicQualification;
    
    public QualMast getQualMast() {
		return qualMast;
	}

	public void setQualMast(QualMast qualMast) {
		this.qualMast = qualMast;
	}

	public QualMast getQualMast2() {
		return qualMast2;
	}

	public void setQualMast2(QualMast qualMast2) {
		this.qualMast2 = qualMast2;
	}

	public void setAcademicQualification(Long academicQualification) {
		this.academicQualification = academicQualification;
	}

	public void setTechnicalQualification(Long technicalQualification) {
		this.technicalQualification = technicalQualification;
	}


	@ManyToOne
    @JoinColumn(name = "academic_qualification", referencedColumnName = "qual_code", insertable = false, updatable = false)
    private QualMast qualMast;

    @Column(name = "technical_qualification")
    private Long technicalQualification;
    
    @ManyToOne
    @JoinColumn(name = "technical_qualification", referencedColumnName = "qual_code", insertable = false, updatable = false)
    private QualMast qualMast2;

    @Column(nullable = false)
    private String goTechCertificatePath;

    @Column(name = "reservation_category")
    private Long reservationCategory;
    
    @ManyToOne
    @JoinColumn(name = "reservation_category", referencedColumnName = "caste_id", insertable = false, updatable = false)
    private CasteMaster casteMaster;

    public ItiEntity getItiEntity() {
		return itiEntity;
	}

	public void setItiEntity(ItiEntity itiEntity) {
		this.itiEntity = itiEntity;
	}

	public DesignationMaster getDesignationMaster() {
		return designationMaster;
	}

	public void setDesignationMaster(DesignationMaster designationMaster) {
		this.designationMaster = designationMaster;
	}

	public CasteMaster getCasteMaster() {
		return casteMaster;
	}

	public void setCasteMaster(CasteMaster casteMaster) {
		this.casteMaster = casteMaster;
	}

	public String getPromotionPost() {
		return promotionPost;
	}

	public Date getReportingDate() {
		return reportingDate;
	}

	public String getTestName() {
		return testName;
	}

	public Date getPassDate() {
		return passDate;
	}
	

	@Column(name = "sub_caste")
    private Long subCaste;
	
	@ManyToOne
    @JoinColumn(name = "sub_caste", referencedColumnName = "subcaste_id", insertable = false, updatable = false)
    private SubCasteMaster subCasteMaster;


    @Column(name = "initial_appointment_post")
    private Long initialAppointmentPost;
    
    public void setInitialAppointmentPost(Long initialAppointmentPost) {
		this.initialAppointmentPost = initialAppointmentPost;
	}

	public DesignationMaster getDesignationMaster2() {
		return designationMaster2;
	}

	public void setDesignationMaster2(DesignationMaster designationMaster2) {
		this.designationMaster2 = designationMaster2;
	}


	@ManyToOne
    @JoinColumn(name = "initial_appointment_post", referencedColumnName = "desig_code", insertable = false, updatable = false)
    private DesignationMaster designationMaster2;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_initial_appointment")
    private Date dateOfInitialAppointment;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reporting_present_station")
    private Date dateOfReportingPresentStation;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EmployeePromotionDetails> employeePromotionDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DeptTest> deptTests;

    private LocalDateTime entryDate;
    private String entryBy;

    /** Derived fields from related tables **/
    @Transient
    private String promotionPost;

    @Transient
    private Date reportingDate;

    @Transient
    private String placeOfReporting;

    @Transient
    private String testName;

    @Transient
    private Date passDate;

    // Constructors, Getters, and Setters

   

    @Override
	public String toString() {
		return "Employee [employeeCode=" + employeeCode + ", ddoCode=" + ddoCode + ", postSanctioningGoNo="
				+ postSanctioningGoNo + ", goCertificatePath=" + goCertificatePath + ", employeeName=" + employeeName
				+ ", designation=" + designation + ", contactNumber=" + contactNumber + ", tradeName=" + tradeName
				+ ", itiTradeMasterEntity=" + itiTradeMasterEntity + ", dob=" + dob + ", dor=" + dor
				+ ", academicQualification=" + academicQualification + ", technicalQualification="
				+ technicalQualification + ", goTechCertificatePath=" + goTechCertificatePath + ", reservationCategory="
				+ reservationCategory + ", subCaste=" + subCaste + ", initialAppointmentPost=" + initialAppointmentPost
				+ ", dateOfInitialAppointment=" + dateOfInitialAppointment + ", dateOfReportingPresentStation="
				+ dateOfReportingPresentStation + ", employeePromotionDetails=" + employeePromotionDetails
				+ ", deptTests=" + deptTests + ", entryDate=" + entryDate + ", entryBy=" + entryBy + ", promotionPost="
				+ promotionPost + ", reportingDate=" + reportingDate + ", placeOfReporting=" + placeOfReporting
				+ ", testName=" + testName + ", passDate=" + passDate + "]";
	}

	/**
	 * @param employeeCode
	 * @param ddoCode
	 * @param postSanctioningGoNo
	 * @param goCertificatePath
	 * @param employeeName
	 * @param designation
	 * @param contactNumber
	 * @param tradeName
	 * @param itiTradeMasterEntity
	 * @param dob
	 * @param dor
	 * @param academicQualification
	 * @param technicalQualification
	 * @param goTechCertificatePath
	 * @param reservationCategory
	 * @param subCaste
	 * @param initialAppointmentPost
	 * @param dateOfInitialAppointment
	 * @param dateOfReportingPresentStation
	 * @param employeePromotionDetails
	 * @param deptTests
	 * @param entryDate
	 * @param entryBy
	 * @param promotionPost
	 * @param reportingDate
	 * @param placeOfReporting
	 * @param testName
	 * @param passDate
	 */
	public Employee(String employeeCode, String ddoCode, String postSanctioningGoNo, String goCertificatePath,
			String employeeName, Long designation, String contactNumber, String tradeName,
			ItiTradeMasterEntity itiTradeMasterEntity, Date dob, Date dor, Long academicQualification,
			Long technicalQualification, String goTechCertificatePath, Long reservationCategory, Long subCaste,
			Long initialAppointmentPost, Date dateOfInitialAppointment, Date dateOfReportingPresentStation,
			List<EmployeePromotionDetails> employeePromotionDetails, List<DeptTest> deptTests, LocalDateTime entryDate,
			String entryBy, String promotionPost, Date reportingDate, String placeOfReporting, String testName,
			Date passDate) {
		super();
		this.employeeCode = employeeCode;
		this.ddoCode = ddoCode;
		this.postSanctioningGoNo = postSanctioningGoNo;
		this.goCertificatePath = goCertificatePath;
		this.employeeName = employeeName;
		this.designation = designation;
		this.contactNumber = contactNumber;
		this.tradeName = tradeName;
		this.itiTradeMasterEntity = itiTradeMasterEntity;
		this.dob = dob;
		this.dor = dor;
		this.academicQualification = academicQualification;
		this.technicalQualification = technicalQualification;
		this.goTechCertificatePath = goTechCertificatePath;
		this.reservationCategory = reservationCategory;
		this.subCaste = subCaste;
		this.initialAppointmentPost = initialAppointmentPost;
		this.dateOfInitialAppointment = dateOfInitialAppointment;
		this.dateOfReportingPresentStation = dateOfReportingPresentStation;
		this.employeePromotionDetails = employeePromotionDetails;
		this.deptTests = deptTests;
		this.entryDate = entryDate;
		this.entryBy = entryBy;
		this.promotionPost = promotionPost;
		this.reportingDate = reportingDate;
		this.placeOfReporting = placeOfReporting;
		this.testName = testName;
		this.passDate = passDate;
	}

	public SubCasteMaster getSubCasteMaster() {
		return subCasteMaster;
	}

	public void setSubCasteMaster(SubCasteMaster subCasteMaster) {
		this.subCasteMaster = subCasteMaster;
	}

	public void setSubCaste(Long subCaste) {
		this.subCaste = subCaste;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public String getPostSanctioningGoNo() {
		return postSanctioningGoNo;
	}

	public void setPostSanctioningGoNo(String postSanctioningGoNo) {
		this.postSanctioningGoNo = postSanctioningGoNo;
	}

	public String getGoCertificatePath() {
		return goCertificatePath;
	}

	public void setGoCertificatePath(String goCertificatePath) {
		this.goCertificatePath = goCertificatePath;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getDesignation() {
		return designation;
	}

	public void setDesignation(Long designation) {
		this.designation = designation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public ItiTradeMasterEntity getItiTradeMasterEntity() {
		return itiTradeMasterEntity;
	}

	public void setItiTradeMasterEntity(ItiTradeMasterEntity itiTradeMasterEntity) {
		this.itiTradeMasterEntity = itiTradeMasterEntity;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDor() {
		return dor;
	}

	public void setDor(Date dor) {
		this.dor = dor;
	}

	

	public String getGoTechCertificatePath() {
		return goTechCertificatePath;
	}

	public void setGoTechCertificatePath(String goTechCertificatePath) {
		this.goTechCertificatePath = goTechCertificatePath;
	}

	public Long getReservationCategory() {
		return reservationCategory;
	}

	public void setReservationCategory(Long reservationCategory) {
		this.reservationCategory = reservationCategory;
	}

	

	public Long getSubCaste() {
		return subCaste;
	}



	public Long getInitialAppointmentPost() {
		return initialAppointmentPost;
	}

	public Date getDateOfInitialAppointment() {
		return dateOfInitialAppointment;
	}

	public void setDateOfInitialAppointment(Date dateOfInitialAppointment) {
		this.dateOfInitialAppointment = dateOfInitialAppointment;
	}

	public Date getDateOfReportingPresentStation() {
		return dateOfReportingPresentStation;
	}

	public void setDateOfReportingPresentStation(Date dateOfReportingPresentStation) {
		this.dateOfReportingPresentStation = dateOfReportingPresentStation;
	}

	public List<EmployeePromotionDetails> getEmployeePromotionDetails() {
		return employeePromotionDetails;
	}

	public void setEmployeePromotionDetails(List<EmployeePromotionDetails> employeePromotionDetails) {
		this.employeePromotionDetails = employeePromotionDetails;
	}

	public List<DeptTest> getDeptTests() {
		return deptTests;
	}

	public Long getAcademicQualification() {
		return academicQualification;
	}

	public Long getTechnicalQualification() {
		return technicalQualification;
	}

	public void setDeptTests(List<DeptTest> deptTests) {
		this.deptTests = deptTests;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public void setPromotionPost(String promotionPost) {
		this.promotionPost = promotionPost;
	}

	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	public void setPlaceOfReporting(String placeOfReporting) {
		this.placeOfReporting = placeOfReporting;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}

	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPlaceOfReporting() {
        if (employeePromotionDetails != null && !employeePromotionDetails.isEmpty()) {
            return employeePromotionDetails.get(employeePromotionDetails.size() - 1).getPlaceOfReporting();
        }
        return null;
    }
    public String getCertificatePath() {
    	if (employeePromotionDetails != null && !employeePromotionDetails.isEmpty()) {
    		return employeePromotionDetails.get(employeePromotionDetails.size() - 1).getCertificatePath();
    	}
    	return null;
    }

  

	public String getItiCode() {
		return itiCode;
	}

	public void setItiCode(String itiCode) {
		this.itiCode = itiCode;
	}
}
