package com.thcode.feedreader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * This is the Widget Entity. It's used by JPA.
 * It's the persistence entity for PostgreSQL.
 * 
 * Widgets are maintained by this model.
 * 
 * @author taha-sk
 *
 */
@Entity
public class Widget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private WidgetType widgetType;
	
	private String widgetTitle;
	private String widgetValue;
	
	//Preventing default constructor access
	protected Widget() {}
	
	public Widget(WidgetType widgetType, String widgetTitle, String widgetValue) {
		this.widgetType = widgetType;
		this.widgetTitle = widgetTitle;
		this.widgetValue = widgetValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public WidgetType getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(WidgetType widgetType) {
		this.widgetType = widgetType;
	}

	public String getWidgetTitle() {
		return widgetTitle;
	}

	public void setWidgetTitle(String widgetTitle) {
		this.widgetTitle = widgetTitle;
	}

	public String getWidgetValue() {
		return widgetValue;
	}

	public void setWidgetValue(String widgetValue) {
		this.widgetValue = widgetValue;
	}
	
}
