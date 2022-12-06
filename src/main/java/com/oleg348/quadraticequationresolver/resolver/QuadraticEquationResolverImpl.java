package com.oleg348.quadraticequationresolver.resolver;


public class QuadraticEquationResolverImpl implements QuadraticEquationResolver {

    @Override
    public QuadraticEquationRoot resolve(double a, double b, double c) {
        var d = b * b - 4 * a * c;
        if (d < 0) {
            throw new ThereIsNoRootException(a, b, c);
        }
        var doubleA = a * 2;
        return QuadraticEquationRoot.of(
                (-b - Math.sqrt(d)) / doubleA,
                (-b + Math.sqrt(d)) / doubleA);
    }
}
