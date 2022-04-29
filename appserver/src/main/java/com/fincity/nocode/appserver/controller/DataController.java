package com.fincity.nocode.appserver.controller;

import static com.fincity.nocode.appserver.controller.ControllerConstants.FILTER_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID;
import static com.fincity.nocode.appserver.controller.ControllerConstants.ID_MAPPING;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAME;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAMESPACE;
import static com.fincity.nocode.appserver.controller.ControllerConstants.NAMESPACE_MAPPING;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;

import io.netty.handler.codec.http.HttpRequest;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(DataController.MAPPING)
public class DataController extends AbstractAppController {

	public static final String MAPPING = "/api/data";

	// Create
	@PostMapping(NAMESPACE_MAPPING)
	public Mono<JsonElement> create(@PathVariable(NAMESPACE) String namespace, @PathVariable(NAME) String dataTable,
			@RequestBody JsonElement element) {

		return Mono.empty();
	}

	// Read
	@GetMapping(NAMESPACE_MAPPING + ID_MAPPING)
	public Mono<JsonElement> getById(@PathVariable(NAMESPACE) String namespace, @PathVariable(NAME) String dataTable,
			@PathVariable(ID) String id) {

		return Mono.empty();
	}

	@PostMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonElement>> filterWithManyParameters(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonElement filter) {

		return Mono.empty();
	}

	@GetMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonElement>> filterWithAFewParameters(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, HttpRequest request) {

		return Mono.empty();
	}

	// Update
	@PutMapping(NAMESPACE_MAPPING)
	public Mono<JsonElement> updateCompletely(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonElement element) {

		return Mono.empty();
	}

	@PatchMapping(NAMESPACE_MAPPING)
	public Mono<JsonElement> updatePartially(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonElement element) {

		return Mono.empty();
	}

	// Delete
	@DeleteMapping(NAMESPACE_MAPPING + ID_MAPPING)
	public Mono<JsonElement> deleteById(@PathVariable(NAMESPACE) String namespace, @PathVariable(NAME) String dataTable,
			@PathVariable(ID) String id) {

		return Mono.empty();
	}

	@DeleteMapping(NAMESPACE_MAPPING + FILTER_MAPPING)
	public Mono<Page<JsonElement>> deleteWithManyParameters(@PathVariable(NAMESPACE) String namespace,
			@PathVariable(NAME) String dataTable, @RequestBody JsonElement filter) {

		return Mono.empty();
	}

}
