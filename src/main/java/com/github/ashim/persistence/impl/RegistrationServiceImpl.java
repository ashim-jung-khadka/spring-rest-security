package com.github.ashim.persistence.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.common.enums.UserStatus;
import com.github.ashim.persistence.common.utility.ServiceHelper;
import com.github.ashim.persistence.entity.PasswordResetToken;
import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.entity.UserProfile;
import com.github.ashim.persistence.entity.VerificationToken;
import com.github.ashim.persistence.repo.PasswordResetTokenRepository;
import com.github.ashim.persistence.repo.UserProfileRepository;
import com.github.ashim.persistence.repo.UserRepository;
import com.github.ashim.persistence.repo.VerificationTokenRepository;
import com.github.ashim.persistence.service.RegistrationService;
import com.github.ashim.web.dto.AuthDto;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	@Override
	public User registerNewUser(AuthDto authDto) {

		String password = ServiceHelper.passwordEncoder(authDto.getPassword());

		// TODO : Check Email Exists
		// TODO : Remove Username and use email
		final User user = new User();
		user.setUserName(authDto.getEmail());
		user.setFirstName(authDto.getFirstName());
		user.setLastName(authDto.getLastName());
		user.setEmail(authDto.getEmail());
		user.setPassword(password);
		user.setStatus(UserStatus.ACTIVE.getName());

		Set<UserProfile> userProfiles = new HashSet<>();
		userProfiles.addAll(userProfileRepository.findAll());
		user.setUserProfiles(userProfiles);

		try {
			userRepository.save(user);
		} catch (Exception ex) {
			throw ex;
		}
		return user;
	}

	@Override
	public void createVerificationTokenForUser(final User user, final String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	@Override
	public VerificationToken getVerificationToken(final String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	@Override
	public void saveVerifiedToken(final VerificationToken verificationToken) {
		tokenRepository.save(verificationToken);
	}

	@Override
	public void saveRegisteredUser(final User user) {
		userRepository.save(user);
	}

	@Override
	public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
		VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
		vToken.updateToken(UUID.randomUUID().toString());
		vToken = tokenRepository.save(vToken);
		return vToken;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepository.save(myToken);
	}

	@Override
	public void changeUserPassword(User user, String password) {
		password = ServiceHelper.passwordEncoder(password);
		user.setPassword(password);
		userRepository.save(user);
	}

	@Override
	public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
		String password = ServiceHelper.passwordEncoder(oldPassword);
		return user.getPassword().equals(password);
	}

}