package com.github.ashim.persistence.service;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.entity.VerificationToken;
import com.github.ashim.web.dto.AuthDto;

public interface RegistrationService {

	public User registerNewUser(AuthDto authDto);

	public void createVerificationTokenForUser(final User user, final String token);

	public VerificationToken getVerificationToken(final String VerificationToken);

	public void saveVerifiedToken(final VerificationToken verificationToken);

	public void saveRegisteredUser(final User user);

	public VerificationToken generateNewVerificationToken(final String existingVerificationToken);

	public User findUserByEmail(final String email);

	public void createPasswordResetTokenForUser(final User user, final String token);

	public void changeUserPassword(User user, String password);

}
