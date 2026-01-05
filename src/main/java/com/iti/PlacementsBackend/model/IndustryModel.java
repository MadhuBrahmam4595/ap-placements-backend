package com.iti.PlacementsBackend.model;

public class IndustryModel {
	
	private Long industryId;
	private String industryName;
	private String industryAddress;
	private String industryType;
	public IndustryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IndustryModel(Long industryId, String industryName, String industryAddress, String industryType) {
		super();
		this.industryId = industryId;
		this.industryName = industryName;
		this.industryAddress = industryAddress;
		this.industryType = industryType;
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
	public Long getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}
	@Override
	public String toString() {
		return "IndustryModel [industryId=" + industryId + ", industryName=" + industryName + ", industryAddress="
				+ industryAddress + ", industryType=" + industryType + "]";
	}
	 
	
}
