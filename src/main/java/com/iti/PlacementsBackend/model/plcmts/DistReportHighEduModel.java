package com.iti.PlacementsBackend.model.plcmts;

public class DistReportHighEduModel {
	String adm_num;
	String name;
	String plcmt_year;
	
	String pcoursename;
	String pclgname;
	
	String statename;
	String distname;
	String paddress;
	public DistReportHighEduModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistReportHighEduModel(String adm_num, String name, String plcmt_year, String pcoursename, String pclgname,
			String statename, String distname, String paddress) {
		super();
		this.adm_num = adm_num;
		this.name = name;
		this.plcmt_year = plcmt_year;
		this.pcoursename = pcoursename;
		this.pclgname = pclgname;
		this.statename = statename;
		this.distname = distname;
		this.paddress = paddress;
	}
	public String getAdm_num() {
		return adm_num;
	}
	public void setAdm_num(String adm_num) {
		this.adm_num = adm_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlcmt_year() {
		return plcmt_year;
	}
	public void setPlcmt_year(String plcmt_year) {
		this.plcmt_year = plcmt_year;
	}
	public String getPcoursename() {
		return pcoursename;
	}
	public void setPcoursename(String pcoursename) {
		this.pcoursename = pcoursename;
	}
	public String getPclgname() {
		return pclgname;
	}
	public void setPclgname(String pclgname) {
		this.pclgname = pclgname;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getDistname() {
		return distname;
	}
	public void setDistname(String distname) {
		this.distname = distname;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	@Override
	public String toString() {
		return "DistReportHighEduModel [adm_num=" + adm_num + ", name=" + name + ", plcmt_year=" + plcmt_year
				+ ", pcoursename=" + pcoursename + ", pclgname=" + pclgname + ", statename=" + statename + ", distname="
				+ distname + ", paddress=" + paddress + "]";
	}
	
	
}

