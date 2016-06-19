package com.github.ashim.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.ashim.persistence.common.validation.annotation.Password;
import com.github.ashim.persistence.common.validation.annotation.PasswordMatches;
import com.github.ashim.persistence.common.validation.annotation.ValidEmail;

@PasswordMatches
public class AuthDto {

	@NotNull
	@Size(min = 1)
	private String firstName;

	@NotNull
	@Size(min = 1)
	private String lastName;

	@Password
	private String password;

	@NotNull
	@Size(min = 1)
	private String matchingPassword;

	@ValidEmail
	@NotNull
	@Size(min = 1)
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{\"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName + "\", \"password\": \"" + password
				+ "\", \"matchingPassword\": \"" + matchingPassword + "\", \"email\": \"" + email + "\"}";
	}

}