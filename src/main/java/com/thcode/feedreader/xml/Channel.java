package com.thcode.feedreader.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * This is the Channel model used by JAXB for parsing Rss Feeds.
 * 
 * 
 * @author taha-sk
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	
	private String title;
	private Item[] item;
	
	protected Channel() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Item[] getItem() {
		return item;
	}

	public void setItem(Item[] item) {
		this.item = item;
	}

}
