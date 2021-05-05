package com.thcode.feedreader.controller;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;

import com.thcode.feedreader.exception.FeedException;
import com.thcode.feedreader.model.FeedRequest;
import com.thcode.feedreader.model.FeedResponse;
import com.thcode.feedreader.xml.Feed;
import com.thcode.feedreader.xml.Responsible;
import com.thcode.feedreader.xml.Rss;

/**
 * 
 * This is the FeedController for getting, parsing and validating feedUrl provided in the request.
 * I used JAXB version 2.3.3 for parsing.
 * XML models that I used for parsing are found under package com.thcode.feedreader.xml.
 * 
 * @author taha-sk
 *
 */
@RestController
public class FeedController {
	
	Logger logger = LoggerFactory.getLogger(FeedController.class);
	
    @Autowired
    private MessageSource messageSource;
	
	@GetMapping("/api/getFeed")
	public ResponseEntity<FeedResponse> getFeed(@RequestBody FeedRequest feedRequest) throws FeedException {
		
		//Check for url
		if(feedRequest.getFeedUrl() == null || feedRequest.getFeedUrl().trim().isBlank()) {
			throw new FeedException(messageSource.getMessage("notfound.FeedRequest.feedUrl", null, null));
		}
		
		//Init response
		FeedResponse response = null;
		
		//Let's get the information
		RestTemplate restTemplate = new RestTemplate();
		
		try
		{
			String feedString = restTemplate.getForObject(feedRequest.getFeedUrl(), String.class);
			
			//Initialize SaxParser and disable validation of dtds
	    	SAXParserFactory spf = SAXParserFactory.newInstance();
	        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	        spf.setFeature("http://xml.org/sax/features/validation", false);
	        
	        //Set source
	        Source stringSource = new SAXSource(spf.newSAXParser().getXMLReader(), new InputSource(new StringReader(feedString)));
	        
	        //Initialize JAXBContext for Rss and Atom and unmarshall the feed
	        JAXBContext context = JAXBContext.newInstance(Rss.class, Feed.class);
	    	Unmarshaller unmar = context.createUnmarshaller();
	    	
	    	//Get the feed objects and create the response
	    	Responsible feed = (Responsible) unmar.unmarshal(stringSource);
	    	response = feed.response();

		}catch (RestClientException ex) {
			logger.error("RestClientException during the url call: {}", ex.getMessage());
			throw new FeedException(messageSource.getMessage("restError.FeedController.feedUrl", null, null));
			//Shortcut, handle the rest :)
			//Don't do this, handle cases separately and give out meaningful messages.
			//I did it for the simplicity of the tutorial.
		} catch (Exception ex) {
			logger.error("Error occured in JAXB: {}", ex.getMessage());
			throw new FeedException(messageSource.getMessage("jaxbError.FeedController.feedUrl", null, null));
		}		
		
		return ResponseEntity.ok(response);
	}

}
