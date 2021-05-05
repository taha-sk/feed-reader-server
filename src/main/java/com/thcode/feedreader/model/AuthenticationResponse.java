package com.thcode.feedreader.model;

/**
 * 
 * This is the Authentication Response Model for Jwt Authentication
 * 
 * @author taha-sk
 *
 */
public class AuthenticationResponse {
	
	private final String token;
	
	//No default constructor here, because final property should be initialized.
	
	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
