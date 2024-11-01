package com.thcode.feedreader.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.thcode.feedreader.filter.JwtRequestFilter;

/**
 * 
 * This is the SecurityConfiguration for the application.
 * 
 * 
 * @author taha-sk
 *
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	/* We injected AuthenticationManager to the AuthenticationController, therefore we
	 * need to expose it as a bean.
	 */
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
	              authorizationManagerRequestMatcherRegistry.requestMatchers("/api/authenticate").permitAll()
	                      .anyRequest().authenticated())
	      .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	      .csrf(AbstractHttpConfigurer::disable)
	      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
	    return http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("https://frclient.herokuapp.com");//http://localhost:4200
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    config.setMaxAge(3600L);
	    source.registerCorsConfiguration("/api/**", config);
	    return new CorsFilter(source);
	}

}
