package com.thcode.feedreader.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsServiceConfiguration {
	
	/* I'm using in memory user details manager.
	 * It's the easiest choice for this tutorial.
	 */
	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		UserBuilder users = User.builder();
		UserDetails user = users
				.username("user")
				.password(bCryptPasswordEncoder.encode("user"))
				.roles("USER")
				.build();
		UserDetails admin = users
				.username("admin")
				.password(bCryptPasswordEncoder.encode("admin"))
				.roles("USER","ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
