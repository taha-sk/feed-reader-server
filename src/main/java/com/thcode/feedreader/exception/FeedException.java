package com.thcode.feedreader.exception;

/**
 * 
 * This is the FeedException, used in FeedController
 * 
 * 
 * @author taha-sk
 *
 */
public class FeedException extends Exception {

	private static final long serialVersionUID = -4713393248968314865L;
	
	private final String message;
	
	public FeedException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
