package com.github.ashim.persistence.enums;

public enum UserProfileType {

	ADMIN("ADMIN"), USER("USER");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
