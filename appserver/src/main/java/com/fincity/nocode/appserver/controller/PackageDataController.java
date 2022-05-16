package com.fincity.nocode.appserver.controller;

import java.util.function.Function;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincity.nocode.appserver.model.RequestContext;
import com.fincity.nocode.core.db.IStore;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(PackageDataController.MAPPING)
public class PackageDataController extends AbstractDataController {

	public static final String MAPPING = "/api/data/package";

	protected <R> Mono<R> commonChain(final ServerHttpRequest request, final String pckg, final String dataStore,
	        Function<IStore, Mono<R>> mapper) {
		return this.getRequestContext(request)
		        .flatMap(c -> dataService.getStoreByPackageName(c.getTenant(), pckg, dataStore)
		                .flatMap(mapper)
		                .contextWrite(ctx -> ctx.put(RequestContext.NAME, c)));
	}
}
