package com.thcode.feedreader.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thcode.feedreader.model.WidgetType;

/**
 * 
 * This is the WidgetType model data access interface
 * 
 * 
 * @author taha-sk
 *
 */
public interface WidgetTypeRepository extends PagingAndSortingRepository<WidgetType, String> {

}
