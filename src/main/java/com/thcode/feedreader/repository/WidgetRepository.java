package com.thcode.feedreader.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thcode.feedreader.model.Widget;

/**
 * 
 * This is the Widget model data access interface
 * 
 * 
 * @author taha-sk
 *
 */
public interface WidgetRepository extends PagingAndSortingRepository<Widget, Long> {

}
