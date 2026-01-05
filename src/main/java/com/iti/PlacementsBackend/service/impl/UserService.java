package com.iti.PlacementsBackend.service.impl;

import java.util.HashSet;
import java.util.Set;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.config.CustomPasswordEncoder;
import com.iti.PlacementsBackend.entity.Role;
import com.iti.PlacementsBackend.entity.Users;
import com.iti.PlacementsBackend.repo.RoleRepository;
import com.iti.PlacementsBackend.repo.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userRepository.findByUsername(username);
		UserDetailsPrincipal userDetailsPrincipal = new UserDetailsPrincipal(user);
		return userDetailsPrincipal;
	}

	public Users addUser(Users user) {
		Set<Role> roles = new HashSet<>();

		CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();

		String password = customPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);

		user.getRoles().forEach(role -> {
			if (role.getRole_id() > 0) {
				Role nrole = roleRepository.findById(role.getRole_id()).get();
				nrole.getUsers().add(user);
				roles.add(nrole);
			} else {
				roles.add(role);
			}
		});
		user.setRoles(roles);
		return userRepository.save(user);
	}

}
