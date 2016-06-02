package com.github.ashim.persistence.common.enums;

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
