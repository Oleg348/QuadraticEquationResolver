package com.oleg348.quadraticequationresolver.configuration;

import com.oleg348.quadraticequationresolver.resolver.QuadraticEquationResolver;
import com.oleg348.quadraticequationresolver.resolver.QuadraticEquationResolverImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuadraticEquationResolverConfiguration {

    @Bean
    public QuadraticEquationResolver quadraticEquationResolver() {
        return new QuadraticEquationResolverImpl();
    }
}
