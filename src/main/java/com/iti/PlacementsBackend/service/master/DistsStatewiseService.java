package com.iti.PlacementsBackend.service.master;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.master.DistsStatewise;

public interface DistsStatewiseService {
	
	public DistsStatewise saveDistsStatewise(DistsStatewise distsStatewise);
	
	public Optional<DistsStatewise> getDistStatewise(Integer distcode);
	
	public List<DistsStatewise> getAllDistStatewise();
	
	
	

}

