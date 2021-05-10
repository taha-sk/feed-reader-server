package com.thcode.feedreader.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thcode.feedreader.model.AuthenticationRequest;
import com.thcode.feedreader.model.AuthenticationResponse;
import com.thcode.feedreader.util.JwtUtil;

/**
 * 
 * This is the AuthenticationController for validating authentication with user
 * 
 * 
 * @author taha-sk
 *
 */
@RestController
public class AuthenticationController {
	
	Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/api/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		
		logger.info("Authentication request received.");
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		UserDetails user = (UserDetails) authentication.getPrincipal();
		
		String ip = authenticationRequest.getIp();
		
		return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateTokenForUserWithIP(user, ip), jwtUtil.getExpiration(), ip, false));
	}
	
	//FOR THE TEST of Authorization - remove these later on
	
	@GetMapping("/api/resource")
	public Map<String,Object> home() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
	
	@GetMapping("/api/resource2")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String,Object> home2() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}

}
