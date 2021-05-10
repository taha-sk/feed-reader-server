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
	
	private final long expirationMs;
	private final String ip;
	private final boolean admin;
	
	public AuthenticationResponse(String token, long expirationMs, String ip, boolean admin) {
		this.token = token;
		this.expirationMs = expirationMs;
		this.ip = ip;
		this.admin = admin;
	}

	public String getToken() {
		return token;
	}
	
	public long getExpirationMs() {
		return expirationMs;
	}

	public String getIp() {
		return ip;
	}
	
	public boolean isAdmin() {
		return admin;
	}

}
