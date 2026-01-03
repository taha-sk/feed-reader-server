package com.thcode.feedreader.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 
 * This is the Item model used by JAXB for parsing Rss Feeds.
 * 
 * 
 * @author taha-sk
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	private String title;
	private String description;
	private String link;
	
	protected Item() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
