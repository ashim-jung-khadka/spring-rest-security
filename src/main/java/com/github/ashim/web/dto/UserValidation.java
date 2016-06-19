package com.github.ashim.web.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.github.ashim.persistence.common.validation.annotation.Alphabet;
import com.github.ashim.persistence.common.validation.annotation.Password;
import com.github.ashim.persistence.common.validation.annotation.PasswordMatches;
import com.github.ashim.persistence.common.validation.annotation.ValidEmail;

@PasswordMatches
public class UserValidation {

	@Alphabet
	@NotBlank
	private String firstName;

	@Alphabet
	@NotBlank
	private String lastName;

	@Password
	@NotBlank
	private String password;

	@NotBlank
	private String matchingPassword;

	@ValidEmail
	@NotBlank
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
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", matchingPassword=" + matchingPassword + ", email=" + email + "]";
	}

}