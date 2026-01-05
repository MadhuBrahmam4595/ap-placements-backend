package com.iti.PlacementsBackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.projection.DashBoardAllSeatsProj;
import com.iti.PlacementsBackend.projection.DashBoardGovtOrPvtSeatsProj;
import com.iti.PlacementsBackend.projection.ITIsSeatsStatisticsProj;
import com.iti.PlacementsBackend.repo.master.OldDistMasterRepo;
import com.iti.PlacementsBackend.service.DashBoardServices;



@Service
public class DashBoardServicesImpl implements DashBoardServices{
	
	@Autowired
	private OldDistMasterRepo repo;

	@Override
	public Long getAbove20percentcollegescountgovt() {
		// TODO Auto-generated method stub
		return repo.getAbove20percentcollegescountgovt();
	}
	
	@Override
	public Long getBelow20percentcollegescountgovt() {
		// TODO Auto-generated method stub
		return repo.getBelow20percentcollegescountgovt();
	}

	@Override
	public Long getAbove20percentcollegescountpvt() {
		// TODO Auto-generated method stub
		return repo.getAbove20percentcollegescountpvt();
	}

	@Override
	public Long getBelow20percentcollegescountpvt() {
		// TODO Auto-generated method stub
		return repo.getBelow20percentcollegescountpvt();
	}
	
	@Override
	public DashBoardAllSeatsProj getDashBoardAllSeats() {
		// TODO Auto-generated method stub
		return repo.getDashBoardAllSeats();
	}
	@Override
	public DashBoardGovtOrPvtSeatsProj getDashBoardGovtOrPvtSeats(String govt) {
		// TODO Auto-generated method stub
		return repo.getDashBoardGovtOrPvtSeats(govt);
	}

	@Override
	public List<ITIsSeatsStatisticsProj> getAbove20PercentItis() {
		// TODO Auto-generated method stub
		return repo.getAbove20PercentItis();
	}

	@Override
	public List<ITIsSeatsStatisticsProj> getBelow20PercentItis() {
		// TODO Auto-generated method stub
		return repo.getBelow20PercentItis();
	}
	@Override
	public ITIsSeatsStatisticsProj getAbove20PercentSeatsStats() {
		// TODO Auto-generated method stub
		return repo.getAbove20PercentSeatsStats();
	}
	@Override
	public ITIsSeatsStatisticsProj getBelow20PercentSeatsStats() {
		// TODO Auto-generated method stub
		return repo.getBelow20PercentSeatsStats();
	}

}
