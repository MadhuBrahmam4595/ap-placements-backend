package com.iti.PlacementsBackend.service.master;

import java.util.List;

import com.iti.PlacementsBackend.entity.master.States_mastEntity;


public interface States_mastService {
   List<States_mastEntity> getAllStates();
   States_mastEntity getByStatecode(String statecode);
}
