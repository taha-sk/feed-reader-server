package com.thcode.feedreader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thcode.feedreader.model.Widget;
import com.thcode.feedreader.model.WidgetType;
import com.thcode.feedreader.repository.WidgetRepository;
import com.thcode.feedreader.repository.WidgetTypeRepository;

/**
 * 
 * Application Main Class
 * 
 * 
 * @author taha-sk
 *
 */
@SpringBootApplication
public class FeedReaderServerApplication implements CommandLineRunner {
	
    static {
        System.setProperty("jwt_secret_key", "dGhjb2RlX3NlY3JldF9rZXlfZm9yX2ZlZWRfcmVhZGVy");
    }
    
	@Autowired
	private WidgetTypeRepository widgetTypeRepository;
	
	@Autowired
	private WidgetRepository widgetRepository;

	public static void main(String[] args) {
		SpringApplication.run(FeedReaderServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Need "admin" role for data manipulation
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", null, AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
		
		//Clean up
		widgetRepository.deleteAll();
		widgetTypeRepository.deleteAll();
		
		//Insert Types
		WidgetType feed = new WidgetType("FEED", "Feed", null);
		WidgetType quote = new WidgetType("QUOTE", "Quote", "https://www.brainyquote.com/link/quotebr.rss");
		
		widgetTypeRepository.save(feed);
		widgetTypeRepository.save(quote);
		
		//Insert
		widgetRepository.save(new Widget(quote, "Today's Quote", quote.getDefaultValue()));
		widgetRepository.save(new Widget(feed, "Martin Fowler", "https://martinfowler.com/feed.atom"));
		widgetRepository.save(new Widget(feed, "DZone.com", "http://feeds.dzone.com/home"));
		widgetRepository.save(new Widget(feed, "TechCrunch", "https://techcrunch.com/feed"));
		widgetRepository.save(new Widget(feed, "IBM Developer", "https://developer.ibm.com/feed"));
		
		//KDnuggets request gets HTTP 406 Not Acceptable unfortunately :(, however it also returns the feed. :) I won't handle that.
		//widgetRepository.save(new Widget(feed, "KDnuggets", "https://www.kdnuggets.com/feed"));
		
		//Logout "admin"
		SecurityContextHolder.clearContext();
		
	}

}
