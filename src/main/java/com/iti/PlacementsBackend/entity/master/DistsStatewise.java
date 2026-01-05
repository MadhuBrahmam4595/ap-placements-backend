package com.iti.PlacementsBackend.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DistsStatewise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer distcode;
	private String distname;
	private String statename;
	private String statecode;
	public DistsStatewise(Integer distcode, String distname, String statename, String statecode) {
		super();
		this.distcode = distcode;
		this.distname = distname;
		this.statename = statename;
		this.statecode = statecode;
	}
	public Integer getDistcode() {
		return distcode;
	}
	public void setDistcode(Integer distcode) {
		this.distcode = distcode;
	}
	public String getDistname() {
		return distname;
	}
	public void setDistname(String distname) {
		this.distname = distname;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public DistsStatewise() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DistsStatewise [distcode=" + distcode + ", distname=" + distname + ", statename=" + statename
				+ ", statecode=" + statecode + "]";
	}

}

