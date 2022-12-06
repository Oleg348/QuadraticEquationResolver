package com.oleg348.quadraticequationresolver.storage.spring.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "SOLUTION",
        uniqueConstraints = @UniqueConstraint(columnNames = {"a", "b", "c"})
)
@Getter
@Setter
@Accessors(chain = true)
public class EquationSolutionEntity {

    @EmbeddedId
    private CoefficientsId coefficientsId;

    @Column(updatable = false)
    private double x1;

    @Column(updatable = false)
    private double x2;

    @Column(updatable = false)
    private long creationTime;
}

