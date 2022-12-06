package com.oleg348.quadraticequationresolver.resolver;

/** Resolver of quadratic equations. */
public interface QuadraticEquationResolver {

    /**
     * Get a solution for quadratic equation.
     *
     * @throws ThereIsNoRootException there is no solution for input coefficients.
     */
    QuadraticEquationRoot resolve(double a, double b, double c);
}
