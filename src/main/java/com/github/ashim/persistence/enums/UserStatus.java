package com.github.ashim.persistence.enums;

public enum UserStatus {

	ACTIVE("active"), INACTIVE("inactive"), DELETED("deleted"), LOCKED("locked");

	private String state;

	private UserStatus(final String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	@Override
	public String toString() {
		return this.state;
	}

	public String getName() {
		return this.name();
	}

}
