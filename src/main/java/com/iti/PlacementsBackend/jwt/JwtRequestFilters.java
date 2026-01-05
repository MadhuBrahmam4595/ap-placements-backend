package com.iti.PlacementsBackend.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iti.PlacementsBackend.exception.ErrorResponse;
import com.iti.PlacementsBackend.model.ValidateTokenRequestModel;
import com.iti.PlacementsBackend.service.impl.UserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilters extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilters.class);

	@Autowired
	private UserService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Value("${tokenkey}") private String tokenkey;
	@Value("${validateToken}") private String validateToken;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
		logger.info("Authorization=>" + authorizationHeader);

		String requestUri = request.getServletContext().getContextPath() + request.getServletPath();
		logger.info("requestUri=>" + requestUri);

		if (requestUri.startsWith("/placementsbe/api") || requestUri.startsWith("/placementsbe/masterdata") || 
				requestUri.startsWith("/placementsbe/std") || requestUri.startsWith("/placementsbe/frontend/")||
				requestUri.startsWith("/placementsbe/hrm")) {
			logger.info("public end points");
		} else {

			try {
				logger.info("resticted apis");

				if (authorizationHeader == null) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Authorization Header is Required for accessing data");
					return;
				}

				if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
					String username = null, jwt = null;

					jwt = authorizationHeader.substring(7);
					logger.info("jwt=> " + jwt);

					ValidateTokenRequestModel validateTokenRequestModel = new ValidateTokenRequestModel();
					validateTokenRequestModel.setJwtToken(jwt);
					validateTokenRequestModel.setSecretKey(tokenkey);

					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<Boolean> resp = restTemplate.postForEntity(validateToken, validateTokenRequestModel,
							Boolean.class);
					logger.info("resp=>" + resp.toString());
					logger.info("getStatusCode=>" + resp.getStatusCode());

					if (resp.getStatusCode().is2xxSuccessful()) {

						logger.info(resp.getBody().toString());

						Claims claims = jwtUtil.getClaims(request);
						logger.info("claims" + claims.toString());

						username = claims.get("username", String.class);
						logger.info("username"+username);

						UserDetails userDetails = userDetailsService.loadUserByUsername(username);
						logger.info("userDetails" + userDetails.getAuthorities());
						logger.info(userDetails.getAuthorities().toString());

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

					}

				} // api
			} catch (Exception e) {
				
				logger.error("Authentication server says invalid token");
				e.printStackTrace();
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setHttpStatus(HttpServletResponse.SC_UNAUTHORIZED);
				errorResponse.setTimestamp(String.valueOf(LocalDateTime.now()));
				errorResponse.setMessage("Given Token is Invalid.");

				ObjectMapper obj = new ObjectMapper();
				response.getWriter().write(obj.writeValueAsString(errorResponse));
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

		} // doFilterInternal
		logger.info("before filter chain");
		filterChain.doFilter(request, response);
	}

}
