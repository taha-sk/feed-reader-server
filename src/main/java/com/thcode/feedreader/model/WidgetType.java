package com.thcode.feedreader.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * This is the WidgetType Entity. It's used by JPA.
 * It's the persistence entity for PostgreSQL.
 * 
 * Widget types are maintained by this model.
 * 
 * @author taha-sk
 *
 */
@Entity
public class WidgetType {
	
	@Id
	private String widgetType;
	
	private String typeName;
	private String defaultValue;
	
	//Preventing default constructor access
	protected WidgetType() {}
	
	public WidgetType(String widgetType, String typeName, String defaultValue) {
		this.widgetType = widgetType;
		this.typeName = typeName;
		this.defaultValue = defaultValue;
	}

	public String getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
