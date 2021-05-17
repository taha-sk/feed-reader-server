package com.thcode.feedreader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.thcode.feedreader.controller.AuthenticationController;

/**
 * 
 * This is the test for contextLoad. It's the server sanity check.
 * 
 * @author taha-sk
 *
 */
@ActiveProfiles("test")
@SpringBootTest
class FeedReaderServerApplicationTests {
	
	@Autowired
	private AuthenticationController authenticationController;

	@Test
	void contextLoads() {
		assertNotNull(authenticationController);
	}

}
