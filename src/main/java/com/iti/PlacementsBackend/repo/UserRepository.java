package com.iti.PlacementsBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iti.PlacementsBackend.entity.Users;




@Repository
public interface  UserRepository extends JpaRepository<Users, Long> {
	
	
		public Users findByUsername(String username);
		public Users findByInsCode(String ins_code);

		Boolean existsByUsername(String username);
		
		

	  
}
