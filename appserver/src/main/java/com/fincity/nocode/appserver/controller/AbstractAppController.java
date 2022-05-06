package com.fincity.nocode.appserver.controller;

import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;

import com.fincity.nocode.appserver.model.RequestContext;
import com.fincity.nocode.core.db.DataBaseService;
import com.fincity.nocode.core.db.IBase;
import com.fincity.nocode.core.db.condition.Condition;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

public abstract class AbstractAppController {

	protected final Logger logger;

	@Autowired
	protected DataBaseService dataService;

	@Autowired
	@Qualifier("masterData")
	protected IBase masterData;

	public AbstractAppController() {
		this.logger = LoggerFactory.getLogger(getClass());
	}

	protected Mono<RequestContext> getRequestContext(final ServerHttpRequest request) {

//		masterData.getTable(Tenant.SCHEMA.getNamespace(), Tenant.SCHEMA.getId());
//		request.
		return Mono.just(RequestContext.forAnonymous("master", "app"));
	}

	protected Pageable pageableFrom(final JsonObject filter) {
		return null;
	}

	protected Condition conditionFrom(final JsonObject filter) {
		return null;
	}

	protected Pageable pageableFrom(final ServerHttpRequest request) {
		
		if (request.getQueryParams().isEmpty()) return PageRequest.of(0, 10);
		
		final String pageString = request.getQueryParams().getFirst("_page");
		final String sizeString = request.getQueryParams().getFirst("_size");
		
		int page = 0;
		
		if (pageString != null) {
			try {
				page = Integer.parseInt(pageString.toString());
			}catch(Exception ex) {
				logger.debug("Parsing page '{}' error.", pageString, ex);
			}
		}
		
		int size = 10;
		if (sizeString != null) {
			try {
				size = Integer.parseInt(sizeString.toString());
			}catch(Exception ex) {
				logger.debug("Parsing size '{}' error.", sizeString, ex);
			}
		}
		
		final List<Order> orders = new ARray
		
		for (final Entry<String, List<String>> entry : request.getQueryParams().entrySet()) {
			if (entry.getKey().isEmpty()) continue;
			if (entry.getKey().charAt(0) == '_') continue;
			
			if (entry.getKey().endsWith("_sort"))
				sort = Order.
		}
		
		return PageRequest.of(page, size);
	}

	protected Condition conditionFrom(final ServerHttpRequest request) {
		return null;
	}
}
