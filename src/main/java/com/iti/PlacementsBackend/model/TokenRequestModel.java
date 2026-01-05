package com.iti.PlacementsBackend.model;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TokenRequestModel {
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String secretKey;
	private Map<String, String> data;
	
	public TokenRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TokenRequestModel(String secretKey, Map<String, String> data) {
		super();
		this.secretKey = secretKey;
		this.data = data;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "TokenRequestModel [secretKey=" + secretKey + ", data=" + data + "]";
	}
	
}

