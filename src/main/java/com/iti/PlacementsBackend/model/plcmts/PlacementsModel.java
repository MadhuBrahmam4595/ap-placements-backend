package com.iti.PlacementsBackend.model.plcmts;

public class PlacementsModel {

	private String fromDate;
	private String toDate;
	private String ptype;
	public PlacementsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlacementsModel(String fromDate, String toDate, String ptype) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.ptype = ptype;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	@Override
	public String toString() {
		return "PlacementsModel [fromDate=" + fromDate + ", toDate=" + toDate + ", ptype=" + ptype + "]";
	}
	
}