package com.iti.PlacementsBackend.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.Role;
import com.iti.PlacementsBackend.entity.Users;
import com.iti.PlacementsBackend.repo.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Autowired
	private UserRepository userRepository;
	
	@Value("${tokenkey}") String tokenkey;

	//String SECRET_KEY = "itiasdfasdfasdfasdfasdfasdfasdfsadfitiasdfasdfasdfasdfasdfasdfasdfsadf";

	public String extractUsername(String token) {
		logger.info("extractUsername=>token=>"+token);
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		logger.info("extractClaim=>token=>"+token);
		final Claims claims = extractAllClaims(token);
		logger.info("extractClaim=>claims=>"+claims.toString());
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		logger.info("extractAllClaims=>token=>"+token);
		return Jwts.parser().setSigningKey(tokenkey).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		Users user=null;
		System.out.println("userDetails=>"+userDetails.getUsername());
		try {
		 user = userRepository.findByUsername(userDetails.getUsername());
		 
		claims.put("insCode", user.getInsCode());
		claims.put("username", userDetails.getUsername());
		}catch(Exception e) {
			System.out.println("Exception e"+e);
			e.printStackTrace();
		}
		Set<Role> roles = user.getRoles();
		roles.stream().forEach(a -> 
		claims.put("roleId",  a.getRole_id()));
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, tokenkey).compact();
	}

	public Boolean validToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// Customized method
	public Claims getClaims(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization").substring(7);
		logger.info("authorizationHeader=>"+authorizationHeader);
		Claims claims = Jwts.parser().setSigningKey(tokenkey).parseClaimsJws(authorizationHeader).getBody();
		logger.info("claims");
		logger.info("claims"+claims.toString());
		return claims;
	}

}
