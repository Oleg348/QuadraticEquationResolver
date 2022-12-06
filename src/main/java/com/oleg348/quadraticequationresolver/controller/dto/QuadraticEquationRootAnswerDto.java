package com.oleg348.quadraticequationresolver.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class QuadraticEquationRootAnswerDto {

    private boolean haveRoots;

    private QuadraticEquationRootDto root;

    private String errorMessage;

    public static QuadraticEquationRootAnswerDto error(String errorMessage) {
        return new QuadraticEquationRootAnswerDto()
                .setHaveRoots(false)
                .setErrorMessage(errorMessage);
    }

    public static QuadraticEquationRootAnswerDto success(double x1, double x2) {
        return new QuadraticEquationRootAnswerDto()
                .setHaveRoots(true)
                .setRoot(new QuadraticEquationRootDto()
                        .setX1(x1)
                        .setX2(x2));
    }
}
