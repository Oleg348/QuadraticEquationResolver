package com.oleg348.quadraticequationresolver.controller;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oleg348.quadraticequationresolver.controller.dto.QuadraticEquationResolveRequestDto;
import com.oleg348.quadraticequationresolver.controller.dto.QuadraticEquationRootAnswerDto;
import com.oleg348.quadraticequationresolver.resolver.QuadraticEquationResolver;
import com.oleg348.quadraticequationresolver.resolver.QuadraticEquationRoot;
import com.oleg348.quadraticequationresolver.resolver.ThereIsNoRootException;
import com.oleg348.quadraticequationresolver.storage.EquationCoefficients;
import com.oleg348.quadraticequationresolver.storage.EquationRoot;
import com.oleg348.quadraticequationresolver.storage.EquationSolution;
import com.oleg348.quadraticequationresolver.storage.EquationSolutionStorageInternalException;
import com.oleg348.quadraticequationresolver.storage.QuadraticEquationSolutionStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Main quadratic equation resolver rest controller. */
@RestController
@RequestMapping(value = "equation-resolver/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class QuadraticEquationResolverController {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private final QuadraticEquationResolver quadraticEquationResolver;

    private final QuadraticEquationSolutionStorage quadraticEquationSolutionStorage;

    @GetMapping(path = "resolve")
    public QuadraticEquationRootAnswerDto resolve(double a, double b, double c) {
        try {
            var root = quadraticEquationResolver.resolve(a, b, c);
            saveSolutionInStorage(a, b, c, root);
            return QuadraticEquationRootAnswerDto.success(root.getX1(), root.getX2());
        }
        catch (ThereIsNoRootException nre) {
            return QuadraticEquationRootAnswerDto.error(nre.getMessage());
        }
    }

    @GetMapping(path = "resolve-json")
    public QuadraticEquationRootAnswerDto resolveJson(String params) {
        QuadraticEquationResolveRequestDto request;
        try {
            request = JSON_MAPPER.readValue(params, QuadraticEquationResolveRequestDto.class);
        }
        catch (JacksonException je) {
            return QuadraticEquationRootAnswerDto.error(je.getMessage());
        }
        return resolve(request.getA(), request.getB(), request.getC());
    }

    @GetMapping(path = "last-solutions")
    public List<EquationSolution> getLastSolutions(@RequestParam(defaultValue = "10") int amount) {
        return quadraticEquationSolutionStorage.getLastSolutions(amount);
    }

    private void saveSolutionInStorage(double a, double b, double c, QuadraticEquationRoot root) {
        var storageCoefficients = new EquationCoefficients(a, b, c);
        var storageRoot = new EquationRoot(root.getX1(), root.getX2());
        try {
            quadraticEquationSolutionStorage.save(storageCoefficients, storageRoot);
        }
        catch (EquationSolutionStorageInternalException sie) {
            log.error("Solution saving error", sie);
        }
    }
}
