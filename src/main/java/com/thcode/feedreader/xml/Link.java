package com.thcode.feedreader.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 
 * This is the Link model used by JAXB for parsing Atom Feeds.
 * 
 * 
 * @author taha-sk
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Link {
	
	@XmlAttribute
	private String href;
	
	protected Link() {}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
}
