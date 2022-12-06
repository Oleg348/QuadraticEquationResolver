package com.oleg348.quadraticequationresolver.storage.spring;

import com.oleg348.quadraticequationresolver.storage.EquationCoefficients;
import com.oleg348.quadraticequationresolver.storage.EquationRoot;
import com.oleg348.quadraticequationresolver.storage.EquationSolution;
import com.oleg348.quadraticequationresolver.storage.EquationSolutionStorageInternalException;
import com.oleg348.quadraticequationresolver.storage.QuadraticEquationSolutionStorage;
import com.oleg348.quadraticequationresolver.storage.spring.entity.CoefficientsId;
import com.oleg348.quadraticequationresolver.storage.spring.entity.EquationSolutionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/** Spring data implementation of {@link QuadraticEquationSolutionStorage}. */
@RequiredArgsConstructor
public class SpringDataQuadraticEquationSolutionStorage implements QuadraticEquationSolutionStorage {

    private final EquationSolutionRepository equationSolutionRepository;

    @Override
    public void save(EquationCoefficients coefficients, EquationRoot equationRoot) {
        var entity = new EquationSolutionEntity()
                .setCoefficientsId(mapToCoefficientsId(coefficients))
                .setX1(equationRoot.getX1())
                .setX2(equationRoot.getX2())
                .setCreationTime(System.currentTimeMillis());
        doWithCatchSpringExceptions(() -> equationSolutionRepository.save(entity));
    }

    @Override
    public List<EquationSolution> getLastSolutions(int amount) {
        return doWithCatchSpringExceptions(
                () -> equationSolutionRepository.getLastRecords(amount).stream()
                        .map(this::mapToStorageSolution)
                        .collect(Collectors.toList())
        );
    }

    private CoefficientsId mapToCoefficientsId(EquationCoefficients coefficients) {
        return new CoefficientsId(coefficients.getA(), coefficients.getB(), coefficients.getC());
    }

    private EquationSolution mapToStorageSolution(EquationSolutionEntity solutionEntity) {
        var coefficientsId = solutionEntity.getCoefficientsId();
        return new EquationSolution()
                .setA(coefficientsId.getA())
                .setB(coefficientsId.getB())
                .setC(coefficientsId.getC())
                .setX1(solutionEntity.getX1())
                .setX2(solutionEntity.getX2());
    }

    private <T> T doWithCatchSpringExceptions(Supplier<? extends T> actionsWithResult) {
        try {
            return actionsWithResult.get();
        }
        catch (DataAccessException dae) {
            throw new EquationSolutionStorageInternalException(dae);
        }
    }
}
