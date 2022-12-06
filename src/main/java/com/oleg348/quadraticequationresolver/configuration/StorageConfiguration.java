package com.oleg348.quadraticequationresolver.configuration;

import com.oleg348.quadraticequationresolver.storage.QuadraticEquationSolutionStorage;
import com.oleg348.quadraticequationresolver.storage.spring.EquationSolutionRepository;
import com.oleg348.quadraticequationresolver.storage.spring.SpringDataQuadraticEquationSolutionStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    @Bean
    public QuadraticEquationSolutionStorage quadraticEquationSolutionStorage(
            EquationSolutionRepository equationSolutionRepository) {
        return new SpringDataQuadraticEquationSolutionStorage(equationSolutionRepository);
    }
}
