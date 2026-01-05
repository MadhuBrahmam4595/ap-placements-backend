package com.iti.PlacementsBackend.entity.inplant;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.iti.PlacementsBackend.entity.master.DistsStatewise;
import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "implant", schema = "implant")
public class ImplantEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long implantId;
	private String facultyName;
	private String location;

	private String tradeShort;
	@ManyToOne
	@JoinColumn(name = "tradeShort", insertable = false, updatable = false)
	private ItiTradeMasterEntity itiTradeMaster;

	private Long slno;
	@ManyToOne
	@JoinColumn(name = "slno", insertable = false, updatable = false)
	private IndustriesEntity industriesEntity;

	private String industryAddress;
	private Long hrNo;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Integer noOfStudents;

	private Integer distcode;
	@ManyToOne
	@JoinColumn(name = "distcode", insertable = false, updatable = false)
	private DistsStatewise distsStatewise;

	private String description;
	private Integer noOfDays;

	private String itiCode;
	@ManyToOne
	@JoinColumn(name = "itiCode", insertable = false, updatable = false)
	private ItiEntity iti;

	private LocalDateTime entryDate;
	private String entryBy;

	public ImplantEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImplantEntity(Long implantId, String facultyName, String location, String tradeShort,
			ItiTradeMasterEntity itiTradeMaster, Long slno, IndustriesEntity industriesEntity, String industryAddress,
			Long hrNo, LocalDate fromDate, LocalDate toDate, Integer noOfStudents, Integer distcode,
			DistsStatewise distsStatewise, String description, Integer noOfDays, String itiCode, ItiEntity iti,
			LocalDateTime entryDate, String entryBy) {
		super();
		this.implantId = implantId;
		this.facultyName = facultyName;
		this.location = location;
		this.tradeShort = tradeShort;
		this.itiTradeMaster = itiTradeMaster;
		this.slno = slno;
		this.industriesEntity = industriesEntity;
		this.industryAddress = industryAddress;
		this.hrNo = hrNo;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.noOfStudents = noOfStudents;
		this.distcode = distcode;
		this.distsStatewise = distsStatewise;
		this.description = description;
		this.noOfDays = noOfDays;
		this.itiCode = itiCode;
		this.iti = iti;
		this.entryDate = entryDate;
		this.entryBy = entryBy;
	}

	public Long getImplantId() {
		return implantId;
	}

	public void setImplantId(Long implantId) {
		this.implantId = implantId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTradeShort() {
		return tradeShort;
	}

	public void setTradeShort(String tradeShort) {
		this.tradeShort = tradeShort;
	}

	public ItiTradeMasterEntity getItiTradeMaster() {
		return itiTradeMaster;
	}

	public void setItiTradeMaster(ItiTradeMasterEntity itiTradeMaster) {
		this.itiTradeMaster = itiTradeMaster;
	}

	public Long getSlno() {
		return slno;
	}

	public void setSlno(Long slno) {
		this.slno = slno;
	}

	public IndustriesEntity getIndustriesEntity() {
		return industriesEntity;
	}

	public void setIndustriesEntity(IndustriesEntity industriesEntity) {
		this.industriesEntity = industriesEntity;
	}

	public String getIndustryAddress() {
		return industryAddress;
	}

	public void setIndustryAddress(String industryAddress) {
		this.industryAddress = industryAddress;
	}

	public Long getHrNo() {
		return hrNo;
	}

	public void setHrNo(Long hrNo) {
		this.hrNo = hrNo;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Integer getNoOfStudents() {
		return noOfStudents;
	}

	public void setNoOfStudents(Integer noOfStudents) {
		this.noOfStudents = noOfStudents;
	}

	public Integer getDistcode() {
		return distcode;
	}

	public void setDistcode(Integer distcode) {
		this.distcode = distcode;
	}

	public DistsStatewise getDistsStatewise() {
		return distsStatewise;
	}

	public void setDistsStatewise(DistsStatewise distsStatewise) {
		this.distsStatewise = distsStatewise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getItiCode() {
		return itiCode;
	}

	public void setItiCode(String itiCode) {
		this.itiCode = itiCode;
	}

	public ItiEntity getIti() {
		return iti;
	}

	public void setIti(ItiEntity iti) {
		this.iti = iti;
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
		return "ImplantEntity [implantId=" + implantId + ", facultyName=" + facultyName + ", location=" + location
				+ ", tradeShort=" + tradeShort + ", itiTradeMaster=" + itiTradeMaster + ", slno=" + slno
				+ ", industriesEntity=" + industriesEntity + ", industryAddress=" + industryAddress + ", hrNo=" + hrNo
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", noOfStudents=" + noOfStudents + ", distcode="
				+ distcode + ", distsStatewise=" + distsStatewise + ", description=" + description + ", noOfDays="
				+ noOfDays + ", itiCode=" + itiCode + ", iti=" + iti + ", entryDate=" + entryDate + ", entryBy="
				+ entryBy + "]";
	}

}
