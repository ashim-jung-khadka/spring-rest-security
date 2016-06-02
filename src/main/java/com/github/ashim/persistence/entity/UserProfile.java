package com.github.ashim.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.ashim.persistence.common.enums.UserProfileType;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "profile_type", length = 15, unique = true, nullable = false)
	private String profileType = UserProfileType.USER.getUserProfileType();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((profileType == null) ? 0 : profileType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserProfile)) {
			return false;
		}
		UserProfile other = (UserProfile) obj;
		if (id != other.id) {
			return false;
		}
		if (profileType == null) {
			if (other.profileType != null) {
				return false;
			}
		} else if (!profileType.equals(other.profileType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", profileType=" + profileType + "]";
	}

}
