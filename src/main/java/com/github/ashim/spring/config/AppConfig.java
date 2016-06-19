package com.github.ashim.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:config/email.properties")
public class AppConfig {

	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {

		// final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		// mailSenderImpl.setHost(env.getProperty("smtp.host"));
		// mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
		// mailSenderImpl.setUsername(env.getProperty("smtp.username"));
		// mailSenderImpl.setPassword(env.getProperty("smtp.password"));
		//
		// final Properties javaMailProps = new Properties();
		// javaMailProps.put("mail.smtp.auth", true);
		// javaMailProps.put("mail.smtp.starttls.enable", true);
		// javaMailProps.put("mail.debug", true);
		//
		// mailSenderImpl.setJavaMailProperties(javaMailProps);
		// return mailSenderImpl;

		// <bean id="mailSender"
		// class="org.springframework.mail.javamail.JavaMailSenderImpl">
		// <property name="host" value="smtp.gmail.com"/>
		// <property name="port" value="25"/>
		// <property name="username" value="howtodoinjava@gmail.com"/>
		// <property name="password" value="password"/>
		// <property name="javaMailProperties">
		// <props>
		// <prop key="mail.transport.protocol">smtp</prop>
		// <prop key="mail.smtp.auth">true</prop>
		// <prop key="mail.smtp.starttls.enable">true</prop>
		// <prop key="mail.debug">true</prop>
		// </props>
		// </property>
		// </bean>

		JavaMailSenderImpl mail = new JavaMailSenderImpl();
		mail.setHost("smtp.gmail.com");
		mail.setPort(25);
		mail.setUsername("demoashim@gmail.com");
		mail.setPassword("ashim100");

		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", true);

		mail.setJavaMailProperties(properties);

		return mail;

	}

}