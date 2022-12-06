package com.oleg348.quadraticequationresolver.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuadraticEquationResolverImplTest {

    private static final int COMPARISON_PRECISION = 5;

    private final QuadraticEquationResolverImpl sut = new QuadraticEquationResolverImpl();

    @Test
    void resolve_throws_ThereIsNoSolutionException_if_D_is_less_than_zero() {
        // 2^2 - 4*1*3 = -8 < 0
        assertThrows(
                ThereIsNoRootException.class,
                () -> sut.resolve(1,2,3));
    }

    @ParameterizedTest
    @CsvSource({
            //a,b,c,x1,x2
            "1, -4, 4, 2, 2"//d = 0
            , "1, 4, 4, -2, -2"//d = 0
            , "1, 0, -4, -2, 2"// d > 0, sqrt(d) is integer
            , "1, 1, -4, -2.56155, 1.56155"// d > 0, sqrt(d) is not integer
    })
    void resolve_returns_correct_answer(double a, double b, double c, double expectedX1, double expectedX2) {
        var solution = sut.resolve(a, b, c);

        assertThat(List.of(solution.getX1(), solution.getX2()))
                .map(this::precise)
                .containsExactlyInAnyOrder(precise(expectedX1), precise(expectedX2));
    }

    private String precise(double x) {
        return String.format("%." + COMPARISON_PRECISION + "f", x);
    }
}