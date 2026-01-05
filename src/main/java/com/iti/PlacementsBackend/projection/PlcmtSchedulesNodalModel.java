package com.iti.PlacementsBackend.projection;

public interface PlcmtSchedulesNodalModel {
	Long getPlcmt_id();
	  String getDist_name();
	  String getIti_name();
	  String getSchedule_date();
	  Integer getNo_of_vacancies();
	  Integer getNo_of_attended_candidates();
	  Integer getNo_of_selected_candidates();
}
