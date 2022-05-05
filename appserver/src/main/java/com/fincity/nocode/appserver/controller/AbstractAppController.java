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

	protected Mono<RequestContext> getRequestContext(ServerHttpRequest request) {
		
//		masterData.getTable(Tenant.SCHEMA.getNamespace(), Tenant.SCHEMA.getId());
//		request.
		return Mono.just(RequestContext.forAnonymous("master", "app"));
	}
}
