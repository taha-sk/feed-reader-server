package com.thcode.feedreader.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This is the Feed ControllerAdvice for Handling FeedException
 * 
 * 
 * @author taha-sk
 *
 */
@ControllerAdvice
public class FeedExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(FeedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected Map<String, Object> feedExceptionHandler(FeedException ex) {
		Map<String, Object> body = new LinkedHashMap<>();		
		body.put("errors", ex);
		return body;
	}

}
