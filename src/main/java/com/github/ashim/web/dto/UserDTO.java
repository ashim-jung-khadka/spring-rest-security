package com.github.ashim.web.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserDTO {

	private String username;

	private SecurityRole role;

	public UserDTO(String username, SecurityRole role) {
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public SecurityRole getRole() {
		return role;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}