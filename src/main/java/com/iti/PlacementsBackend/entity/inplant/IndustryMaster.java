package com.iti.PlacementsBackend.entity.inplant;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "industry_master", schema = "implant")
public class IndustryMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long industryId;
	private String industryName;
	private String industryAddress;
	private String industryType;
	
	private LocalDateTime entryTime;
	private String entryBy;
	
	private LocalDateTime editTime;
	private String editBy;
	
	public IndustryMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndustryMaster(Long industryId, String industryName, String industryAddress, String industryType,
			LocalDateTime entryTime, String entryBy, LocalDateTime editTime, String editBy) {
		super();
		this.industryId = industryId;
		this.industryName = industryName;
		this.industryAddress = industryAddress;
		this.industryType = industryType;
		this.entryTime = entryTime;
		this.entryBy = entryBy;
		this.editTime = editTime;
		this.editBy = editBy;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryAddress() {
		return industryAddress;
	}

	public void setIndustryAddress(String industryAddress) {
		this.industryAddress = industryAddress;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public LocalDateTime getEditTime() {
		return editTime;
	}

	public void setEditTime(LocalDateTime editTime) {
		this.editTime = editTime;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	@Override
	public String toString() {
		return "IndustryMaster [industryId=" + industryId + ", industryName=" + industryName + ", industryAddress="
				+ industryAddress + ", industryType=" + industryType + ", entryTime=" + entryTime + ", entryBy="
				+ entryBy + ", editTime=" + editTime + ", editBy=" + editBy + "]";
	}
	

}

