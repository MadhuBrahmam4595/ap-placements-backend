package com.iti.PlacementsBackend.model.inplant;

public class DistinctCountModel {
	
	private Long noOfDistinctItis;
	private Long noOfDistinctIndustries;
	private Long noOfDistinctTrades;
	private Long noOfTrainees;
	
	public DistinctCountModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DistinctCountModel(Long noOfDistinctItis, Long noOfDistinctIndustries, Long noOfDistinctTrades,
			Long noOfTrainees) {
		super();
		this.noOfDistinctItis = noOfDistinctItis;
		this.noOfDistinctIndustries = noOfDistinctIndustries;
		this.noOfDistinctTrades = noOfDistinctTrades;
		this.noOfTrainees = noOfTrainees;
	}

	public Long getNoOfDistinctItis() {
		return noOfDistinctItis;
	}

	public void setNoOfDistinctItis(Long noOfDistinctItis) {
		this.noOfDistinctItis = noOfDistinctItis;
	}

	public Long getNoOfDistinctIndustries() {
		return noOfDistinctIndustries;
	}

	public void setNoOfDistinctIndustries(Long noOfDistinctIndustries) {
		this.noOfDistinctIndustries = noOfDistinctIndustries;
	}

	public Long getNoOfDistinctTrades() {
		return noOfDistinctTrades;
	}

	public void setNoOfDistinctTrades(Long noOfDistinctTrades) {
		this.noOfDistinctTrades = noOfDistinctTrades;
	}

	public Long getNoOfTrainees() {
		return noOfTrainees;
	}

	public void setNoOfTrainees(Long noOfTrainees) {
		this.noOfTrainees = noOfTrainees;
	}

	@Override
	public String toString() {
		return "DistinctCountModel [noOfDistinctItis=" + noOfDistinctItis + ", noOfDistinctIndustries="
				+ noOfDistinctIndustries + ", noOfDistinctTrades=" + noOfDistinctTrades + ", noOfTrainees="
				+ noOfTrainees + "]";
	}
	 
	
}
