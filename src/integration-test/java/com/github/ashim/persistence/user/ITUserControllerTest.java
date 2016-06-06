package com.github.ashim.persistence.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class ITUserControllerTest extends AbstractIntegration {

	// @Test
	// public void getLoggedInUserAsAnonymous() throws Exception {
	// mockMvc.perform(get("/users/auth")).andExpect(status().isOk()).andExpect(content().string(""));
	// }

	@Test
	public void getLoggedInUserAsUser() throws Exception {

		mockMvc.perform(get("/users/auth").with(testUser())).andExpect(status().isOk())
				.andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
				.andExpect(content().string("{\"username\":\"ashim\",\"role\":\"ROLE_ADMIN\"}"));

	}

}