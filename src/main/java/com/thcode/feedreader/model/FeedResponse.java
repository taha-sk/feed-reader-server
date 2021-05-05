package com.thcode.feedreader.model;

/**
 * 
 * This is the Feed Response Model for the Feed Controller
 * 
 * @author taha-sk
 *
 */
public class FeedResponse {
	
	private String feedTitle;
	private FeedItem[] feedItems;
	
	protected FeedResponse() {}
	
	public FeedResponse(String feedTitle, FeedItem[] feedItems) {
		this.feedTitle = feedTitle;
		this.feedItems = feedItems;
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public FeedItem[] getFeedItems() {
		return feedItems;
	}

	public void setFeedItems(FeedItem[] feedItems) {
		this.feedItems = feedItems;
	}
	
}
