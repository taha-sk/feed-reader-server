package com.thcode.feedreader.xml;

import com.thcode.feedreader.model.FeedResponse;

/**
 * 
 * This is the Responsible interface. 
 * 
 * It's a funny word play interface and it's making the implemented classes "response" "able". Get it ? :P
 * I'm parsing the Rss and the Atom feeds. These feeds have different structures, therefore they're parsed with different classes.
 * However, I don't want to use these different classes on the output of the FeedController.
 * I want to manage the response sent from the FeedController. For that purpose, I created the FeedResponse class.
 * Root classes of Rss and the Atom feeds implements this and handles the generation of a standardized FeedResponse class.
 * 
 * @author taha-sk
 *
 */
public interface Responsible {
	
	//Return 5 items at most
	public static final int MAX_ITEM_RETURN_SIZE = 5;
	
	public FeedResponse response();

}
