package com.fincity.nocode.appserver.controller;

import static com.fincity.nocode.appserver.controller.ControllerConstants.FILTER_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.GROUP;
import static com.fincity.nocode.appserver.controller.ControllerConstants.GROUP_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAME;

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

import com.fincity.nocode.core.db.IStore;
import com.fincity.nocode.core.db.request.FilterRequest;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

public abstract class AbstractDataController extends AbstractAppController {

	// Create
	@PostMapping(GROUP_MAPPING)
	public Mono<JsonObject> create(@PathVariable(GROUP) final String group, @PathVariable(NAME) final String dataStore,
	        @RequestBody final JsonObject element, final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.create(element));
	}

	// Read
	@GetMapping(GROUP_MAPPING + ID_MAPPING)
	public Mono<JsonObject> getById(@PathVariable(GROUP) final String group, @PathVariable(NAME) final String dataStore,
	        @PathVariable(ID) final String id, final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.getById(id));
	}

	@PostMapping(GROUP_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> filterWithManyParameters(@PathVariable(GROUP) final String group,
	        @PathVariable(NAME) final String dataStore, @RequestBody final FilterRequest filter,
	        final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.filter(filter));
	}

	@GetMapping(GROUP_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> filterWithAFewParameters(@PathVariable(GROUP) final String group,
	        @PathVariable(NAME) final String dataStore, final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.filter(request.getQueryParams()));
	}

	// Update
	@PutMapping(GROUP_MAPPING)
	public Mono<JsonObject> updateCompletely(@PathVariable(GROUP) final String group,
	        @PathVariable(NAME) final String dataStore, @RequestBody final JsonObject element,
	        final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.update(element));
	}

	@PatchMapping(GROUP_MAPPING)
	public Mono<JsonObject> updatePartially(@PathVariable(GROUP) final String group,
	        @PathVariable(NAME) final String dataStore, @RequestBody final JsonObject element,
	        final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.patch(element));
	}

	// Delete
	@DeleteMapping(GROUP_MAPPING + ID_MAPPING)
	public Mono<JsonObject> deleteById(@PathVariable(GROUP) final String group,
	        @PathVariable(NAME) final String dataStore, @PathVariable(ID) final String id,
	        final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.deleteById(id));
	}

	@DeleteMapping(GROUP_MAPPING + FILTER_MAPPING)
	public Mono<Integer> deleteWithManyParameters(@PathVariable(GROUP) final String group,
	        @PathVariable(NAME) final String dataStore, @RequestBody final FilterRequest filter,
	        final ServerHttpRequest request) {

		return commonChain(request, group, dataStore, t -> t.deleteByFilter(filter));
	}

	protected abstract <R> Mono<R> commonChain(final ServerHttpRequest request, final String group,
	        final String dataStore, Function<IStore, Mono<R>> mapper);
}
