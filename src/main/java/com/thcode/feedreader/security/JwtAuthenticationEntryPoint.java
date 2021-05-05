package com.thcode.feedreader.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * This is the JwtAuthenticationEntryPoint for exception handling in authentication.
 * If you don't set this, then Spring will default to Http 403 for Unauthorized access.
 * However, 403 is used for Forbidded access. They're both close in meaning, but 
 * we'll use 401 for user is not authenticated, and 403 for user is authenticated
 * but has no proper right or role for the request.
 * 
 * 
 * @author taha-sk
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
	}

}
