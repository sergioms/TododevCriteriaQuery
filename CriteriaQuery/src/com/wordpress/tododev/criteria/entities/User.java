package com.wordpress.tododev.criteria.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private Integer userId;
	
	@Basic
	@Column(length=15, nullable=false)
	private String name;
	
	@Basic
	@Column(length=64, nullable=false)
	private String userDigestedPasswd;
	
	@Basic
	@Column(length=50, nullable=true)
	private String email;
	
	@Basic
	@Column(nullable=false)
	public Integer privilegeLevel;
	
	@Basic
	@Column(nullable=false)
	private Boolean active;

	public User() {
	}

	public User(Integer userId, String name, String userDigestedPasswd,
			String email, Integer privilegeLevel, Boolean active) {
		this.userId = userId;
		this.name = name;
		this.userDigestedPasswd = userDigestedPasswd;
		this.email = email;
		this.privilegeLevel = privilegeLevel;
		this.active = active;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getUserDigestedPasswd() {
		return userDigestedPasswd;
	}

	public String getEmail() {
		return email;
	}

	public Integer getPrivilegeLevel() {
		return privilegeLevel;
	}

	public Boolean getActive() {
		return active;
	}

}
