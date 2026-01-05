package com.iti.PlacementsBackend.entity.hrm;

import java.sql.Date;
import java.time.LocalDateTime;

import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="employee_service",schema="hrms")
public class EmployeeTransfer {
	
	 @Column(name = "employee_name", nullable = false)
	private String employeeName;
	
	@Id
    @Column(name = "employee_code", unique = true, nullable = false)
    private String employeeCode;
	
	@Column(name = "designation")
    private Long designation;
    
    @ManyToOne
    @JoinColumn(name = "designation", referencedColumnName = "desig_code", insertable = false, updatable = false)
    private DesignationMaster designationMaster;
	 
	 @Column(name = "trade_name")
    private String tradeName;
	  @ManyToOne
	    @JoinColumn(name = "trade_name", referencedColumnName = "tradeShort", insertable = false, updatable = false)
	    private ItiTradeMasterEntity itiTradeMasterEntity;
	  @Column(name = "present_working_station") 
    private String presentWorkingStation;
	  @Column(name = "working_since_all_cadres")  
    private Date workingSinceAllCadres;
    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;
    @Temporal(TemporalType.DATE)
    @Column(name = "dor")
    private Date dor;
    @Column(name="service_years")
    private String serviceYears;
    @Column(name="service_months")
    private String serviceMonths;
    @Column(name="service_days")
    private String serviceDays;
    @Column(name="benchmark_disability")
    private String benchmarkDisability;
    @Column(name="pwd_percentage")
    private String pwdPercentage;
    @Column(name="pbd_category")
    private String pbdCategory;
    @Column(name="spouse_working_place")
    private String spouseWorkingPlace;
    @Column(name="challenged_children")
    private String challengedChildren;
    @Column(name="widow_case")
    private String widowCase;
    @Column(name="medical_grounds")
    private String medicalGrounds;
    @Column(name="worked_tribal_area")
    private String workedTribalArea;
    @Column(name="area_name")
    private String areaName;
    @Column(name="office_bearer_terms")
    private Integer officeBearerTerms;
    @Column(name="office_bearer_years")
    private Integer officeBearerYears;
    @Column(name="pass_percentage")
    private String passPercentage;
    @Column(name="placement_percentage")
    private String placementPercentage;
    @Column(name="remarks")
    private String remarks;
    
    private String pbdCertificate;
    private String spouseCertificate;
    private String challengedChildrenCert;
    private String widowCertificate;
    private String medicalCertificate;
    private String officeBearerCert;
    
    @Column(name = "iti_code")
    private String itiCode;
	@ManyToOne
	@JoinColumn(name = "iti_code",referencedColumnName = "itiCode", insertable = false, updatable = false)
	private ItiEntity itiEntity;
	
