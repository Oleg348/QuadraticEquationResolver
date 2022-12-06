package com.oleg348.quadraticequationresolver.storage;

import java.util.List;

/**
 * Storage for quadratic equation solutions.
 * <br> Each method can throw {@link EquationSolutionStorageInternalException}
 * in cases of some internal storage errors.
 */
public interface QuadraticEquationSolutionStorage {

    /** Save the solution for equation coefficients. */
    void save(EquationCoefficients coefficients, EquationRoot equationRoot);

    /** Get last ${amount} saved solutions. */
    List<EquationSolution> getLastSolutions(int amount);
}
