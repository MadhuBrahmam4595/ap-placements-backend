package com.iti.PlacementsBackend.model.inplant;

public class IndustryPartnerDetailsModel {
	
	private String pid;
	private String itiCode;
	private String distCode;
	
	private String revisedLeadSector;
	private String revisedLeadIndustryPartner;
	private String proposedNewTrade;
	public IndustryPartnerDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IndustryPartnerDetailsModel(String pid, String itiCode, String distCode, String revisedLeadSector,
			String revisedLeadIndustryPartner, String proposedNewTrade) {
		super();
		this.pid = pid;
		this.itiCode = itiCode;
		this.distCode = distCode;
		this.revisedLeadSector = revisedLeadSector;
		this.revisedLeadIndustryPartner = revisedLeadIndustryPartner;
		this.proposedNewTrade = proposedNewTrade;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getItiCode() {
		return itiCode;
	}
	public void setItiCode(String itiCode) {
		this.itiCode = itiCode;
	}
	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
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
	@Override
	public String toString() {
		return "IndustryPartnerDetailsModel [pid=" + pid + ", itiCode=" + itiCode + ", distCode=" + distCode
				+ ", revisedLeadSector=" + revisedLeadSector + ", revisedLeadIndustryPartner="
				+ revisedLeadIndustryPartner + ", proposedNewTrade=" + proposedNewTrade + "]";
	}
	

}
