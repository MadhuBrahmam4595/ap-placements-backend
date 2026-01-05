package com.iti.PlacementsBackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iti.PlacementsBackend.entity.Users;
import com.iti.PlacementsBackend.model.AuthenticationRequest;
import com.iti.PlacementsBackend.model.TokenRequestModel;
import com.iti.PlacementsBackend.model.TokenResponseModel;
import com.iti.PlacementsBackend.repo.UserRepository;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Value("${tokenkey}") private String tokenkey;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MyUtil myUtil;
	
	 

	
	@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		System.out.println("hellohellohellohellohellohellohellohello");
		return ResponseEntity.ok("hello from controller");
	}

//	@PostMapping(value = "addRole")
//	public Role addRole(@RequestBody Role role) {
//		return roleRepository.save(role);
//
//	}
//
	

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthToken(@Valid @RequestBody AuthenticationRequest authenticationRequest)
			throws AuthenticationException {
		System.out.println("authenticate" + authenticationRequest.toString());
		Authentication authentication = null;
		try {
			System.out.println("dddddddddddddddddddddddddddd");
			  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			System.out.println("authentication=>" + authentication.getPrincipal());
			System.out.println("authentication=>" + authentication.isAuthenticated());
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);

		}

		Users user = userRepository.findByUsername(authenticationRequest.getUsername());

		Map<String, String> data = new HashMap<>();
		user.getRoles().stream().forEach(a -> data.put("roleId", String.valueOf(a.getRole_id())));
		data.put("ins_code", user.getInsCode());
		data.put("username", authenticationRequest.getUsername());

		TokenRequestModel reqModel = new TokenRequestModel();
		reqModel.setSecretKey(tokenkey);
		reqModel.setData(data);

		String jwtToken = myUtil.generateToken(reqModel);
		TokenResponseModel respModel = new TokenResponseModel();
		respModel.setJwtToken(jwtToken);

		if (respModel.getJwtToken() == null) {
			return new ResponseEntity<>("Given Username length is not sufficient for generating JWT Token.",
					HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(respModel, HttpStatus.OK);
		}
	}

//	@PostMapping("/validateToken")
//	public ResponseEntity<?> createAuthentivationToken(HttpServletRequest request) {
//		System.out.println("validateToken");
//		
//		Claims claims = jwtUtil.getClaims(request);
//		
//		String username = String.valueOf(claims.get("username"));
//		final String authorizationHeader = request.getHeader("Authorization");
//		
//		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//		
//		Boolean valid = jwtUtil.validToken(authorizationHeader, userDetails);
//		if(valid) {
//			String insCode = String.valueOf(claims.get("insCode"));
//			String roleId = String.valueOf(claims.get("roleId"));
//			ResponseObject responseObject = new ResponseObject();
//			responseObject.setValidToken(valid);
//			responseObject.setInsCode(insCode);
//			responseObject.setRoleId(roleId);
//			
//			return new ResponseEntity<>(responseObject,HttpStatus.OK);
//		} 
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//	}

}

