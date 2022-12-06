package com.oleg348.quadraticequationresolver.resolver;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class QuadraticEquationRoot {

    private final double x1;

    private final double x2;

    public static QuadraticEquationRoot of(double x1, double x2) {
        return new QuadraticEquationRoot(x1, x2);
    }
}
