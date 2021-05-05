package com.thcode.feedreader.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * This is the JwtUtil for Jwt usage.
 * This class is responsible for generation, validation and parsing of Jwt.
 * 
 * @author taha-sk
 *
 */
@Component
public class JwtUtil {
	
	//Logger
	Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	private SecretKey secretKey;
	
	@Value("${jwt.issuer}")
    private String issuer;
	
	@Value("${jwt.expiration.ms}")
    private long expiration;
	
	@Autowired
	private Environment environment;
	
	//secret key is provided as environment variable
	private SecretKey getSecretKey() {
		if(this.secretKey == null) {
			this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(environment.getProperty("jwt_secret_key")));
		}
		return this.secretKey;
	}
	
	public String generateTokenForUser(UserDetails user) {
		return Jwts.builder().setIssuer(this.issuer).setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + this.expiration)).setIssuedAt(new Date())
				.signWith(getSecretKey()).compact();
	}
	
	public String getUserNameFromToken(String jws) {
		Jws<Claims> claims = validateAndParseClaims(jws);
		if (claims != null) {
			return claims.getBody().getSubject();
		}else {
			return null;
		}
	}
	
	//parseClaimsJws method also checks for the validity of the token. It looks for your requirements and the date controls. You don't need to specify additional date checks.
	private Jws<Claims> validateAndParseClaims(String jws) {
		try {
			return Jwts.parserBuilder().requireIssuer(this.issuer).setSigningKey(getSecretKey()).build().parseClaimsJws(jws);
		} catch (JwtException ex) {
			logger.error("Invalid JWT signature: {}", ex.getMessage());
			return null;
		}
	}

}
