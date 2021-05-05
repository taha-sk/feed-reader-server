package com.thcode.feedreader.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thcode.feedreader.model.FeedItem;
import com.thcode.feedreader.model.FeedResponse;

/**
 * 
 * This is the Rss model used by JAXB for parsing Rss Feeds.
 * 
 * 
 * @author taha-sk
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss implements Responsible {
	
	private Channel channel;
	
	protected Rss() {}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public FeedResponse response() {
		
		//Set Max Return
		int maxItemSize = MAX_ITEM_RETURN_SIZE;
		
		Item[] rssItems = getChannel().getItem();
		
		//If less, then use existing size
		if(rssItems.length < maxItemSize) {
			maxItemSize = rssItems.length;
		}
		
		FeedItem[] items = new FeedItem[maxItemSize];
		
		//set items
		for(int i=0; i<maxItemSize; i++) {
			items[i] = new FeedItem(rssItems[i]);
		}
		
		return new FeedResponse(getChannel().getTitle(), items);
	}
	
}
