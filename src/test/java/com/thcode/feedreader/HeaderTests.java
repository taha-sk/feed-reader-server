package com.thcode.feedreader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.thcode.feedreader.model.AuthenticationRequest;

/**
 * 
 * This is the Header Tests for JwtFilter
 * 
 * @author taha-sk
 *
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeaderTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private String ip = "127.0.0.1";
	private String token = null;
	
	@BeforeAll
	void authenticate() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new AuthenticationRequest("user", "user", this.ip)))
				)
		.andExpect(status().isOk())
		.andReturn();
		
		this.token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
	}
	
	@Test
	void authorizedCallShouldBeSuccessfulOnWidgetWithHeaders() throws Exception {
		this.mockMvc.perform(get("/api/widgetTypes")
				.header("X-Forwarded-For", this.ip)
				.header("Authorization", "Bearer " + this.token))
		.andExpect(status().isOk());
	}
	
	@Test
	void authorizedCallWithMissingHeadersShouldFailOnWidget() throws Exception {
		this.mockMvc.perform(get("/api/widgetTypes")
				.header("Authorization", "Bearer " + this.token))
		.andExpect(status().isUnauthorized());
	}

}
