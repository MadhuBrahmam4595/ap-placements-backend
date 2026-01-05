package com.iti.PlacementsBackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iti.PlacementsBackend.config.CustomPasswordEncoder;
import com.iti.PlacementsBackend.entity.Users;
import com.iti.PlacementsBackend.model.AuthenticationRequest;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.repo.UserRepository;
import com.iti.PlacementsBackend.service.impl.UserService;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("services")
@CrossOrigin(originPatterns = "*")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MyUtil myUtil;
	
	@PostMapping(value = "addUser")
	public ResponseEntity<?> addRole(@RequestBody Users user,HttpServletRequest httpServletRequest) {
		
		logger.info("addUser=>" + user.toString());

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")) {
			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
		}
		
		Users findByUsername = userRepository.findByUsername(user.getUsername());
		if(findByUsername != null) {
			return new ResponseEntity<String>("User is already existed with the Given Username: "+user.getUsername(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Users>(userService.addUser(user), HttpStatus.OK);
	}
	
	@GetMapping("getAllUsers")
	public ResponseEntity<?> getAllUsers(HttpServletRequest httpServletRequest){
		logger.info("getAllUsers=>");

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		if (!claimsFromToken.getRoleId().equalsIgnoreCase("10")) {
			return new ResponseEntity<String>("Your not autorized to view the data.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Users>>(userRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("getUserInfo")
	public ResponseEntity<?> getUserInfo(HttpServletRequest httpServletRequest){
		logger.info("getUserInfo=>");

		ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		Users findByUsername = userRepository.findByUsername(claimsFromToken.getUsername());
		if(findByUsername != null) {
			return new ResponseEntity<Users>(findByUsername, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("NO DATA FOUND WITH GIVEN USERNAME", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("editUserDetails")
	public ResponseEntity<?> editUserDetails(@RequestBody AuthenticationRequest user,
			HttpServletRequest httpServletRequest) {
		logger.info("getUserInfo=>" + user.toString());

		try {
			ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
			logger.info("claims=>=>" + claimsFromToken.toString());

			Users findByUsername = userRepository.findByUsername(user.getUsername());
			logger.info("findByUsername=>username=>"+findByUsername.getUsername());	
			
			if(claimsFromToken.getUsername().equalsIgnoreCase(findByUsername.getUsername())) {
				CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
				String password = customPasswordEncoder.encode(user.getPassword());
				findByUsername.setPassword(password);
				userRepository.save(findByUsername);
				return new ResponseEntity<String>("User details are updated successfully.You can login with your new credentials.", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Usernames are not matched for update password", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Exception arised while updating password", HttpStatus.BAD_REQUEST);
		}
	}

}
