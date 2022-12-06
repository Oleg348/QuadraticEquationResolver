package com.oleg348.quadraticequationresolver.storage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class EquationSolution {

    private double a;

    private double b;

    private double c;

    private double x1;

    private double x2;
}
