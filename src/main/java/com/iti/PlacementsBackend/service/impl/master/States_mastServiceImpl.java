package com.iti.PlacementsBackend.service.impl.master;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.master.States_mastEntity;
import com.iti.PlacementsBackend.repo.master.States_mastRepo;
import com.iti.PlacementsBackend.service.master.States_mastService;

@Service
public class States_mastServiceImpl implements States_mastService {
   @Autowired
   private States_mastRepo repo;

   public List<States_mastEntity> getAllStates() {
      return this.repo.findAll();
   }
   @Override
	public States_mastEntity getByStatecode(String statecode) {
		// TODO Auto-generated method stub
		return this.repo.findByStatecode(statecode);
	}
}
