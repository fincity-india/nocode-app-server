package com.fincity.nocode.appserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.server.reactive.ServerHttpRequest;

import com.fincity.nocode.appserver.model.RequestContext;
import com.fincity.nocode.core.db.DataBaseService;
import com.fincity.nocode.core.db.IBase;

import reactor.core.publisher.Mono;

public abstract class AbstractAppController {

	@Autowired
	protected DataBaseService dataService;

	@Autowired
	@Qualifier("masterData")
	protected IBase masterData;

	protected Mono<RequestContext> getRequestContext(final ServerHttpRequest request) {

//		masterData.getTable(Tenant.SCHEMA.getNamespace(), Tenant.SCHEMA.getId());
//		request.
		return Mono.just(RequestContext.forAnonymous("master", "app"));
	}

//	protected Pageable pageableFrom(final JsonObject filter) {
//		return null;
//	}
//
//	protected Condition conditionFrom(final JsonObject filter) {
//		return null;
//	}
//
//	protected Pageable pageableFrom(final ServerHttpRequest request) {
//
//		if (request.getQueryParams().isEmpty())
//			return PageRequest.of(0, 10);
//
//		final String pageString = request.getQueryParams().getFirst("_page");
//		final String sizeString = request.getQueryParams().getFirst("_size");
//
//		int page = 0;
//
//		if (pageString != null) {
//			try {
//				page = Integer.parseInt(pageString);
//			} catch (Exception ex) {
//				logger.debug("Parsing page '{}' error.", pageString, ex);
//			}
//		}
//
//		int size = 10;
//		if (sizeString != null) {
//			try {
//				size = Integer.parseInt(sizeString);
//			} catch (Exception ex) {
//				logger.debug("Parsing size '{}' error.", sizeString, ex);
//			}
//		}
//
//		return PageRequest.of(page, size, sortFrom(request));
//	}
//
//	private Sort sortFrom(final ServerHttpRequest request) {
//		
//		final List<Order> orders = new ArrayList<>();
//
//		for (final Entry<String, List<String>> entry : request.getQueryParams().entrySet()) {
//			if (entry.getKey().isEmpty() || entry.getKey().charAt(0) == '_')
//				continue;
//
//			if (entry.getKey().endsWith("_sort")) {
//
//				final String key = entry.getKey().substring(0, entry.getKey().length() - 6);
//				if (entry.getValue().isEmpty() || entry.getValue().get(0).isBlank()
//						|| entry.getValue().get(0).equalsIgnoreCase("asc"))
//					orders.add(Order.asc(key));
//				else
//					orders.add(Order.desc(key));
//			}
//		}
//		
//		return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
//	}
//
//	protected Condition conditionFrom(final ServerHttpRequest request) {
//		
//		final List<Condition> orders = new ArrayList<>();
//
//		for (final Entry<String, List<String>> entry : request.getQueryParams().entrySet()) {
//			if (entry.getKey().isEmpty() || entry.getKey().charAt(0) == '_')
//				continue;
//
//			if (entry.getKey().endsWith("_sort")) {
//
//				final String key = entry.getKey().substring(0, entry.getKey().length() - 6);
//				if (entry.getValue().isEmpty() || entry.getValue().get(0).isBlank()
//						|| entry.getValue().get(0).equalsIgnoreCase("asc"))
//					orders.add(Order.asc(key));
//				else
//					orders.add(Order.desc(key));
//			}
//		}
//		
//		return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
//	}
}
