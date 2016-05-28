package com.github.ashim.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ashim.persistence.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

	UserProfile findById(Integer id);

	UserProfile findByProfileType(String profileType);

}
