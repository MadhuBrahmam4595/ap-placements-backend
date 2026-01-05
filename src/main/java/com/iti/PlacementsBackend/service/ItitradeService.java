package com.iti.PlacementsBackend.service;

import java.util.List;

import com.iti.PlacementsBackend.projection.UniversalProjection;

public interface ItitradeService {
	
	public List<UniversalProjection> getTradesInIti(String iticode);

}
