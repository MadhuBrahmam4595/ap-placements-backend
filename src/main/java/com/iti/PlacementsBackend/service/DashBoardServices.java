package com.iti.PlacementsBackend.service;

import java.util.List;

import com.iti.PlacementsBackend.projection.DashBoardAllSeatsProj;
import com.iti.PlacementsBackend.projection.DashBoardGovtOrPvtSeatsProj;
import com.iti.PlacementsBackend.projection.ITIsSeatsStatisticsProj;



public interface DashBoardServices {
	
	public Long getAbove20percentcollegescountgovt();
	public Long getBelow20percentcollegescountgovt();
	
	public Long getAbove20percentcollegescountpvt();
	public Long getBelow20percentcollegescountpvt();
	
	public DashBoardAllSeatsProj getDashBoardAllSeats();
	public DashBoardGovtOrPvtSeatsProj getDashBoardGovtOrPvtSeats(String govt);
	
	public List<ITIsSeatsStatisticsProj> getAbove20PercentItis();
	public List<ITIsSeatsStatisticsProj> getBelow20PercentItis();
	
	public ITIsSeatsStatisticsProj getAbove20PercentSeatsStats();
	public ITIsSeatsStatisticsProj getBelow20PercentSeatsStats();

}
