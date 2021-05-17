package com.thcode.feedreader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thcode.feedreader.model.AuthenticationRequest;

/**
 * 
 * This is the AuthenticationController tests
 * 
 * @author taha-sk
 *
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void correctCredentialsShouldBeSuccessful() throws Exception {
		String ip = "127.0.0.1";
		this.mockMvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new AuthenticationRequest("user", "user", ip)))
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.token").exists())
		.andExpect(jsonPath("$.expirationMs").exists())
		.andExpect(jsonPath("$.ip").value(ip))
		.andExpect(jsonPath("$.admin").value(false));
	}
	
	@Test
	void wrongCredentialsShouldFail() throws Exception {
		String ip = "127.0.0.1";
		this.mockMvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new AuthenticationRequest("user", "wrongpass", ip)))
				)
		.andExpect(status().isUnauthorized());
	}

}
