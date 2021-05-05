package com.thcode.feedreader.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.thcode.feedreader.util.JwtUtil;

/**
 * 
 * This is the JwtRequestFilter for Jwt
 * It gets the token from the header and validates it.
 * If it's valid, then creates the authentication for the user.
 * 
 * @author taha-sk
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Get Header
		final String requestAuthorizationHeader = request.getHeader("Authorization");
		
		//If it exists, then continue
		if(requestAuthorizationHeader != null) {
			
			//Check for "Bearer <token>"
			if (requestAuthorizationHeader.startsWith("Bearer ")) {
				String jws = requestAuthorizationHeader.substring(7);

				//Get user name from the token
				String userName = jwtUtil.getUserNameFromToken(jws);

				//If you can find the user name, then load it and authenticate.
				if(userName != null) {
					UserDetails user = userDetailsService.loadUserByUsername(userName);
					if(user != null) {
						SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
					}else {
						logger.warn("User not found.");
					}
				}

			}else {
				logger.warn("Authorization Header does not start with \"Bearer \".");
			}
		}
		
		//Go to next filter
		filterChain.doFilter(request, response);
		
	}

}
