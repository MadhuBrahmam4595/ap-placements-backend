package com.iti.PlacementsBackend.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(	name = "usersapi")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	private String username;
	private String password;
	private String insCode;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch= FetchType.EAGER)
	@JoinTable(name="user_roles",
	joinColumns = {@JoinColumn(name="fk_user_id",referencedColumnName = "user_id")},
	inverseJoinColumns= {@JoinColumn(name="fk_role_id",referencedColumnName = "role_id")})
	private Set<Role> roles=new HashSet<>();
	public Users() {
	}

	public String getPassword() {
		return password;
	}

	 

	public String getInsCode() {
		return insCode;
	}

	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", insCode="
				+ insCode + ", roles=" + roles + "]";
	}

	
}
