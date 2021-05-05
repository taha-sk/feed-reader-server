package com.thcode.feedreader.model;

/**
 * 
 * This is the Feed Request Model for the Feed Controller
 * 
 * @author taha-sk
 *
 */
public class FeedRequest {
	
	private String feedUrl;
	
	protected FeedRequest() {}
	
	public FeedRequest(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
	
}
