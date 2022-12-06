package com.oleg348.quadraticequationresolver.controller;

import com.oleg348.quadraticequationresolver.resolver.QuadraticEquationResolver;
import com.oleg348.quadraticequationresolver.resolver.QuadraticEquationRoot;
import com.oleg348.quadraticequationresolver.resolver.ThereIsNoRootException;
import com.oleg348.quadraticequationresolver.storage.QuadraticEquationSolutionStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuadraticEquationResolverControllerTest {

    @Mock
    private QuadraticEquationResolver quadraticEquationResolver;

    @Mock
    private QuadraticEquationSolutionStorage quadraticEquationSolutionStorage;

    @InjectMocks
    private QuadraticEquationResolverController sut;

    private static final QuadraticEquationRoot DEFAULT_ROOTS = QuadraticEquationRoot.of(10, 11);

    @BeforeEach
    public void beforeEach() {
        when(quadraticEquationResolver.resolve(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(DEFAULT_ROOTS);
    }

    @Test
    void resolve_saves_result_in_db_and_returns_it_roots_exist() {
        var a = 1;
        var b = 2;
        var c = 3;
        var answer = sut.resolve(a, b, c);

        assertTrue(answer.isHaveRoots());
        assertEquals(DEFAULT_ROOTS.getX1(), answer.getRoot().getX1());
        assertEquals(DEFAULT_ROOTS.getX2(), answer.getRoot().getX2());
        verify(quadraticEquationSolutionStorage).save(
                argThat(coefs -> coefs.getA() == a
                        && coefs.getB() == b
                        && coefs.getC() == c),
                argThat(roots -> roots.getX1() == DEFAULT_ROOTS.getX1()
                        && roots.getX2() == DEFAULT_ROOTS.getX2())
        );
    }

    @Test
    void resolve_returns_error_and_does_not_save_anything_if_there_are_no_roots() {
        when(quadraticEquationResolver.resolve(anyDouble(), anyDouble(), anyDouble()))
                .thenThrow(new ThereIsNoRootException(1,1,1));

        var answer = sut.resolve(1, 2, 3);

        assertFalse(answer.isHaveRoots());
        assertNull(answer.getRoot());
    }
}