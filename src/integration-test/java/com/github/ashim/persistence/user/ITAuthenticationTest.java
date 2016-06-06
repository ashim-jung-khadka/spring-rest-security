package com.github.ashim.persistence.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.ashim.spring.config.SpringConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class ITAuthenticationTest extends AbstractIntegration {

	@Test
	public void loginWithCorrectCredentials() throws Exception {
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param(IntegrationTestUtil.REQUEST_PARAMETER_USERNAME, IntegrationTestUtil.CORRECT_USERNAME)
				.param(IntegrationTestUtil.REQUEST_PARAMETER_PASSWORD, IntegrationTestUtil.CORRECT_PASSWORD))
				.andExpect(status().isOk());
	}

	@Test
	public void loginWithIncorrectCredentials() throws Exception {
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param(IntegrationTestUtil.REQUEST_PARAMETER_USERNAME, IntegrationTestUtil.INCORRECT_USERNAME)
				.param(IntegrationTestUtil.REQUEST_PARAMETER_PASSWORD, IntegrationTestUtil.CORRECT_PASSWORD))
				.andExpect(status().isUnauthorized());
	}

	// @Test
	// public void loginByUsingIncorrectRequestMethod() throws Exception {
	// mockMvc.perform(get("/login")
	// .param(IntegrationTestUtil.REQUEST_PARAMETER_USERNAME,
	// IntegrationTestUtil.CORRECT_USERNAME)
	// .param(IntegrationTestUtil.REQUEST_PARAMETER_PASSWORD,
	// IntegrationTestUtil.INCORRECT_USERNAME))
	// .andExpect(status().isUnauthorized());
	// }

	// @Test
	// public void logout() throws Exception {
	// mockMvc.perform(get("/logout").with(userDetailsService(IntegrationTestUtil.CORRECT_USERNAME)))
	// .andExpect(status().isOk());
	// }

}