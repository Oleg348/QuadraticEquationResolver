package com.oleg348.quadraticequationresolver.storage.spring.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/** Composite key for {@link EquationSolutionEntity} */
@Embeddable
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class CoefficientsId implements Serializable {

    private static final long serialVersionUID = 9182939071084632860L;

    private final double a;

    private final double b;

    private final double c;

    public CoefficientsId() {
        this(0, 0, 0);
    }
}
