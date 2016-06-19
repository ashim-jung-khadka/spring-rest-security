package com.github.ashim.web.controller;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.entity.VerificationToken;
import com.github.ashim.persistence.service.RegistrationService;
import com.github.ashim.web.dto.AuthDto;
import com.github.ashim.web.dto.GenericResponse;
import com.github.ashim.web.listener.OnRegistrationCompleteEvent;
import com.github.ashim.web.util.SecurityService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(method = RequestMethod.POST)
	public User registerUserAccount(@RequestBody @Valid AuthDto authDto, final HttpServletRequest request) {

		final User user = registrationService.registerNewUser(authDto);

		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));

		return user;
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public GenericResponse confirmRegistration(@RequestParam("token") final String token) {

		VerificationToken verificationToken = registrationService.getVerificationToken(token);
		GenericResponse genericResponse = new GenericResponse();

		if (verificationToken == null) {
			genericResponse.setMessage("Invalid account confirmation token.");

		} else if (verificationToken.isUsed()) {
			genericResponse.setMessage("Your have already used registration token.");

		} else if (new Date().after(verificationToken.getExpiryDate())) {
			genericResponse.setMessage("Your registration token has expired. Please register again.");

		} else {

			verificationToken.isUsed(true);
			registrationService.saveVerifiedToken(verificationToken);

			final User user = verificationToken.getUser();
			user.setEnabled(true);
			registrationService.saveRegisteredUser(user);

			genericResponse.setMessage("Your account verified successfully");
		}

		return genericResponse;
	}

	@RequestMapping(value = "/resend", method = RequestMethod.GET)
	public GenericResponse resendRegistrationToken(final HttpServletRequest request,
			@RequestParam("token") final String existingToken) {

		// TODO : Send mail only if token has expired
		final VerificationToken newToken = registrationService.generateNewVerificationToken(existingToken);
		final User user = newToken.getUser();

		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));

		return new GenericResponse("We have send an email with a new registration token to your email account");
	}

	// Reset password
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public GenericResponse resetPassword(final HttpServletRequest request,
			@RequestParam("email") final String userEmail) {

		final User user = registrationService.findUserByEmail(userEmail);

		if (user == null) {
			// TODO : Add UserNotFoundException()
			// TODO : Check if reset token is send or not
			// throw new UserNotFoundException();
		}

		final String token = UUID.randomUUID().toString();
		registrationService.createPasswordResetTokenForUser(user, token);
		mailSender.send(constructResetTokenEmail(getAppUrl(request), token, user));

		return new GenericResponse("You should receive an Password Reset Email shortly.");
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public GenericResponse showChangePasswordPage(@RequestParam("id") final long id,
			@RequestParam("token") final String token) {

		final String result = securityService.validatePasswordResetToken(id, token);

		if (result != null) {
			// TODO : Handle Exception for Invalid Password Token
		}

		GenericResponse genericResponse = new GenericResponse(
				"Please goto /registration/savePassword for password change");
		return genericResponse;
	}

	// @PreAuthorize("hasRole('READ_PRIVILEGE')")
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public GenericResponse savePassword(@RequestParam("password") final String password) {

		// TODO : Change it to SpringContextUtil
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// TODO : Validate password
		registrationService.changeUserPassword(user, password);

		return new GenericResponse("Password reset successfully");
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public GenericResponse changeUserPassword(final Locale locale, @RequestParam("password") final String password,
			@RequestParam("oldPassword") final String oldPassword) {
		System.out.println(locale);
		final User user = registrationService
				.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (!registrationService.checkIfValidOldPassword(user, oldPassword)) {
			// TODO : Handle Invalid Password
			// throw new InvalidOldPasswordException();
		}
		registrationService.changeUserPassword(user, password);
		return new GenericResponse("Password updated successfully");
	}

	private final SimpleMailMessage constructResetTokenEmail(final String contextPath, final String token,
			final User user) {
		final String url = contextPath + "/registration/changePassword?id=" + user.getId() + "&token=" + token;
		final String message = "Reset Password";
		return constructEmail("Reset Password", message + " \r\n" + url, user);
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {

		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom("ashim.jung.khadka@gmail.com");
		return email;
	}

}