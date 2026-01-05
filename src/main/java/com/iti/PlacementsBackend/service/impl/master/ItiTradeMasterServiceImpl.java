package com.iti.PlacementsBackend.service.impl.master;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.repo.master.ItiTradeMasterRepo;
import com.iti.PlacementsBackend.service.master.ItiTradeMasterService;


@Service
public class ItiTradeMasterServiceImpl implements ItiTradeMasterService{
	
	@Autowired
	private ItiTradeMasterRepo repo;

	@Override
	public ItiTradeMasterEntity findByTradeCode(Integer tradeCode) {
		// TODO Auto-generated method stub
		return repo.findByTradeCode(tradeCode);
	}

	@Override
	public Optional<ItiTradeMasterEntity> getByTradeShort(String tradeShort) {
		// TODO Auto-generated method stub
		return repo.findById(tradeShort);
	}

	@Override
	public List<ItiTradeMasterEntity> findAllByOrderByTradeNameAsc() {
		// TODO Auto-generated method stub
		return repo.findAllByOrderByTradeNameAsc();
	}

}

