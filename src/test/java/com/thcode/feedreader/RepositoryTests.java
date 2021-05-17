package com.thcode.feedreader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thcode.feedreader.model.Widget;

/**
 * 
 * This is the Spring Data Rest Repositories tests
 * 
 * @author taha-sk
 *
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void unauthorizedCallShouldFailOnWidget() throws Exception {
		this.mockMvc.perform(get("/api/widgets"))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	void unauthorizedCallShouldFailOnWidgetType() throws Exception {
		this.mockMvc.perform(get("/api/widgetTypes"))
		.andExpect(status().isUnauthorized());
	}
	
	@WithMockUser("user")
	@Test
	void authorizedCallShouldBeSuccessfulOnWidget() throws Exception {
		this.mockMvc.perform(get("/api/widgets"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser("user")
	@Test
	void authorizedCallShouldBeSuccessfulOnWidgetType() throws Exception {
		this.mockMvc.perform(get("/api/widgetTypes"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser("user")
	@Test
	void authenticatedCallShouldBeForbiddenForUserAuthorizationOnWidget() throws Exception {
		this.mockMvc.perform(post("/api/widgets").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new Widget(null, "test widget", "test source")))
				)
		.andExpect(status().isForbidden());
	}
	
	@WithMockUser("user")
	@Test
	void authenticatedCallShouldBeForbiddenForUserAuthorizationOnWidgetTypes() throws Exception {
		this.mockMvc.perform(post("/api/widgetTypes").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new Widget(null, "test widget", "test source")))
				)
		.andExpect(status().isForbidden());
	}
	
	@WithMockUser(username = "admin", authorities = { "ROLE_ADMIN" })
	@Test
	void authenticatedCallShouldBeOkForAdminAuthorizationOnWidgetWithLimit() throws Exception {
		//Repository entity creation test
		for(int i=0; i<5; i++) {
			this.mockMvc.perform(post("/api/widgets").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(new Widget(null, "test widget", "test source")))
					)
			.andExpect(status().isCreated());
		}
		//Limit test
		this.mockMvc.perform(post("/api/widgets").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new Widget(null, "test widget", "test source")))
				)
		.andExpect(status().isForbidden());
	}
	
	@WithMockUser(username = "admin", authorities = { "ROLE_ADMIN" })
	@Test
	void missingTitleWidgetSaveRequestShouldBeInvalid() throws Exception {
		this.mockMvc.perform(post("/api/widgets").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new Widget(null, null, "test source")))
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0].message").value("Widget title is required."));
	}
	
	@WithMockUser(username = "admin", authorities = { "ROLE_ADMIN" })
	@Test
	void missingSourceWidgetSaveRequestShouldBeInvalid() throws Exception {
		this.mockMvc.perform(post("/api/widgets").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new Widget(null, "test widget", null)))
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0].message").value("Widget Source is required."));
	}

}
