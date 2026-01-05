package com.iti.PlacementsBackend.projection.plcmts;

public interface StateSkillDevelopmentPlanProj {
	
	//tradewise strength in all itis params
	Integer getTrade_code();
	String getTrade_name();
	Long getIti_count();
	Long getTotal_strength();
	
	//gender wise data and placed student params
	Long getTotalmale();
	Long getTotalfemale();
	Long getTotalgender();
	Long getTotalplcmts();
	
}
