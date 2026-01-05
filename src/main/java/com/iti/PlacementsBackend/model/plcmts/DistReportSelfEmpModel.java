package com.iti.PlacementsBackend.model.plcmts;

public class DistReportSelfEmpModel {
	
	String adm_num;
	String name;
	String plcmt_year;
	
	String pselfemp;
	String pmonthincome;
	
	String statename;
	String distname;
	String paddress;
	public DistReportSelfEmpModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistReportSelfEmpModel(String adm_num, String name, String plcmt_year, String pselfemp, String pmonthincome,
			String statename, String distname, String paddress) {
		super();
		this.adm_num = adm_num;
		this.name = name;
		this.plcmt_year = plcmt_year;
		this.pselfemp = pselfemp;
		this.pmonthincome = pmonthincome;
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
	public String getPselfemp() {
		return pselfemp;
	}
	public void setPselfemp(String pselfemp) {
		this.pselfemp = pselfemp;
	}
	public String getPmonthincome() {
		return pmonthincome;
	}
	public void setPmonthincome(String pmonthincome) {
		this.pmonthincome = pmonthincome;
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
		return "DistReportSelfEmpModel [adm_num=" + adm_num + ", name=" + name + ", plcmt_year=" + plcmt_year
				+ ", pselfemp=" + pselfemp + ", pmonthincome=" + pmonthincome + ", statename=" + statename
				+ ", distname=" + distname + ", paddress=" + paddress + "]";
	}
	
}

