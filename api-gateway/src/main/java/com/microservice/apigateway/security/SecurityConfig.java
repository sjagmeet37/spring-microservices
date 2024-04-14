package com.microservice.apigateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    public SecurityWebFiltersChain springSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {


    }

}
