package com.fincity.nocode.appserver.controller;

import static com.fincity.nocode.appserver.controller.ControllerConstants.FILTER_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAME;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAMESPACE;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAMESPACE_MAPPING;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincity.nocode.appserver.model.RequestContext;
import com.fincity.nocode.core.db.IStore;
import com.fincity.nocode.core.db.request.FilterRequest;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(DataController.MAPPING)
public class DataController extends AbstractAppController {

	public static final String MAPPING = "/api/data";

	// Create
	@PostMapping(NAMESPACE_MAPPING)
	public Mono<JsonObject> create(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @RequestBody final JsonObject element,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.create(element));
	}

	// Read
	@GetMapping(NAMESPACE_MAPPING + ID_MAPPING)
	public Mono<JsonObject> getById(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @PathVariable(ID) final String id,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.getById(id));
	}

	@PostMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> filterWithManyParameters(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @RequestBody final FilterRequest filter,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.filter(filter));
	}

	@GetMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> filterWithAFewParameters(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.filter(request.getQueryParams()));
	}

	// Update
	@PutMapping(NAMESPACE_MAPPING)
	public Mono<JsonObject> updateCompletely(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @RequestBody final JsonObject element,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.update(element));
	}

	@PatchMapping(NAMESPACE_MAPPING)
	public Mono<JsonObject> updatePartially(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @RequestBody final JsonObject element,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.patch(element));
	}

	// Delete
	@DeleteMapping(NAMESPACE_MAPPING + ID_MAPPING)
	public Mono<JsonObject> deleteById(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @PathVariable(ID) final String id,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.deleteById(id));
	}

	@DeleteMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Integer> deleteWithManyParameters(@PathVariable(NAMESPACE) final String namespace,
			@PathVariable(NAME) final String dataStore, @RequestBody final FilterRequest filter,
			final ServerHttpRequest request) {

		return commonChain(request, namespace, dataStore, t -> t.deleteByFilter(filter));
	}

	private <R> Mono<R> commonChain(final ServerHttpRequest request, final String namespace, final String dataStore,
			Function<IStore, Mono<R>> mapper) {
		return this.getRequestContext(request).flatMap(c -> dataService.getStore(c.getTenant(), namespace, dataStore)
				.flatMap(mapper).contextWrite(ctx -> ctx.put(RequestContext.NAME, c)));
	}
}
