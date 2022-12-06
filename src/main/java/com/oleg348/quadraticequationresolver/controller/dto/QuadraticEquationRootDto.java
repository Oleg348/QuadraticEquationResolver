package com.oleg348.quadraticequationresolver.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class QuadraticEquationRootDto {

    private double x1;

    private double x2;
}
