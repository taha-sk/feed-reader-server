package com.thcode.feedreader.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.thcode.feedreader.model.WidgetType;

/**
 * 
 * This is the WidgetType model data access interface
 * 
 * 
 * @author taha-sk
 *
 */
@PreAuthorize("hasRole('ADMIN')")
public interface WidgetTypeRepository extends PagingAndSortingRepository<WidgetType, String> {
	
	@Override
	@PreAuthorize("hasRole('USER')")
	Page<WidgetType> findAll(Pageable pageable);
	
	@Override
	@PreAuthorize("hasRole('USER')")
	Optional<WidgetType> findById(String id);	
	
}