	private LocalDateTime entryDate;
    private String entryBy;
	public EmployeeTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeTransfer(String employeeName, String employeeCode, Long designation,
			DesignationMaster designationMaster, String tradeName, ItiTradeMasterEntity itiTradeMasterEntity,
			String presentWorkingStation, Date workingSinceAllCadres, Date dob, Date dor, String serviceYears,
			String serviceMonths, String serviceDays, String benchmarkDisability, String pwdPercentage,
			String pbdCategory, String spouseWorkingPlace, String challengedChildren, String widowCase,
			String medicalGrounds, String workedTribalArea, String areaName, Integer officeBearerTerms,
			Integer officeBearerYears, String passPercentage, String placementPercentage, String remarks,
			String pbdCertificate, String spouseCertificate, String challengedChildrenCert, String widowCertificate,
			String medicalCertificate, String officeBearerCert, String itiCode, ItiEntity itiEntity,
			LocalDateTime entryDate, String entryBy) {
		super();
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.designation = designation;
		this.designationMaster = designationMaster;
		this.tradeName = tradeName;
		this.itiTradeMasterEntity = itiTradeMasterEntity;
		this.presentWorkingStation = presentWorkingStation;
		this.workingSinceAllCadres = workingSinceAllCadres;
		this.dob = dob;
		this.dor = dor;
		this.serviceYears = serviceYears;
		this.serviceMonths = serviceMonths;
		this.serviceDays = serviceDays;
		this.benchmarkDisability = benchmarkDisability;
		this.pwdPercentage = pwdPercentage;
		this.pbdCategory = pbdCategory;
		this.spouseWorkingPlace = spouseWorkingPlace;
		this.challengedChildren = challengedChildren;
		this.widowCase = widowCase;
		this.medicalGrounds = medicalGrounds;
		this.workedTribalArea = workedTribalArea;
		this.areaName = areaName;
		this.officeBearerTerms = officeBearerTerms;
		this.officeBearerYears = officeBearerYears;
		this.passPercentage = passPercentage;
		this.placementPercentage = placementPercentage;
		this.remarks = remarks;
		this.pbdCertificate = pbdCertificate;
		this.spouseCertificate = spouseCertificate;
		this.challengedChildrenCert = challengedChildrenCert;
		this.widowCertificate = widowCertificate;
		this.medicalCertificate = medicalCertificate;
		this.officeBearerCert = officeBearerCert;
		this.itiCode = itiCode;
		this.itiEntity = itiEntity;
		this.entryDate = entryDate;
		this.entryBy = entryBy;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public Long getDesignation() {
		return designation;
	}
	public void setDesignation(Long designation) {
		this.designation = designation;
	}
	public DesignationMaster getDesignationMaster() {
		return designationMaster;
	}
	public void setDesignationMaster(DesignationMaster designationMaster) {
		this.designationMaster = designationMaster;
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
	public String getPresentWorkingStation() {
		return presentWorkingStation;
	}
	public void setPresentWorkingStation(String presentWorkingStation) {
		this.presentWorkingStation = presentWorkingStation;
	}
	public Date getWorkingSinceAllCadres() {
		return workingSinceAllCadres;
	}
	public void setWorkingSinceAllCadres(Date workingSinceAllCadres) {
		this.workingSinceAllCadres = workingSinceAllCadres;
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
	public String getServiceYears() {
		return serviceYears;
	}
	public void setServiceYears(String serviceYears) {
		this.serviceYears = serviceYears;
	}
	public String getServiceMonths() {
		return serviceMonths;
	}
	public void setServiceMonths(String serviceMonths) {
		this.serviceMonths = serviceMonths;
	}
	public String getServiceDays() {
		return serviceDays;
	}
	public void setServiceDays(String serviceDays) {
		this.serviceDays = serviceDays;
	}
	public String getBenchmarkDisability() {
		return benchmarkDisability;
	}
	public void setBenchmarkDisability(String benchmarkDisability) {
		this.benchmarkDisability = benchmarkDisability;
	}
	public String getPwdPercentage() {
		return pwdPercentage;
	}
	public void setPwdPercentage(String pwdPercentage) {
		this.pwdPercentage = pwdPercentage;
	}
	public String getPbdCategory() {
		return pbdCategory;
	}
	public void setPbdCategory(String pbdCategory) {
		this.pbdCategory = pbdCategory;
	}
	public String getSpouseWorkingPlace() {
		return spouseWorkingPlace;
	}
	public void setSpouseWorkingPlace(String spouseWorkingPlace) {
		this.spouseWorkingPlace = spouseWorkingPlace;
	}
	public String getChallengedChildren() {
		return challengedChildren;
	}
	public void setChallengedChildren(String challengedChildren) {
		this.challengedChildren = challengedChildren;
	}
	public String getWidowCase() {
		return widowCase;
	}
	public void setWidowCase(String widowCase) {
		this.widowCase = widowCase;
	}
	public String getMedicalGrounds() {
		return medicalGrounds;
	}
	public void setMedicalGrounds(String medicalGrounds) {
		this.medicalGrounds = medicalGrounds;
	}
	public String getWorkedTribalArea() {
		return workedTribalArea;
	}
	public void setWorkedTribalArea(String workedTribalArea) {
		this.workedTribalArea = workedTribalArea;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getOfficeBearerTerms() {
		return officeBearerTerms;
	}
	public void setOfficeBearerTerms(Integer officeBearerTerms) {
		this.officeBearerTerms = officeBearerTerms;
	}
	public Integer getOfficeBearerYears() {
		return officeBearerYears;
	}
	public void setOfficeBearerYears(Integer officeBearerYears) {
		this.officeBearerYears = officeBearerYears;
	}
	public String getPassPercentage() {
		return passPercentage;
	}
	public void setPassPercentage(String passPercentage) {
		this.passPercentage = passPercentage;
	}
	public String getPlacementPercentage() {
		return placementPercentage;
	}
	public void setPlacementPercentage(String placementPercentage) {
		this.placementPercentage = placementPercentage;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPbdCertificate() {
		return pbdCertificate;
	}
	public void setPbdCertificate(String pbdCertificate) {
		this.pbdCertificate = pbdCertificate;
	}
	public String getSpouseCertificate() {
		return spouseCertificate;
	}
	public void setSpouseCertificate(String spouseCertificate) {
		this.spouseCertificate = spouseCertificate;
	}
	public String getChallengedChildrenCert() {
		return challengedChildrenCert;
	}
	public void setChallengedChildrenCert(String challengedChildrenCert) {
		this.challengedChildrenCert = challengedChildrenCert;
	}
	public String getWidowCertificate() {
		return widowCertificate;
	}
	public void setWidowCertificate(String widowCertificate) {
		this.widowCertificate = widowCertificate;
	}
	public String getMedicalCertificate() {
		return medicalCertificate;
	}
	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}
	public String getOfficeBearerCert() {
		return officeBearerCert;
	}
	public void setOfficeBearerCert(String officeBearerCert) {
		this.officeBearerCert = officeBearerCert;
	}
	public String getItiCode() {
		return itiCode;
	}
	public void setItiCode(String itiCode) {
		this.itiCode = itiCode;
	}
	public ItiEntity getItiEntity() {
		return itiEntity;
	}
	public void setItiEntity(ItiEntity itiEntity) {
		this.itiEntity = itiEntity;
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
	@Override
	public String toString() {
		return "EmployeeTransfer [employeeName=" + employeeName + ", employeeCode=" + employeeCode + ", designation="
				+ designation + ", designationMaster=" + designationMaster + ", tradeName=" + tradeName
				+ ", itiTradeMasterEntity=" + itiTradeMasterEntity + ", presentWorkingStation=" + presentWorkingStation
				+ ", workingSinceAllCadres=" + workingSinceAllCadres + ", dob=" + dob + ", dor=" + dor
				+ ", serviceYears=" + serviceYears + ", serviceMonths=" + serviceMonths + ", serviceDays=" + serviceDays
				+ ", benchmarkDisability=" + benchmarkDisability + ", pwdPercentage=" + pwdPercentage + ", pbdCategory="
				+ pbdCategory + ", spouseWorkingPlace=" + spouseWorkingPlace + ", challengedChildren="
				+ challengedChildren + ", widowCase=" + widowCase + ", medicalGrounds=" + medicalGrounds
				+ ", workedTribalArea=" + workedTribalArea + ", areaName=" + areaName + ", officeBearerTerms="
				+ officeBearerTerms + ", officeBearerYears=" + officeBearerYears + ", passPercentage=" + passPercentage
				+ ", placementPercentage=" + placementPercentage + ", remarks=" + remarks + ", pbdCertificate="
				+ pbdCertificate + ", spouseCertificate=" + spouseCertificate + ", challengedChildrenCert="
				+ challengedChildrenCert + ", widowCertificate=" + widowCertificate + ", medicalCertificate="
				+ medicalCertificate + ", officeBearerCert=" + officeBearerCert + ", itiCode=" + itiCode
				+ ", itiEntity=" + itiEntity + ", entryDate=" + entryDate + ", entryBy=" + entryBy + "]";
	}
	
}
