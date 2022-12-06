package com.oleg348.quadraticequationresolver.storage.spring;

import com.oleg348.quadraticequationresolver.storage.EquationCoefficients;
import com.oleg348.quadraticequationresolver.storage.EquationRoot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringDataQuadraticEquationRootStorageTest {

    @Autowired
    private EquationSolutionRepository equationSolutionRepository;

    private SpringDataQuadraticEquationSolutionStorage sut;

    private static final EquationCoefficients DEFAULT_COEFFICIENTS = new EquationCoefficients(1, 2, 3);

    private static final EquationRoot DEFAULT_ROOT = new EquationRoot(4, 5);

    @BeforeEach
    public void beforeEach() {
        equationSolutionRepository.deleteAll();
        sut = new SpringDataQuadraticEquationSolutionStorage(equationSolutionRepository);
    }

    @Test
    void save_saves_the_entity_in_database() {
        sut.save(DEFAULT_COEFFICIENTS, DEFAULT_ROOT);

        assertIsTheOnlyOneDefaultRecord();
    }

    @Test
    void save_does_not_add_new_record_if_same_coefficients_exist() {
        sut.save(DEFAULT_COEFFICIENTS, DEFAULT_ROOT);
        sut.save(DEFAULT_COEFFICIENTS, DEFAULT_ROOT);

        assertIsTheOnlyOneDefaultRecord();
    }

    private void assertIsTheOnlyOneDefaultRecord() {
        var solutions = equationSolutionRepository.findAll();
        assertEquals(1, solutions.size());

        var solution0 = solutions.get(0);
        assertEquals(DEFAULT_COEFFICIENTS.getA(), solution0.getCoefficientsId().getA());
        assertEquals(DEFAULT_COEFFICIENTS.getB(), solution0.getCoefficientsId().getB());
        assertEquals(DEFAULT_COEFFICIENTS.getC(), solution0.getCoefficientsId().getC());
        assertEquals(DEFAULT_ROOT.getX1(), solution0.getX1());
        assertEquals(DEFAULT_ROOT.getX2(), solution0.getX2());
        assertThat(solution0.getCreationTime())
                .isNotEqualTo(0)
                .isLessThanOrEqualTo(System.currentTimeMillis());
    }
}
