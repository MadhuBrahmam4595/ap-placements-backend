package com.iti.PlacementsBackend.model;

import com.iti.PlacementsBackend.projection.DashBoardAllSeatsProj;
import com.iti.PlacementsBackend.projection.DashBoardGovtOrPvtSeatsProj;

public class DashBoadModel {
	
	public Long above20percentcollegescountgovt;
	public Long below20percentcollegescountgovt;
	
	public Long above20percentcollegescountpvt;
	public Long below20percentcollegescountpvt;
	
	public DashBoardAllSeatsProj dashBoardAllSeats;
	
	public DashBoardGovtOrPvtSeatsProj dashBoardGovtSeats;
	public DashBoardGovtOrPvtSeatsProj dashBoardPvtSeats;
	
	 
	
	public DashBoadModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DashBoadModel(Long above20percentcollegescountgovt, Long below20percentcollegescountgovt,
			Long above20percentcollegescountpvt, Long below20percentcollegescountpvt,
			DashBoardAllSeatsProj dashBoardAllSeats, DashBoardGovtOrPvtSeatsProj dashBoardGovtSeats,
			DashBoardGovtOrPvtSeatsProj dashBoardPvtSeats) {
		super();
		this.above20percentcollegescountgovt = above20percentcollegescountgovt;
		this.below20percentcollegescountgovt = below20percentcollegescountgovt;
		this.above20percentcollegescountpvt = above20percentcollegescountpvt;
		this.below20percentcollegescountpvt = below20percentcollegescountpvt;
		this.dashBoardAllSeats = dashBoardAllSeats;
		this.dashBoardGovtSeats = dashBoardGovtSeats;
		this.dashBoardPvtSeats = dashBoardPvtSeats;
	}

	public Long getAbove20percentcollegescountgovt() {
		return above20percentcollegescountgovt;
	}

	public void setAbove20percentcollegescountgovt(Long above20percentcollegescountgovt) {
		this.above20percentcollegescountgovt = above20percentcollegescountgovt;
	}

	public Long getBelow20percentcollegescountgovt() {
		return below20percentcollegescountgovt;
	}

	public void setBelow20percentcollegescountgovt(Long below20percentcollegescountgovt) {
		this.below20percentcollegescountgovt = below20percentcollegescountgovt;
	}

	public Long getAbove20percentcollegescountpvt() {
		return above20percentcollegescountpvt;
	}

	public void setAbove20percentcollegescountpvt(Long above20percentcollegescountpvt) {
		this.above20percentcollegescountpvt = above20percentcollegescountpvt;
	}

	public Long getBelow20percentcollegescountpvt() {
		return below20percentcollegescountpvt;
	}

	public void setBelow20percentcollegescountpvt(Long below20percentcollegescountpvt) {
		this.below20percentcollegescountpvt = below20percentcollegescountpvt;
	}

	public DashBoardAllSeatsProj getDashBoardAllSeats() {
		return dashBoardAllSeats;
	}

	public void setDashBoardAllSeats(DashBoardAllSeatsProj dashBoardAllSeats) {
		this.dashBoardAllSeats = dashBoardAllSeats;
	}

	public DashBoardGovtOrPvtSeatsProj getDashBoardGovtSeats() {
		return dashBoardGovtSeats;
	}

	public void setDashBoardGovtSeats(DashBoardGovtOrPvtSeatsProj dashBoardGovtSeats) {
		this.dashBoardGovtSeats = dashBoardGovtSeats;
	}

	public DashBoardGovtOrPvtSeatsProj getDashBoardPvtSeats() {
		return dashBoardPvtSeats;
	}

	public void setDashBoardPvtSeats(DashBoardGovtOrPvtSeatsProj dashBoardPvtSeats) {
		this.dashBoardPvtSeats = dashBoardPvtSeats;
	}

	@Override
	public String toString() {
		return "DashBoadModel [above20percentcollegescountgovt=" + above20percentcollegescountgovt
				+ ", below20percentcollegescountgovt=" + below20percentcollegescountgovt
				+ ", above20percentcollegescountpvt=" + above20percentcollegescountpvt
				+ ", below20percentcollegescountpvt=" + below20percentcollegescountpvt + ", dashBoardAllSeats="
				+ dashBoardAllSeats + ", dashBoardGovtSeats=" + dashBoardGovtSeats + ", dashBoardPvtSeats="
				+ dashBoardPvtSeats + "]";
	}

}
