package com.iti.PlacementsBackend.service.master;

import java.util.List;
import java.util.Optional;

import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;

public interface ItiTradeMasterService {
	
	ItiTradeMasterEntity findByTradeCode(Integer tradeCode);
	Optional<ItiTradeMasterEntity> getByTradeShort(String tradeShort);
	List<ItiTradeMasterEntity> findAllByOrderByTradeNameAsc();

}
