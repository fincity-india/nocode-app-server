package com.fincity.nocode.appserver.controller;

import static com.fincity.nocode.appserver.controller.ControllerConstants.FILTER_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAME;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAMESPACE;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAMESPACE_MAPPING;

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
import com.fincity.nocode.appserver.model.UserContext;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(DataController.MAPPING)
public class DataController extends AbstractAppController {

	public static final String MAPPING = "/api/data";

	// Create
	@PostMapping(NAMESPACE_MAPPING)
	public Mono<JsonObject> create(@PathVariable(NAMESPACE) String namespace, @PathVariable(NAME) String dataTable,
			@RequestBody JsonObject element, ServerHttpRequest request) {

		return this.getRequestContext(request).map(c -> dataService.getTable(c.getTenant(), namespace, dataTable)
				.map(t -> t.create(element).contextWrite(ctx -> ctx.put(RequestContext.NAME, c))));
	}

	// Read
	@GetMapping(NAMESPACE_MAPPING + ID_MAPPING)
	public Mono<JsonObject> getById(@PathVariable(NAMESPACE) String namespace, @PathVariable(NAME) String dataTable,
			@PathVariable(ID) String id, ServerHttpRequest request) {

		return Mono.empty();
	}

	@PostMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> filterWithManyParameters(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonObject filter, ServerHttpRequest request) {

		return Mono.empty();
	}

	@GetMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> filterWithAFewParameters(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, ServerHttpRequest request) {

		return Mono.empty();
	}

	// Update
	@PutMapping(NAMESPACE_MAPPING)
	public Mono<JsonObject> updateCompletely(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonObject element, ServerHttpRequest request) {

		return Mono.empty();
	}

	@PatchMapping(NAMESPACE_MAPPING)
	public Mono<JsonObject> updatePartially(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonObject element, ServerHttpRequest request) {

		return Mono.empty();
	}

	// Delete
	@DeleteMapping(NAMESPACE_MAPPING + ID_MAPPING)
	public Mono<JsonObject> deleteById(@PathVariable(NAMESPACE) String namespace, @PathVariable(NAME) String dataTable,
			@PathVariable(ID) String id, ServerHttpRequest request) {

		return Mono.empty();
	}

	@DeleteMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonObject>> deleteWithManyParameters(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonObject filter, ServerHttpRequest request) {

		return Mono.empty();
	}

}
