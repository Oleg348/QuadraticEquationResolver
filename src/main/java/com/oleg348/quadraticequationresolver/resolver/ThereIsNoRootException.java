package com.oleg348.quadraticequationresolver.resolver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThereIsNoRootException extends RuntimeException {

    private static final String DEFAULT_MESSAGE_FORMAT = "There is no solution for coefficients:"
            + " a: %,.2f; b: %,.2f; c: %,.2f";

    private final double a;

    private final double b;

    private final double c;

    @Override
    public String getMessage() {
        return String.format(DEFAULT_MESSAGE_FORMAT, a, b, c);
    }
}
