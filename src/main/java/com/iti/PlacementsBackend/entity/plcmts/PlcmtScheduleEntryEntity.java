package com.iti.PlacementsBackend.entity.plcmts;

import java.time.LocalDateTime;

import com.iti.PlacementsBackend.entity.master.ItiEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "placements_schedules", schema = "placements")
public class PlcmtScheduleEntryEntity {
	@Id
	private Long plcmtId;
	private Long scheduleId;
	private String scheduleType;
	private String scheduleDate;
	
	private String scheduleLocation;
	@ManyToOne
	@JoinColumn(name = "scheduleLocation", insertable = false, updatable = false)
	private ItiEntity itiEntity;
	
	private String scheduleDesc;
	private String distCode;

	private Integer noOfVacancies;
	private Integer noOfAttendedCandidates;
	private Integer noOfSelectedCandidates;
	
	private String entryBy;
	private LocalDateTime entryDateTime;

	@Transient
	private String distName;
	@Transient
	private String itiName;
	@Transient
	private String plcmtsCount;

	public PlcmtScheduleEntryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	 

	public PlcmtScheduleEntryEntity(Long plcmtId, Long scheduleId, String scheduleType, String scheduleDate,
			String scheduleLocation, ItiEntity itiEntity, String scheduleDesc, String distCode, Integer noOfVacancies,
			Integer noOfAttendedCandidates, Integer noOfSelectedCandidates, String entryBy, LocalDateTime entryDateTime,
			String distName, String itiName, String plcmtsCount) {
		super();
		this.plcmtId = plcmtId;
		this.scheduleId = scheduleId;
		this.scheduleType = scheduleType;
		this.scheduleDate = scheduleDate;
		this.scheduleLocation = scheduleLocation;
		this.itiEntity = itiEntity;
		this.scheduleDesc = scheduleDesc;
		this.distCode = distCode;
		this.noOfVacancies = noOfVacancies;
		this.noOfAttendedCandidates = noOfAttendedCandidates;
		this.noOfSelectedCandidates = noOfSelectedCandidates;
		this.entryBy = entryBy;
		this.entryDateTime = entryDateTime;
		this.distName = distName;
		this.itiName = itiName;
		this.plcmtsCount = plcmtsCount;
	}



	public ItiEntity getItiEntity() {
		return itiEntity;
	}



	public void setItiEntity(ItiEntity itiEntity) {
		this.itiEntity = itiEntity;
	}



	public Long getPlcmtId() {
		return plcmtId;
	}

	public void setPlcmtId(Long plcmtId) {
		this.plcmtId = plcmtId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleLocation() {
		return scheduleLocation;
	}

	public void setScheduleLocation(String scheduleLocation) {
		this.scheduleLocation = scheduleLocation;
	}

	public String getScheduleDesc() {
		return scheduleDesc;
	}

	public void setScheduleDesc(String scheduleDesc) {
		this.scheduleDesc = scheduleDesc;
	}

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public Integer getNoOfVacancies() {
		return noOfVacancies;
	}

	public void setNoOfVacancies(Integer noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}

	public Integer getNoOfAttendedCandidates() {
		return noOfAttendedCandidates;
	}

	public void setNoOfAttendedCandidates(Integer noOfAttendedCandidates) {
		this.noOfAttendedCandidates = noOfAttendedCandidates;
	}

	public Integer getNoOfSelectedCandidates() {
		return noOfSelectedCandidates;
	}

	public void setNoOfSelectedCandidates(Integer noOfSelectedCandidates) {
		this.noOfSelectedCandidates = noOfSelectedCandidates;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public LocalDateTime getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(LocalDateTime entryDateTime) {
		this.entryDateTime = entryDateTime;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getItiName() {
		return itiName;
	}

	public void setItiName(String itiName) {
		this.itiName = itiName;
	}

	public String getPlcmtsCount() {
		return plcmtsCount;
	}

	public void setPlcmtsCount(String plcmtsCount) {
		this.plcmtsCount = plcmtsCount;
	}



	@Override
	public String toString() {
		return "PlcmtScheduleEntryEntity [plcmtId=" + plcmtId + ", scheduleId=" + scheduleId + ", scheduleType="
				+ scheduleType + ", scheduleDate=" + scheduleDate + ", scheduleLocation=" + scheduleLocation
				+ ", itiEntity=" + itiEntity + ", scheduleDesc=" + scheduleDesc + ", distCode=" + distCode
				+ ", noOfVacancies=" + noOfVacancies + ", noOfAttendedCandidates=" + noOfAttendedCandidates
				+ ", noOfSelectedCandidates=" + noOfSelectedCandidates + ", entryBy=" + entryBy + ", entryDateTime="
				+ entryDateTime + ", distName=" + distName + ", itiName=" + itiName + ", plcmtsCount=" + plcmtsCount
				+ "]";
	}

	 

	

}
