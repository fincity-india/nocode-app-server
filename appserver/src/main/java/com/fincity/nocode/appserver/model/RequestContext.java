package com.fincity.nocode.appserver.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RequestContext {

	public static final String NAME = "requestContext";

	private String username;
	private String tenant;
	private String app;
	private String token;
	private Set<String> authorities;

	public static RequestContext forAnonymous(final String tenant, final String app) {
		return new RequestContext().setApp(app)
		        .setTenant(tenant)
		        .setUsername("_ANONYMOUS_USER")
		        .setAuthorities(Set.of("_ANONYMOUS"));
	}
}
