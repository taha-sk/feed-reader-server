package com.thcode.feedreader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 
 * This is the FeedController tests
 * 
 * @author taha-sk
 *
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class FeedControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void unauthorizedCallShouldFail() throws Exception {
		this.mockMvc.perform(get("/api/getFeed"))
		.andExpect(status().isUnauthorized());
	}
	
	@WithMockUser("user")
	@Test
	void authorizedCallWithoutFeedUrlShouldFail() throws Exception {
		this.mockMvc.perform(get("/api/getFeed"))
		.andExpect(status().isBadRequest());
	}
	
	@WithMockUser("user")
	@Test
	void authorizedCallWithCorrectFeedUrlShouldSucceed() throws Exception {
		this.mockMvc.perform(get("/api/getFeed").param("feedUrl", "https://thcodejournal.wordpress.com/feed"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.feedTitle").value("TH Code Journal"));
	}
	
	@WithMockUser("user")
	@Test
	void authorizedCallWithWrongFeedUrlShouldFail() throws Exception {
		this.mockMvc.perform(get("/api/getFeed").param("feedUrl", "https://thcodejournal.wordpress.com"))
		.andExpect(status().isBadRequest());
	}

}
