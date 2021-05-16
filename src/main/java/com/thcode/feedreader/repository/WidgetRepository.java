package com.thcode.feedreader.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.thcode.feedreader.model.Widget;

/**
 * 
 * This is the Widget model data access interface
 * 
 * 
 * @author taha-sk
 *
 */
@PreAuthorize("hasRole('ADMIN')")
public interface WidgetRepository extends PagingAndSortingRepository<Widget, Long> {
	
	@Override
	@PreAuthorize("hasRole('USER')")
	Page<Widget> findAll(Pageable pageable);
	
	@Override
	@PreAuthorize("hasRole('USER')")
	Optional<Widget> findById(Long id);
	
	@Override
	@PreAuthorize("@widgetRepository.count() < 10 or @widgetRepository.existsById(#widget.id)")
	Widget save(@Param("widget") Widget entity);
	
}
