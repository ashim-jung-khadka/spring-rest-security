package com.github.ashim.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.ashim.persistence.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository
		extends JpaRepository<VerificationToken, Long>, JpaSpecificationExecutor<VerificationToken> {

	public VerificationToken findByToken(String token);
}