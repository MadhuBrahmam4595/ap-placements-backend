package com.iti.PlacementsBackend.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DistReportProj {
	
	Long getImplant_id();
	String getDescription();
	
	Integer getImplant_distcode();
	String getImplant_distname();
	
	String getImplant_statecode();
	String getImplant_statename();
	
	LocalDateTime getEntry_date();
	String getFaculty_name();
	LocalDate getFrom_date();
	Long getHr_no();
	String getIndustry_address();
	
	String getIti_code();
	String getIti_name();
	
	String getLocation();
	Integer getNo_of_days();
	Integer getNo_of_students();
	
	Long getSlno();
	String getIndustry_name();
	
	LocalDate getTo_date();
	
	String getTrade_short();
	String getTrade_name();
	
	String getItidistcode();
	String getItidistname();


}
