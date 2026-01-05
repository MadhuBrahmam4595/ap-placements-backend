package com.iti.PlacementsBackend.model.plcmts;


public class DistReportAppreaAndOAModel {
	
	String adm_num;
	String name;
	String plcmt_year;
	String pname_of_company;
	String trade_name;
	String pstipendamt;
	String phrno;
	String paaprstartdate;
	String paaprenddate;
	String statename;
	String distname;
	String paddress;
	String schedule;
	public DistReportAppreaAndOAModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistReportAppreaAndOAModel(String adm_num, String name, String plcmt_year, String pname_of_company,
			String trade_name, String pstipendamt, String phrno, String paaprstartdate, String paaprenddate,
			String statename, String distname, String paddress, String schedule) {
		super();
		this.adm_num = adm_num;
		this.name = name;
		this.plcmt_year = plcmt_year;
		this.pname_of_company = pname_of_company;
		this.trade_name = trade_name;
		this.pstipendamt = pstipendamt;
		this.phrno = phrno;
		this.paaprstartdate = paaprstartdate;
		this.paaprenddate = paaprenddate;
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
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getPstipendamt() {
		return pstipendamt;
	}
	public void setPstipendamt(String pstipendamt) {
		this.pstipendamt = pstipendamt;
	}
	public String getPhrno() {
		return phrno;
	}
	public void setPhrno(String phrno) {
		this.phrno = phrno;
	}
	public String getPaaprstartdate() {
		return paaprstartdate;
	}
	public void setPaaprstartdate(String paaprstartdate) {
		this.paaprstartdate = paaprstartdate;
	}
	public String getPaaprenddate() {
		return paaprenddate;
	}
	public void setPaaprenddate(String paaprenddate) {
		this.paaprenddate = paaprenddate;
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
		return "DistReportAppreaAndOAModel [adm_num=" + adm_num + ", name=" + name + ", plcmt_year=" + plcmt_year
				+ ", pname_of_company=" + pname_of_company + ", trade_name=" + trade_name + ", pstipendamt="
				+ pstipendamt + ", phrno=" + phrno + ", paaprstartdate=" + paaprstartdate + ", paaprenddate="
				+ paaprenddate + ", statename=" + statename + ", distname=" + distname + ", paddress=" + paddress
				+ ", schedule=" + schedule + "]";
	}
	
	

}

