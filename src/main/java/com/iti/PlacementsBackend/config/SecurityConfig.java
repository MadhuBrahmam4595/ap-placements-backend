package com.iti.PlacementsBackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.iti.PlacementsBackend.jwt.JwtRequestFilters;
import com.iti.PlacementsBackend.service.impl.UserService;
import com.iti.PlacementsBackend.util.SimpleCORSFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private JwtRequestFilters jwtRequestFilters;
	
	@Autowired 
	private SimpleCORSFilter simpleCORSFilter;
	
	@Bean
	public CustomPasswordEncoder bcrypPasswordEncoder() {
		return new CustomPasswordEncoder();
	}
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(bcrypPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/frontend/**")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/std/**")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/masterdata/**")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/hrm/**")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/iti/**")).hasRole("ITI")
		.requestMatchers(new AntPathRequestMatcher("/navbar/")).hasAnyRole("ITI","DCP","ADMIN","RDD","NODAL")
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(daoAuthenticationProvider())
		.addFilterBefore(simpleCORSFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterAfter(jwtRequestFilters, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
	         throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
}
