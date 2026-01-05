package com.iti.PlacementsBackend.model.plcmts;

import java.util.List;

import com.iti.PlacementsBackend.entity.master.DistsStatewise;
import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.entity.master.States_mastEntity;
import com.iti.PlacementsBackend.model.AdmissionModel;

public class AjaxResponseBody {
	List<DistsStatewise> dists;
	List<States_mastEntity> states;
	List<ItiTradeMasterEntity> trades;
	String msg;
	AdmissionModel result;
	String jwtToken;

	public AjaxResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBody(List<DistsStatewise> dists, List<States_mastEntity> states,
			List<ItiTradeMasterEntity> trades, String msg, AdmissionModel result, String jwtToken) {
		super();
		this.dists = dists;
		this.states = states;
		this.trades = trades;
		this.msg = msg;
		this.result = result;
		this.jwtToken = jwtToken;
	}

	public List<DistsStatewise> getDists() {
		return dists;
	}

	public void setDists(List<DistsStatewise> dists) {
		this.dists = dists;
	}

	public List<States_mastEntity> getStates() {
		return states;
	}

	public void setStates(List<States_mastEntity> states) {
		this.states = states;
	}

	public List<ItiTradeMasterEntity> getTrades() {
		return trades;
	}

	public void setTrades(List<ItiTradeMasterEntity> trades) {
		this.trades = trades;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AdmissionModel getResult() {
		return result;
	}

	public void setResult(AdmissionModel result) {
		this.result = result;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "AjaxResponseBody [dists=" + dists + ", states=" + states + ", trades=" + trades + ", msg=" + msg
				+ ", result=" + result + ", jwtToken=" + jwtToken + "]";
	}

}
