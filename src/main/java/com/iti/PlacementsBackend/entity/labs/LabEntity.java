package com.iti.PlacementsBackend.entity.labs;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "labs", schema = "labs")
public class LabEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labId;

	private String industryName;

	private String tradeShort;
	@ManyToOne
	@JoinColumn(name = "tradeShort", insertable = false, updatable = false)
	private ItiTradeMasterEntity itiTradeMasterEntity;

	@OneToMany(mappedBy = "labEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	List<LabItemsEntity> labItemsEntity;

	private String itiCode;
	@ManyToOne
	@JoinColumn(name = "itiCode", insertable = false, updatable = false)
	private ItiEntity itiEntity;
	
	private LocalDateTime entryDate;
	private String entryBy;
	
	private String description;

	public LabEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LabEntity(Long labId, String industryName, String tradeShort, ItiTradeMasterEntity itiTradeMasterEntity,
			List<LabItemsEntity> labItemsEntity, String itiCode, ItiEntity itiEntity, LocalDateTime entryDate,
			String entryBy, String description) {
		super();
		this.labId = labId;
		this.industryName = industryName;
		this.tradeShort = tradeShort;
		this.itiTradeMasterEntity = itiTradeMasterEntity;
		this.labItemsEntity = labItemsEntity;
		this.itiCode = itiCode;
		this.itiEntity = itiEntity;
		this.entryDate = entryDate;
		this.entryBy = entryBy;
		this.description = description;
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getTradeShort() {
		return tradeShort;
	}

	public void setTradeShort(String tradeShort) {
		this.tradeShort = tradeShort;
	}

	public ItiTradeMasterEntity getItiTradeMasterEntity() {
		return itiTradeMasterEntity;
	}

	public void setItiTradeMasterEntity(ItiTradeMasterEntity itiTradeMasterEntity) {
		this.itiTradeMasterEntity = itiTradeMasterEntity;
	}

	public List<LabItemsEntity> getLabItemsEntity() {
		return labItemsEntity;
	}

	public void setLabItemsEntity(List<LabItemsEntity> labItemsEntity) {
		this.labItemsEntity = labItemsEntity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LabEntity [labId=" + labId + ", industryName=" + industryName + ", tradeShort=" + tradeShort
				+ ", itiTradeMasterEntity=" + itiTradeMasterEntity + ", labItemsEntity=" + labItemsEntity + ", itiCode="
				+ itiCode + ", itiEntity=" + itiEntity + ", entryDate=" + entryDate + ", entryBy=" + entryBy
				+ ", description=" + description + "]";
	}

}
