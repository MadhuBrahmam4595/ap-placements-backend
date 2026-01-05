package com.iti.PlacementsBackend.model.plcmts;

public class StateSkillDevelopmentPlanModel {

	private Integer tradeCode;
	private String tradeName;
	private Long itiCount;
	private Long totalStrength;

	private Long totalMale;
	private Long totalFemale;
	private Long totalGender;
	private Long totalPlcmts;
	
	public StateSkillDevelopmentPlanModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StateSkillDevelopmentPlanModel(Integer tradeCode, String tradeName, Long itiCount, Long totalStrength,
			Long totalMale, Long totalFemale, Long totalGender, Long totalPlcmts) {
		super();
		this.tradeCode = tradeCode;
		this.tradeName = tradeName;
		this.itiCount = itiCount;
		this.totalStrength = totalStrength;
		this.totalMale = totalMale;
		this.totalFemale = totalFemale;
		this.totalGender = totalGender;
		this.totalPlcmts = totalPlcmts;
	}
	public Integer getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(Integer tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public Long getItiCount() {
		return itiCount;
	}
	public void setItiCount(Long itiCount) {
		this.itiCount = itiCount;
	}
	public Long getTotalStrength() {
		return totalStrength;
	}
	public void setTotalStrength(Long totalStrength) {
		this.totalStrength = totalStrength;
	}
	public Long getTotalMale() {
		return totalMale;
	}
	public void setTotalMale(Long totalMale) {
		this.totalMale = totalMale;
	}
	public Long getTotalFemale() {
		return totalFemale;
	}
	public void setTotalFemale(Long totalFemale) {
		this.totalFemale = totalFemale;
	}
	public Long getTotalGender() {
		return totalGender;
	}
	public void setTotalGender(Long totalGender) {
		this.totalGender = totalGender;
	}
	public Long getTotalPlcmts() {
		return totalPlcmts;
	}
	public void setTotalPlcmts(Long totalPlcmts) {
		this.totalPlcmts = totalPlcmts;
	}
	@Override
	public String toString() {
		return "StateSkillDevelopmentPlanModel [tradeCode=" + tradeCode + ", tradeName=" + tradeName + ", itiCount="
				+ itiCount + ", totalStrength=" + totalStrength + ", totalMale=" + totalMale + ", totalFemale="
				+ totalFemale + ", totalGender=" + totalGender + ", totalPlcmts=" + totalPlcmts + "]";
	}
	
}
