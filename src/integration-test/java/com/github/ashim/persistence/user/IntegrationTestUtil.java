package com.github.ashim.persistence.user;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IntegrationTestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static final String CORRECT_PASSWORD = "ashim";
	public static final String CORRECT_USERNAME = "ashim";

	public static final String INCORRECT_PASSWORD = "password1";
	public static final String INCORRECT_USERNAME = "user1";

	public static final String REQUEST_PARAMETER_PASSWORD = "password";
	public static final String REQUEST_PARAMETER_USERNAME = "username";

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}