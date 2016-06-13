package com.github.ashim.persistence.entity;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.ashim.persistence.common.validation.annotation.AlphaNumeric;
import com.github.ashim.persistence.common.validation.annotation.Alphabet;
import com.github.ashim.persistence.common.validation.annotation.Numeric;
import com.github.ashim.persistence.common.validation.annotation.OmitScriptlet;
import com.github.ashim.persistence.common.validation.annotation.Password;
import com.github.ashim.persistence.common.validation.annotation.Phone;
import com.github.ashim.persistence.common.validation.annotation.Username;

public class ValidationDto {

	@NotBlank
	@Username
	private String username;

	@Password
	@NotBlank
	private String password;

	@NotEmpty
	private String passwordMatches;

	@NotBlank
	@Alphabet
	private String name;

	@NotBlank
	@AlphaNumeric
	private String companyName;

	@NotBlank
	@OmitScriptlet
	private String description;

	@Email
	@NotBlank
	private String email;

	@Phone
	@NotBlank
	private String phone;

	@URL
	@NotBlank
	private String url;

	@NotBlank
	@Length(min = 5)
	private String length;

	@NotNull
	@Numeric
	@Digits(integer = 3, fraction = 0)
	private Integer size;

	@NotNull
	@Digits(integer = 5, fraction = 2)
	private Double amount;

	@NotNull
	@Numeric
	private Integer number;

	@Future
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date futureDate;

	@Past
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date pastDate;

	private Date createDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordMatches() {
		return passwordMatches;
	}

	public void setPasswordMatches(String passwordMatches) {
		this.passwordMatches = passwordMatches;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getFutureDate() {
		return futureDate;
	}

	public void setFutureDate(Date futureDate) {
		this.futureDate = futureDate;
	}

	public Date getPastDate() {
		return pastDate;
	}

	public void setPastDate(Date pastDate) {
		this.pastDate = pastDate;
	}

	public Date getCreateDate() {
		if (createDate == null) {
			createDate = new Date();
		}

		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ValidationDto [username=" + username + ", password=" + password + ", passwordMatches=" + passwordMatches
				+ ", name=" + name + ", companyName=" + companyName + ", description=" + description + ", email="
				+ email + ", phone=" + phone + ", url=" + url + ", length=" + length + ", size=" + size + ", amount="
				+ amount + ", number=" + number + ", futureDate=" + futureDate + ", pastDate=" + pastDate
				+ ", createDate=" + createDate + "]";
	}

}