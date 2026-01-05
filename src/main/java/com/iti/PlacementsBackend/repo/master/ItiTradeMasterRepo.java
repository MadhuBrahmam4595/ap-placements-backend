package com.iti.PlacementsBackend.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iti.PlacementsBackend.entity.master.ItiTradeMasterEntity;
import com.iti.PlacementsBackend.projection.UniversalProjection;


public interface ItiTradeMasterRepo extends JpaRepository<ItiTradeMasterEntity, String> {
	
	ItiTradeMasterEntity findByTradeCode(Integer tradeCode);
	List<ItiTradeMasterEntity> findAllByOrderByTradeNameAsc();
	
	//get trades in individual iti based on iticode
	@Query(value = "select  a.trade_short as strcol1,b.trade_name as strcol2 from ititrade a\r\n"
			+ " inner join ititrade_master b on a.trade_short = b.trade_short\r\n"
			+ " where iti_code=:iticode",nativeQuery = true)
	public List<UniversalProjection> getTradesInIti(String iticode);
}

