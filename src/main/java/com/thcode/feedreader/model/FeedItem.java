package com.thcode.feedreader.model;

import com.thcode.feedreader.xml.Entry;
import com.thcode.feedreader.xml.Item;

/**
 * 
 * This is the Feed Item Model, used in Feed Response
 * 
 * @author taha-sk
 *
 */
public class FeedItem {
	
	private String title;
	private String link;
	private String description;
	
	protected FeedItem() {}
	
	public FeedItem(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public FeedItem (Item item) {
		this.title = item.getTitle();
		this.link = item.getLink();
		this.description = item.getDescription();
	}
	
	public FeedItem (Entry entry) {
		this.title = entry.getTitle();
		this.link = entry.getLink().getHref();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
