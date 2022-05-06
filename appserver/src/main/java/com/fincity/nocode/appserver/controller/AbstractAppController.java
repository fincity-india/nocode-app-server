package com.fincity.nocode.appserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.server.reactive.ServerHttpRequest;

import com.fincity.nocode.appserver.model.RequestContext;
import com.fincity.nocode.core.db.DataBaseService;
import com.fincity.nocode.core.db.IBase;
import com.fincity.nocode.core.db.condition.Condition;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

public abstract class AbstractAppController {
	
	@Autowired
	protected DataBaseService dataService;
	
	@Autowired
	@Qualifier("masterData")
	protected IBase masterData;

	protected Mono<RequestContext> getRequestContext(ServerHttpRequest request) {
		
//		masterData.getTable(Tenant.SCHEMA.getNamespace(), Tenant.SCHEMA.getId());
//		request.
		return Mono.just(RequestContext.forAnonymous("master", "app"));
	}
	
	protected Pageable pageableFrom(JsonObject filter) {
		return null;
	}
	
	protected Condition conditionFrom(JsonObject filter) {
		return null;
	}
	
	protected Pageable pageableFrom(ServerHttpRequest request) {
		return null;
	}
	
	protected Condition conditionFrom(ServerHttpRequest request) {
		return null;
	}
}
