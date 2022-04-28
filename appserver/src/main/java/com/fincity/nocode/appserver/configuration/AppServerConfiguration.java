package com.fincity.nocode.appserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.fincity.nocode.core.configuration.master.NoCodeCoreConfiguration;

@Configuration
@EnableWebFlux
public class AppServerConfiguration extends NoCodeCoreConfiguration implements WebFluxConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/api/**");
	}
	
	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
	
		WebFluxConfigurer.super.configureHttpMessageCodecs(configurer);
	}
}
