package com.iti.PlacementsBackend.model.plcmts;


public class DistReportJobAndOJModel {
	
	String adm_num;
	String name;
	String plcmt_year;
	String pname_of_company;
	String ppostname;
	String psalary;
	String phrno;
	String statename;
	String distname;
	String paddress;
	String schedule;
	public DistReportJobAndOJModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistReportJobAndOJModel(String adm_num, String name, String plcmt_year, String pname_of_company,
			String ppostname, String psalary, String phrno, String statename, String distname, String paddress,
			String schedule) {
		super();
		this.adm_num = adm_num;
		this.name = name;
		this.plcmt_year = plcmt_year;
		this.pname_of_company = pname_of_company;
		this.ppostname = ppostname;
		this.psalary = psalary;
		this.phrno = phrno;
		this.statename = statename;
		this.distname = distname;
		this.paddress = paddress;
		this.schedule = schedule;
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
	public String getPname_of_company() {
		return pname_of_company;
	}
	public void setPname_of_company(String pname_of_company) {
		this.pname_of_company = pname_of_company;
	}
	public String getPpostname() {
		return ppostname;
	}
	public void setPpostname(String ppostname) {
		this.ppostname = ppostname;
	}
	public String getPsalary() {
		return psalary;
	}
	public void setPsalary(String psalary) {
		this.psalary = psalary;
	}
	public String getPhrno() {
		return phrno;
	}
	public void setPhrno(String phrno) {
		this.phrno = phrno;
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
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	@Override
	public String toString() {
		return "DistReportJobAndOJModel [adm_num=" + adm_num + ", name=" + name + ", plcmt_year=" + plcmt_year
				+ ", pname_of_company=" + pname_of_company + ", ppostname=" + ppostname + ", psalary=" + psalary
				+ ", phrno=" + phrno + ", statename=" + statename + ", distname=" + distname + ", paddress=" + paddress
				+ ", schedule=" + schedule + "]";
	}
	
	

}

