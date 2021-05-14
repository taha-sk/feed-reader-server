package com.thcode.feedreader.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.thcode.feedreader.model.Widget;
import com.thcode.feedreader.model.WidgetType;
import com.thcode.feedreader.validator.WidgetValidator;

@Configuration
public class AppRepoRestConfigurer implements RepositoryRestConfigurer {
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Widget.class);
		config.exposeIdsFor(WidgetType.class);
	}
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", new WidgetValidator());
	}

}
