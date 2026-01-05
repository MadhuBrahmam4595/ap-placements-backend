package com.iti.PlacementsBackend.projection;

public interface ITIsSeatsStatisticsProj {
	
	String getDist_code();
	  String getDist_name();
	  String getIti_code();
	  String getIti_name();
	  
	  Integer getStrength();
	  Integer getStrength_fill();
	  Integer getStrength_vacant();
	  Integer getFill_ratio();

}
