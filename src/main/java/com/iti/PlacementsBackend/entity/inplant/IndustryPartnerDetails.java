package com.iti.PlacementsBackend.entity.inplant;

import java.time.LocalDateTime;

import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.entity.master.OldDistMasterEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "industry_partner_details",schema = "implant")
public class IndustryPartnerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String itiCode;
	@ManyToOne
	@JoinColumn(name = "itiCode", insertable = false, updatable = false)
	private ItiEntity itiEntity;
	
	private String distCode;
	@ManyToOne
	@JoinColumn(name = "distCode", insertable = false, updatable = false)
	private OldDistMasterEntity oldDistMasterEntity;
	
	private String revisedLeadSector;
	private String revisedLeadIndustryPartner;
	private String proposedNewTrade;
	
	private LocalDateTime entryDate;
	private String entryBy;
	public IndustryPartnerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IndustryPartnerDetails(Long pid, String itiCode, ItiEntity itiEntity, String distCode,
			OldDistMasterEntity oldDistMasterEntity, String revisedLeadSector, String revisedLeadIndustryPartner,
			String proposedNewTrade, LocalDateTime entryDate, String entryBy) {
		super();
		this.pid = pid;
		this.itiCode = itiCode;
		this.itiEntity = itiEntity;
		this.distCode = distCode;
		this.oldDistMasterEntity = oldDistMasterEntity;
		this.revisedLeadSector = revisedLeadSector;
		this.revisedLeadIndustryPartner = revisedLeadIndustryPartner;
		this.proposedNewTrade = proposedNewTrade;
		this.entryDate = entryDate;
		this.entryBy = entryBy;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}
	public OldDistMasterEntity getOldDistMasterEntity() {
		return oldDistMasterEntity;
	}
	public void setOldDistMasterEntity(OldDistMasterEntity oldDistMasterEntity) {
		this.oldDistMasterEntity = oldDistMasterEntity;
	}
	public String getRevisedLeadSector() {
		return revisedLeadSector;
	}
	public void setRevisedLeadSector(String revisedLeadSector) {
		this.revisedLeadSector = revisedLeadSector;
	}
	public String getRevisedLeadIndustryPartner() {
		return revisedLeadIndustryPartner;
	}
	public void setRevisedLeadIndustryPartner(String revisedLeadIndustryPartner) {
		this.revisedLeadIndustryPartner = revisedLeadIndustryPartner;
	}
	public String getProposedNewTrade() {
		return proposedNewTrade;
	}
	public void setProposedNewTrade(String proposedNewTrade) {
		this.proposedNewTrade = proposedNewTrade;
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
		return "IndustryPartnerDetails [pid=" + pid + ", itiCode=" + itiCode + ", itiEntity=" + itiEntity
				+ ", distCode=" + distCode + ", oldDistMasterEntity=" + oldDistMasterEntity + ", revisedLeadSector="
				+ revisedLeadSector + ", revisedLeadIndustryPartner=" + revisedLeadIndustryPartner
				+ ", proposedNewTrade=" + proposedNewTrade + ", entryDate=" + entryDate + ", entryBy=" + entryBy + "]";
	}
	
}
