package com.thcode.feedreader.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thcode.feedreader.model.FeedItem;
import com.thcode.feedreader.model.FeedResponse;

/**
 * 
 * This is the Feed model used by JAXB for parsing Atom Feeds.
 * 
 * 
 * @author taha-sk
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Feed implements Responsible {
	
	private String title;
	private Entry[] entry;
	
	protected Feed() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Entry[] getEntry() {
		return entry;
	}

	public void setEntry(Entry[] entry) {
		this.entry = entry;
	}

	@Override
	public FeedResponse response() {
		
		//Set Max Return
		int maxItemSize = MAX_ITEM_RETURN_SIZE;
		//If less, then use existing size
		if(getEntry().length < maxItemSize) {
			maxItemSize = getEntry().length;
		}
		
		FeedItem[] items = new FeedItem[maxItemSize];
		
		//set items
		for(int i=0; i<maxItemSize; i++) {
			items[i] = new FeedItem(getEntry()[i]);
		}

		return new FeedResponse(getTitle(), items);
	}

}
