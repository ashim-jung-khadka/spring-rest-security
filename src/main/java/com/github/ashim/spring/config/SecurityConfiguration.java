package com.github.ashim.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.github.ashim.spring.security.CustomAccessDeniedHandler;
import com.github.ashim.spring.security.RestAuthenticationEntryPoint;
import com.github.ashim.spring.security.SuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private SuccessHandler authenticationSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off

		 http
	        .csrf().disable()
	        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
	        .authenticationEntryPoint(restAuthenticationEntryPoint)
	        .and()
	        .authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/users/**").access("hasRole('ADMIN')")
			.and()
	        .formLogin()
	        .successHandler(authenticationSuccessHandler)
	        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
	        .and()
	        .logout();

		 	//http.authorizeRequests()
			//.antMatchers("/").permitAll()
			//.antMatchers("/users/**").access("hasRole('ADMIN')")
			//.and()
			//.formLogin()
			//.successHandler(authenticationSuccessHandler)
			//.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			//.loginPage("/login")
			//.usernameParameter("ssoId").passwordParameter("password")
			//.and().csrf().disable()
			//.exceptionHandling();
			//.accessDeniedPage("/Access_Denied");

		// @formatter:on
	}

	@Bean
	public SuccessHandler mySuccessHandler() {
		return new SuccessHandler();
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
