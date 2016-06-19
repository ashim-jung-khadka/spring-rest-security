package com.github.ashim.web.listener;

import org.springframework.context.ApplicationEvent;

import com.github.ashim.persistence.entity.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 6029328201645993363L;

	private final String appUrl;
	private final User user;

	public OnRegistrationCompleteEvent(final User user, final String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public User getUser() {
		return user;
	}

}